package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.ModelType;
import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.OrganizationType;
import io.github.carlosthe19916.core.models.jpa.entities.OrganizationEntity;
import io.github.carlosthe19916.core.models.ModelType;
import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.OrganizationType;
import io.github.carlosthe19916.core.models.jpa.entities.OrganizationEntity;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class OrganizationAdapter implements OrganizationModel, JpaModel<OrganizationEntity> {

    private final OrganizationEntity organization;

    public OrganizationAdapter(OrganizationEntity organization) {
        this.organization = organization;
    }

    public static OrganizationEntity toEntity(OrganizationModel model, EntityManager em) {
        if (model instanceof OrganizationAdapter) {
            return ((OrganizationAdapter) model).getEntity();
        }
        return em.getReference(OrganizationEntity.class, model.getId());
    }

    @Override
    public OrganizationEntity getEntity() {
        return organization;
    }

    @Override
    public String getId() {
        return organization.getId();
    }

    @Override
    public ModelType getModelType() {
        return ModelType.ORGANIZATION;
    }

    @Override
    public OrganizationType getType() {
        return organization.getType();
    }

    @Override
    public String getName() {
        return organization.getName();
    }

    @Override
    public void setName(String name) {
        organization.setName(name);
    }

    @Override
    public String getDescription() {
        return organization.getDescription();
    }

    @Override
    public void setDescription(String description) {
        organization.setDescription(description);
    }

    @Override
    public boolean getUseCustomCertificates() {
        return organization.isUseCustomCertificates();
    }

    @Override
    public void setUseCustomCertificates(boolean useCustomCertificates) {
        organization.setUseCustomCertificates(useCustomCertificates);
    }

    @Override
    public boolean getUseCustomSmtpConfig() {
        return organization.isUseCustomSmtpConfig();
    }

    @Override
    public void setUseCustomSmtpConfig(boolean useCustomSmtpConfig) {
        organization.setUseCustomSmtpConfig(useCustomSmtpConfig);
    }

    @Override
    public Map<String, String> getSmtpConfig() {
        Map<String, String> config = new HashMap<>();
        config.putAll(organization.getSmtpConfig());
        return Collections.unmodifiableMap(config);
    }

    @Override
    public void setSmtpConfig(Map<String, String> smtpConfig) {
        organization.setSmtpConfig(smtpConfig);
    }

    @Override
    public TimeZone getTimeZone() {
        return organization.getTimeZone() != null ? TimeZone.getTimeZone(organization.getTimeZone()) : null;
    }

    @Override
    public void setTimeZone(TimeZone timeZone) {
        organization.setTimeZone(timeZone.getID());
    }

    @Override
    public String getAssignedId() {
        return organization.getAssignedId();
    }

    @Override
    public void setAssignedId(String assignedId) {
        organization.setAssignedId(assignedId);
    }

    @Override
    public String getAdditionalAssignedId() {
        return organization.getAdditionalAssignedId();
    }

    @Override
    public void setAdditionalAssignedId(String additionalAssignedId) {
        organization.setAdditionalAssignedId(additionalAssignedId);
    }

    @Override
    public String getPartyName() {
        return organization.getPartyName();
    }

    @Override
    public void setPartyName(String partyName) {
        organization.setPartyName(partyName);
    }

    @Override
    public String getPartyLegalRegistrationName() {
        return organization.getPartyLegalRegistrationName();
    }

    @Override
    public void setPartyLegalRegistrationName(String partyLegalRegistrationName) {
        organization.setPartyLegalRegistrationName(partyLegalRegistrationName);
    }

    @Override
    public String getPostalAddressID() {
        return organization.getPostalAddressID();
    }

    @Override
    public void setPostalAddressID(String postalAddressID) {
        organization.setPostalAddressID(postalAddressID);
    }

    @Override
    public String getPostalAddressStreetName() {
        return organization.getPostalAddressStreetName();
    }

    @Override
    public void setPostalAddressStreetName(String postalAddressStreetName) {
        organization.setPostalAddressStreetName(postalAddressStreetName);
    }

    @Override
    public String getPostalAddressCitySubdivisionName() {
        return organization.getPostalAddressCitySubdivisionName();
    }

    @Override
    public void setPostalAddressCitySubdivisionName(String postalAddressCitySubdivisionName) {
        organization.setPostalAddressCitySubdivisionName(postalAddressCitySubdivisionName);
    }

    @Override
    public String getPostalAddressCityName() {
        return organization.getPostalAddressCityName();
    }

    @Override
    public void setPostalAddressCityName(String postalAddressCityName) {
        organization.setPostalAddressCityName(postalAddressCityName);
    }

    @Override
    public String getPostalAddressCountrySubentity() {
        return organization.getPostalAddressCountrySubentity();
    }

    @Override
    public void setPostalAddressCountrySubentity(String postalAddressCountrySubEntity) {
        organization.setPostalAddressCountrySubentity(postalAddressCountrySubEntity);
    }

    @Override
    public String getPostalAddressCountryIdentificationCode() {
        return organization.getPostalAddressCountryIdentificationCode();
    }

    @Override
    public void setPostalAddressCountryIdentificationCode(String postalAddressCountryIdentificationCode) {
        organization.setPostalAddressCountryIdentificationCode(postalAddressCountryIdentificationCode);
    }
}
