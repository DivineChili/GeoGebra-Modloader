package net.divinechili.plugin;

import net.divinechili.gui.GuiMaker;

public interface IPlugin {
    String getPluginName();

    String getPluginAuthor();

    void preInitialization();
    void initialization();
    void cleanUp();

    GuiMaker getGuiMaker();

    boolean hasError();
}