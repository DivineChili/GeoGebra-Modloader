package net.divinechili.exceptions;

public class PluginException extends ModLoaderException {
    public PluginException() {
        super();
    }

    public PluginException(String s) {
        super(s);
    }

    public PluginException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
