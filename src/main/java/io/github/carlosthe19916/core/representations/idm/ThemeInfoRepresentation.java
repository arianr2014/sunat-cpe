package io.github.carlosthe19916.core.representations.idm;

public class ThemeInfoRepresentation {

    private String name;
    private String[] locales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLocales() {
        return locales;
    }

    public void setLocales(String[] locales) {
        this.locales = locales;
    }
}