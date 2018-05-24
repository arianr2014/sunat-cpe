package io.github.carlosthe19916.core.utils.files.exceptions;

public class NotReadableCompressFileException extends Exception {

    public NotReadableCompressFileException() {
    }

    public NotReadableCompressFileException(String message) {
        super(message);
    }

    public NotReadableCompressFileException(String message, Throwable e) {
        super(message, e);
    }

}
