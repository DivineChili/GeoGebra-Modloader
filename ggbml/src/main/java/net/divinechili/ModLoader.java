package net.divinechili;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.divinechili.exceptions.ModLoaderException;
import net.divinechili.exceptions.PluginException;
import net.divinechili.plugin.IPlugin;
import org.geogebra.desktop.CommandLineArguments;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader extends Application {

    private static Stage ProgramWrapper = null;
    private static final String pluginsDir = "plugins";
    private static List<IPlugin> pluginsList = new ArrayList<>();
    private static List<String> loadedPlugins = new ArrayList<>();

    public static void main(String[] args) throws PluginException {

        getPlugins();

        for (IPlugin plugin : ModLoader.pluginsList) {
            plugin.preInitialization();
        }

        launch(args);
    }

    public static void close() {
        /*
         * Close operation
         */

        if (GeoGebraWrapper.WrapperApp != null)
            GeoGebraWrapper.WrapperApp.exitAll();
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GeoGebra Modloader");
        System.out.println("JavaFX initialization phase started successfully!");
        System.out.println("Working directory: " + new File("./").getCanonicalPath());

        ModLoader.ProgramWrapper = primaryStage;

        // Start GeoGebra with custom wrapper
        EventQueue.invokeAndWait(() -> GeoGebraWrapper.main(new CommandLineArguments(new String[]{})));

        // Innitialize plugins after geogebra is loaded
        for (IPlugin pf : ModLoader.pluginsList) {
            try {
                pf.initialization();
            } catch (SecurityException secEx) {
                System.out.println("A plugin tried to do something illegal during post-init!");
            }
        }
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            ModLoader.close();
        });

        Parent root = FXMLLoader.load(getClass().getResource("/assets/gui/main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static void getPlugins() throws PluginException {
        File dir = new File(System.getProperty("user.dir") + File.separator + pluginsDir);

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                try {
                    // only consider files ending in ".jar"
                    if (!file.getName().endsWith(".jar"))
                        continue;

                    JarFile pluginPkg = new JarFile(file.getCanonicalPath());
                    Enumeration<JarEntry> e = pluginPkg.entries();

                    System.out.println("Loading : " + pluginPkg.getName());

                    URL[] urls = {new URL("jar:file:" + file.getCanonicalPath() + "!/")};
                    URLClassLoader cl = URLClassLoader.newInstance(urls);

                    while (e.hasMoreElements()) {
                        JarEntry je = e.nextElement();
                        if (je.isDirectory() || !je.getName().endsWith(".class")) continue;

                        String className = je.getName().substring(0, je.getName().length() - 6);
                        className = className.replace('/', '.');


                        Class c = cl.loadClass(className);

                        System.out.println("Loading class: " + c.getName());

                        Class[] intf = c.getInterfaces();

                        for (Class anIntf : intf) {
                            if (anIntf.getName().equals("net.divinechili.plugin.IPlugin")) {

                                IPlugin pf = (IPlugin) c.newInstance();
                                if (!loadedPlugins.contains(pf.getPluginName())) {
                                    pluginsList.add(pf);
                                    loadedPlugins.add(pf.getPluginName());
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println('\n');
            }
        }
    }
}
