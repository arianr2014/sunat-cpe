package io.github.carlosthe19916.core.security;

public class UnknownSecurityContextTypeException extends RuntimeException {

    public UnknownSecurityContextTypeException(String securityContextType) {
        super("Unknown security context type:" + securityContextType);
    }
}
