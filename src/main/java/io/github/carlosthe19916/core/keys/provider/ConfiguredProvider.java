package io.github.carlosthe19916.core.keys.provider;

import java.util.List;

public interface ConfiguredProvider {
    String getHelpText();

    List<ProviderConfigProperty> getConfigProperties();
}
