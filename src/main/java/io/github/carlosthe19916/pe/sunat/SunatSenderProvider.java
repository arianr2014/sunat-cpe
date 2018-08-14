package io.github.carlosthe19916.pe.sunat;

import io.github.carlosthe19916.pe.models.OrganizacionInformacionAdicionalModel;
import io.github.carlosthe19916.pe.models.OrganizacionInformacionSunatModel;
import pe.gob.sunat.service.StatusResponse;

public interface SunatSenderProvider {

    byte[] sendBill(OrganizacionInformacionAdicionalModel additionalInfo, OrganizacionInformacionSunatModel orgSunatInfo, String fileName, byte[] file) throws SendSunatException;

    StatusResponse getStatus(OrganizacionInformacionAdicionalModel additionalInfo, OrganizacionInformacionSunatModel orgSunatInfo, String ticket) throws SendSunatException;

    String sendSummary(OrganizacionInformacionAdicionalModel additionalInfo, OrganizacionInformacionSunatModel orgSunatInfo, String fileName, byte[] file) throws SendSunatException;

    String sendPack(String fileName, byte[] file) throws SendSunatException;

}
