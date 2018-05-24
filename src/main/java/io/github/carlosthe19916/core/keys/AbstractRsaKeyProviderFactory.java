package io.github.carlosthe19916.core.keys;

import io.github.carlosthe19916.core.keys.component.ComponentModel;
import io.github.carlosthe19916.core.keys.component.ComponentValidationException;
import io.github.carlosthe19916.core.keys.provider.ConfigurationValidationHelper;
import io.github.carlosthe19916.core.keys.provider.ProviderConfigurationBuilder;
import io.github.carlosthe19916.core.keys.component.ComponentModel;
import io.github.carlosthe19916.core.keys.component.ComponentValidationException;
import io.github.carlosthe19916.core.keys.provider.ConfigurationValidationHelper;
import io.github.carlosthe19916.core.keys.provider.ProviderConfigurationBuilder;
import io.github.carlosthe19916.core.models.OrganizationModel;

public abstract class AbstractRsaKeyProviderFactory {

    public final static ProviderConfigurationBuilder configurationBuilder() {
        return ProviderConfigurationBuilder.create()
                .property(Attributes.PRIORITY_PROPERTY)
                .property(Attributes.ENABLED_PROPERTY)
                .property(Attributes.ACTIVE_PROPERTY);
    }

    public void validateConfiguration(OrganizationModel organization, ComponentModel model) throws ComponentValidationException {
        ConfigurationValidationHelper.check(model)
                .checkLong(Attributes.PRIORITY_PROPERTY, false)
                .checkBoolean(Attributes.ENABLED_PROPERTY, false)
                .checkBoolean(Attributes.ACTIVE_PROPERTY, false);
    }
}
