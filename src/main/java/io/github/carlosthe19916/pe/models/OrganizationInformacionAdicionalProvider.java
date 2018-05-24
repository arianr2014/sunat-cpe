package io.github.carlosthe19916.pe.models;

import io.github.carlosthe19916.core.models.OrganizationModel;

import java.util.Optional;

public interface OrganizationInformacionAdicionalProvider {

    Optional<OrganizacionInformacionAdicionalModel> getOrganizacionInformacionAdicional(String id);

    Optional<OrganizacionInformacionAdicionalModel> getOrganizacionInformacionAdicional(OrganizationModel organization);

}
