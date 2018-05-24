package io.github.carlosthe19916.pe.representations.idm;

import io.github.carlosthe19916.core.representations.idm.OrganizationRepresentation;
import io.github.carlosthe19916.core.representations.idm.OrganizationRepresentation;

public class OrganizacionImportadaRepresentation extends OrganizationRepresentation {

    private OrganizacionInformacionAdicionalRepresentation informacionAdicional;
    private OrganizacionInformacionSunatRepresentation informacionSunat;

    public OrganizacionInformacionAdicionalRepresentation getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(OrganizacionInformacionAdicionalRepresentation informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public OrganizacionInformacionSunatRepresentation getInformacionSunat() {
        return informacionSunat;
    }

    public void setInformacionSunat(OrganizacionInformacionSunatRepresentation informacionSunat) {
        this.informacionSunat = informacionSunat;
    }

}
