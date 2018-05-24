package io.github.carlosthe19916.core.models.utils;

import io.github.carlosthe19916.core.keys.KeyProvider;
import io.github.carlosthe19916.core.keys.component.ComponentModel;
import io.github.carlosthe19916.core.models.ComponentProvider;
import io.github.carlosthe19916.core.models.ModelException;
import io.github.carlosthe19916.core.models.OrganizationModel;
import org.keycloak.common.util.MultivaluedHashMap;
import io.github.carlosthe19916.core.keys.KeyProvider;
import io.github.carlosthe19916.core.keys.component.ComponentModel;
import io.github.carlosthe19916.core.models.ComponentProvider;
import io.github.carlosthe19916.core.models.ModelException;
import io.github.carlosthe19916.core.models.OrganizationModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class DefaultKeyProviders {

    @Inject
    private ComponentProvider componentProvider;

    public void createProviders(OrganizationModel organization) throws ModelException {
        ComponentModel generated = new ComponentModel();
        generated.setName("rsa-generated");
        generated.setParentId(organization.getId());
        generated.setProviderId("rsa-generated");
        generated.setProviderType(KeyProvider.class.getName());

        MultivaluedHashMap<String, String> config = new MultivaluedHashMap<>();
        config.putSingle("priority", "100");
        generated.setConfig(config);

        componentProvider.addComponentModel(organization, generated);
    }

    public void createProviders(OrganizationModel organization, String privateKeyPem, String certificatePem) throws ModelException {
        ComponentModel rsa = new ComponentModel();
        rsa.setName("rsa");
        rsa.setParentId(organization.getId());
        rsa.setProviderId("rsa");
        rsa.setProviderType(KeyProvider.class.getName());

        MultivaluedHashMap<String, String> config = new MultivaluedHashMap<>();
        config.putSingle("priority", "100");
        config.putSingle("privateKey", privateKeyPem);
        if (certificatePem != null) {
            config.putSingle("certificate", certificatePem);
        }
        rsa.setConfig(config);

        componentProvider.addComponentModel(organization, rsa);
    }

}