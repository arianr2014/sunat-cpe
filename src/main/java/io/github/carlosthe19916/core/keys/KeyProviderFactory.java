package io.github.carlosthe19916.core.keys;

import io.github.carlosthe19916.core.keys.component.ComponentFactory;
import io.github.carlosthe19916.core.keys.component.ComponentModel;
import io.github.carlosthe19916.core.models.OrganizationModel;

public interface KeyProviderFactory<T extends KeyProvider> extends ComponentFactory<T, KeyProvider> {

    T create(OrganizationModel organization, ComponentModel model);

}
