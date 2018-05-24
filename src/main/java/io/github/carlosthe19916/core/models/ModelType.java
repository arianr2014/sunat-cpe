package io.github.carlosthe19916.core.models;

public enum ModelType {

    ORGANIZATION("organization");

    private String alias;

    ModelType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
