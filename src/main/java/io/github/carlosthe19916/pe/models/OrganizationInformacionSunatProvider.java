package io.github.carlosthe19916.pe.models;

import io.github.carlosthe19916.core.models.OrganizationModel;

import java.util.Optional;

public interface OrganizationInformacionSunatProvider {

    Optional<OrganizacionInformacionSunatModel> getOrganizacionInformacionSUNAT(String id);

    Optional<OrganizacionInformacionSunatModel> getOrganizacionInformacionSunat(OrganizationModel organization);

}
