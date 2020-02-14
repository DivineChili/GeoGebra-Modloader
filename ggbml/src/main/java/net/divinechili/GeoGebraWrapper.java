package net.divinechili;

import org.geogebra.desktop.CommandLineArguments;
import org.geogebra.desktop.geogebra3D.App3D;
import org.geogebra.desktop.gui.app.GeoGebraFrame;
import org.geogebra.desktop.main.AppD;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Frame for geogebra 3D.
 *
 * @author Mathieu
 *
 */
public class GeoGebraWrapper extends GeoGebraFrame {

    public static AppD WrapperApp;

	private static final long serialVersionUID = 1L;

	public static synchronized void main(CommandLineArguments args) {
		GeoGebraFrame.init(args, new GeoGebraWrapper());
	}

	@Override
	protected AppD createApplication(CommandLineArguments args, JFrame frame) {
	    GeoGebraWrapper.WrapperApp = new App3D(args, frame, true);
		GeoGebraWrapper.WrapperApp.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				super.windowClosing(windowEvent);
				ModLoader.close();
			}
		});
		return GeoGebraWrapper.WrapperApp;
	}

	/**
	 * Create a new 3D geogebra window
	 *
	 * @param args
	 *            command line arguments
	 * @return new geogebra window
	 */
	public static synchronized GeoGebraFrame createNewWindow3D(
			CommandLineArguments args) {
		return createNewWindow(args, new GeoGebraWrapper());
	}

	@Override
	protected GeoGebraFrame copy() {
		return new GeoGebraWrapper();
	}

}
