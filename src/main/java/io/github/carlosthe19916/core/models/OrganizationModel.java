package io.github.carlosthe19916.core.models;

import java.util.Map;
import java.util.TimeZone;

public interface OrganizationModel extends Model {

    String MASTER_ID = "master";

    OrganizationType getType();

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

    boolean getUseCustomCertificates();
    void setUseCustomCertificates(boolean useCustomCertificates);

    boolean getUseCustomSmtpConfig();
    void setUseCustomSmtpConfig(boolean useCustomSmtpConfig);

    Map<String, String> getSmtpConfig();
    void setSmtpConfig(Map<String, String> smtpConfig);

    TimeZone getTimeZone();
    void setTimeZone(TimeZone timeZone);

    String getAssignedId();
    void setAssignedId(String assignedId);

    String getAdditionalAssignedId();
    void setAdditionalAssignedId(String additionalAssignedId);

    String getPartyName();
    void setPartyName(String partyName);

    String getPartyLegalRegistrationName();
    void setPartyLegalRegistrationName(String partyLegalRegistrationName);

    String getPostalAddressID();
    void setPostalAddressID(String postalAddressID);

    String getPostalAddressStreetName();
    void setPostalAddressStreetName(String postalAddressStreetName);

    String getPostalAddressCitySubdivisionName();
    void setPostalAddressCitySubdivisionName(String postalAddressCitySubDivisionName);

    String getPostalAddressCityName();
    void setPostalAddressCityName(String postalAddressCityName);

    String getPostalAddressCountrySubentity();
    void setPostalAddressCountrySubentity(String postalAddressCountrySubEntity);

    String getPostalAddressCountryIdentificationCode();
    void setPostalAddressCountryIdentificationCode(String postalAddressCountryIdentificationCode);

}
