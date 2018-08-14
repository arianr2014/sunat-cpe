package io.github.carlosthe19916.core.models.jpa.entities;

import io.github.carlosthe19916.core.models.OrganizationType;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import io.github.carlosthe19916.core.models.OrganizationType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
//@Cacheable
@Table(name = "organization", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@NamedQueries(value = {
        @NamedQuery(name = "ListOrganizations", query = "select o from OrganizationEntity o"),
        @NamedQuery(name = "FilterOrganizations", query = "select o from OrganizationEntity o where lower(o.name) like :filterText"),
})
public class OrganizationEntity implements Serializable {

    @Id
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
    private String id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private OrganizationType type;

    @NotNull
    @NaturalId(mutable = true)
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "time_zone")
    private String timeZone;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "use_custom_certificates")
    private boolean useCustomCertificates;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "use_custom_smtp_config")
    private boolean useCustomSmtpConfig;

    //@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "NAME")
    @Column(name = "VALUE")
    @CollectionTable(name = "organization_smtp_config", joinColumns = {@JoinColumn(name = "organization_id")})
    private Map<String, String> smtpConfig = new HashMap<>();

    @Column(name = "assigned_id")
    private String assignedId;

    @Column(name = "additional_assigned_id")
    private String additionalAssignedId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "party_legal_registration_name")
    private String partyLegalRegistrationName;

    @Column(name = "postal_address_id")
    private String postalAddressID;

    @Column(name = "postal_address_street_name")
    private String postalAddressStreetName;

    @Column(name = "postal_address_city_subdivison_name")
    private String postalAddressCitySubdivisionName;

    @Column(name = "postal_address_city_name")
    private String postalAddressCityName;

    @Column(name = "postal_address_country_subentity")
    private String postalAddressCountrySubentity;

    @Column(name = "postal_address_country_identification_code")
    private String postalAddressCountryIdentificationCode;

    @Version
    @Column(name = "version")
    private int version;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrganizationEntity)) {
            return false;
        }
        OrganizationEntity other = (OrganizationEntity) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public Map<String, String> getSmtpConfig() {
        return smtpConfig;
    }

    public void setSmtpConfig(Map<String, String> smtpConfig) {
        this.smtpConfig = smtpConfig;
    }

    public boolean isUseCustomSmtpConfig() {
        return useCustomSmtpConfig;
    }

    public void setUseCustomSmtpConfig(boolean useAdminSmtpConfig) {
        this.useCustomSmtpConfig = useAdminSmtpConfig;
    }

    public boolean isUseCustomCertificates() {
        return useCustomCertificates;
    }

    public void setUseCustomCertificates(boolean useCustomCertificates) {
        this.useCustomCertificates = useCustomCertificates;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public String getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(String assignedId) {
        this.assignedId = assignedId;
    }

    public String getAdditionalAssignedId() {
        return additionalAssignedId;
    }

    public void setAdditionalAssignedId(String additionalAssignedId) {
        this.additionalAssignedId = additionalAssignedId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyLegalRegistrationName() {
        return partyLegalRegistrationName;
    }

    public void setPartyLegalRegistrationName(String partyLegalRegistrationName) {
        this.partyLegalRegistrationName = partyLegalRegistrationName;
    }

    public String getPostalAddressID() {
        return postalAddressID;
    }

    public void setPostalAddressID(String postalAddressID) {
        this.postalAddressID = postalAddressID;
    }

    public String getPostalAddressStreetName() {
        return postalAddressStreetName;
    }

    public void setPostalAddressStreetName(String postalAddressStreetName) {
        this.postalAddressStreetName = postalAddressStreetName;
    }

    public String getPostalAddressCitySubdivisionName() {
        return postalAddressCitySubdivisionName;
    }

    public void setPostalAddressCitySubdivisionName(String postalAddressCitySubdivisionName) {
        this.postalAddressCitySubdivisionName = postalAddressCitySubdivisionName;
    }

    public String getPostalAddressCityName() {
        return postalAddressCityName;
    }

    public void setPostalAddressCityName(String postalAddressCityName) {
        this.postalAddressCityName = postalAddressCityName;
    }

    public String getPostalAddressCountrySubentity() {
        return postalAddressCountrySubentity;
    }

    public void setPostalAddressCountrySubentity(String postalAddressCountrySubentity) {
        this.postalAddressCountrySubentity = postalAddressCountrySubentity;
    }

    public String getPostalAddressCountryIdentificationCode() {
        return postalAddressCountryIdentificationCode;
    }

    public void setPostalAddressCountryIdentificationCode(String postalAddressCountryIdentificationCode) {
        this.postalAddressCountryIdentificationCode = postalAddressCountryIdentificationCode;
    }
}