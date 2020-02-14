package net.divinechili.exceptions;

public class ModLoaderException extends Exception {
    public ModLoaderException() {
        super();
    }

    public ModLoaderException(String s) {
        super(s);
    }

    public ModLoaderException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
