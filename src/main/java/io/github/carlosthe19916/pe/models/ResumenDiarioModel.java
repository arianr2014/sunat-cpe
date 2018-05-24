package io.github.carlosthe19916.pe.models;

import java.util.Date;

public interface ResumenDiarioModel {

    String getId();
    Date getFechaEmision();
    Date getFechaEmisionDocumentosAsociados();

    CdrModel getCdr();
    EstadoSunatModel getEstadoSunat();

}
