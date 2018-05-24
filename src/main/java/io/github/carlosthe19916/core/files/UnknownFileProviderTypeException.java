package io.github.carlosthe19916.core.files;

public class UnknownFileProviderTypeException extends RuntimeException {

    public UnknownFileProviderTypeException(String securityContextType) {
        super("Unknown security context type:" + securityContextType);
    }

}
