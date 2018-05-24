package io.github.carlosthe19916.pe.bootstrap;

import io.github.carlosthe19916.pe.models.OrganizacionInformacionAdicionalModel;
import io.github.carlosthe19916.pe.models.OrganizacionInformacionSunatModel;
import io.github.carlosthe19916.pe.models.OrganizationInformacionAdicionalProvider;
import io.github.carlosthe19916.pe.models.OrganizationInformacionSunatProvider;
import io.github.carlosthe19916.pe.representations.idm.OrganizacionImportadaRepresentation;
import org.jboss.logging.Logger;
import org.keycloak.util.JsonSerialization;
import io.github.carlosthe19916.core.models.ModelRuntimeException;
import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.OrganizationProvider;
import io.github.carlosthe19916.core.models.utils.RepresentationToModel;
import io.github.carlosthe19916.pe.models.OrganizacionInformacionAdicionalModel;
import io.github.carlosthe19916.pe.models.OrganizacionInformacionSunatModel;
import io.github.carlosthe19916.pe.models.OrganizationInformacionAdicionalProvider;
import io.github.carlosthe19916.pe.models.OrganizationInformacionSunatProvider;
import io.github.carlosthe19916.pe.representations.idm.OrganizacionImportadaRepresentation;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Startup
@Singleton
@DependsOn("ServerBootstrap")
public class ConfigurarOrganizacionMaster {

    private static final Logger logger = Logger.getLogger(ConfigurarOrganizacionMaster.class);

    @Inject
    @ConfigurationValue("openfact.pe.organizaciones.master.import")
    private Optional<String> organizationPath;

    @Inject
    private OrganizationProvider organizationProvider;

    @Inject
    private OrganizationInformacionAdicionalProvider organizationInformacionAdicionalProvider;

    @Inject
    private OrganizationInformacionSunatProvider organizationInformacionSunatProvider;

    @PostConstruct
    private void bootstrap() {
        organizationPath.ifPresent(organizationPath -> {
            logger.info("Importando configuración en la organización Master");
            try {
                InputStream is = new FileInputStream(organizationPath);
                OrganizacionImportadaRepresentation rep = loadJson(is, OrganizacionImportadaRepresentation.class);
                importOrganization(rep);
            } catch (FileNotFoundException e) {
                throw new ModelRuntimeException("No se pudo importar la configuración en la organización Master");
            }
        });
    }

    private static <T> T loadJson(InputStream is, Class<T> type) {
        try {
            return JsonSerialization.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse json", e);
        }
    }

    private void importOrganization(OrganizacionImportadaRepresentation rep) {
        OrganizationModel organization = organizationProvider.getOrganization(rep.getId()).orElseThrow(() -> new ModelRuntimeException("Organization not found"));

        RepresentationToModel.updateOrganization(rep, organization);

        OrganizacionInformacionAdicionalModel informacionAdicional = organizationInformacionAdicionalProvider.getOrganizacionInformacionAdicional(organization.getId()).orElseThrow(() -> new ModelRuntimeException("Organization not found"));
        io.github.carlosthe19916.pe.models.utils.RepresentationToModel.modelToRepresentation(informacionAdicional, rep.getInformacionAdicional());

        OrganizacionInformacionSunatModel sunatInformacion = organizationInformacionSunatProvider.getOrganizacionInformacionSUNAT(organization.getId()).orElseThrow(() -> new ModelRuntimeException("Organization not found"));
        io.github.carlosthe19916.pe.models.utils.RepresentationToModel.modelToRepresentation(sunatInformacion, rep.getInformacionSunat());
    }

}
