package io.github.carlosthe19916.pe.models.utils;

import io.github.carlosthe19916.pe.models.*;
import io.github.carlosthe19916.pe.models.types.TipoDocumentoEntidad;
import io.github.carlosthe19916.pe.representations.idm.*;

import java.util.stream.Collectors;

public class ModelToRepresentation {

    private ModelToRepresentation() {
        // Util Class
    }

    public static OrganizacionInformacionAdicionalRepresentation toRepresentation(OrganizacionInformacionAdicionalModel model, boolean fullInfo) {
        OrganizacionInformacionAdicionalRepresentation rep = new OrganizacionInformacionAdicionalRepresentation();

        rep.setId(model.getId());
        rep.setAssignedId(model.getAssignedId());
        rep.setAdditionalAssignedId(model.getAdditionalAssignedId());
        rep.setRazonSocial(model.getRazonSocial());
        rep.setNombreComercial(model.getNombreComercial());
        rep.setRegion(model.getRegion());
        rep.setProvincia(model.getProvincia());
        rep.setDistrito(model.getDistrito());
        rep.setCodigoPostal(model.getCodigoPostal());
        rep.setDireccion(model.getDireccion());

        return rep;
    }


    public static OrganizacionInformacionSunatRepresentation toRepresentation(OrganizacionInformacionSunatModel model, boolean fullInfo) {
        OrganizacionInformacionSunatRepresentation rep = new OrganizacionInformacionSunatRepresentation();

        rep.setId(model.getId());
        rep.setUsuario(model.getUsuario());
        if (model.getPassword() != null) {
            rep.setPassword("**********");
        }

        rep.setUseCustomConfig(model.isUseCustomConfig());

        rep.setBoletaFacturaEndpoint(model.getBoletaFacturaEndpoint());
        rep.setGuiaRemisionEndpoint(model.getGuiaRemisionEndpoint());
        rep.setRetencionPercepcionEndpoint(model.getRetencionPercepcionEndpoint());
        rep.setConsultaCdrEndpoint(model.getConsultaCdrEndpoint());
        rep.setConsultaFacturaEndpoint(model.getConsultaFacturaEndpoint());

        if (fullInfo) {

        }

        return rep;
    }

    private static ClienteRepresentation toClienteRepresentation(ClienteModel model) {
        ClienteRepresentation rep = new ClienteRepresentation();

        rep.setNombre(model.getNombre());

        rep.setTipoDocumento(model.getTipoDocumento());
        TipoDocumentoEntidad.getByCode(model.getTipoDocumento()).ifPresent((v) -> rep.setTipoDocumentoAlias(v.getAbreviatura()));

        rep.setNumeroDocumento(model.getNumeroDocumento());
        rep.setDireccion(model.getDireccion());
        rep.setEmail(model.getEmail());

        return rep;
    }

    private static FechaRepresentation toFechaRepresentation(FechaModel model) {
        FechaRepresentation rep = new FechaRepresentation();
        rep.setEmision(model.getEmision());
        rep.setVencimiento(model.getVencimiento());
        return rep;
    }

    private static TotalRepresentation toTotalRepresentation(TotalModel total) {
        TotalRepresentation rep = new TotalRepresentation();
        rep.setPagar(total.getTotalPagar());
        rep.setOtrosCargos(total.getOtrosCargos());
        rep.setDescuentoGlobal(total.getDescuentoGlobal());
        return rep;
    }

    private static MonedaRepresentation toMonedaRepresentation(MonedaModel moneda) {
        MonedaRepresentation rep = new MonedaRepresentation();
        rep.setCodigo(moneda.getMoneda());
        rep.setTipoCambio(moneda.getTipoCambio());
        return rep;
    }

    private static TotalImpuestosRepresentation toTotalImpuestosRepresentation(ImpuestosModel impuestos) {
        TotalImpuestosRepresentation rep = new TotalImpuestosRepresentation();
        rep.setIgv(impuestos.getIgv());
        rep.setIsc(impuestos.getIsc());
        return rep;
    }

    private static TotalInformacionAdicionalRepresentation toTotalInformacionAdicional(TotalInformacionAdicionalModel totalInformacionAdicional) {
        TotalInformacionAdicionalRepresentation rep = new TotalInformacionAdicionalRepresentation();
        rep.setTotalGratuito(totalInformacionAdicional.getTotalGratuito());
        rep.setTotalGravado(totalInformacionAdicional.getTotalGravado());
        rep.setTotalInafecto(totalInformacionAdicional.getTotalInafecto());
        rep.setTotalExonerado(totalInformacionAdicional.getTotalExonerado());
        return rep;
    }

    public static InvoiceRepresentation toRepresentation(InvoiceModel model, boolean fullInfo) {
        InvoiceRepresentation rep = new InvoiceRepresentation();

        DatosVentaModel datosVentaModel = model.getDatosVenta();

        rep.setId(model.getId());
        rep.setSerie(model.getSerie());
        rep.setNumero(model.getNumero());
        rep.setFecha(toFechaRepresentation(datosVentaModel.getFecha()));
        rep.setCliente(toClienteRepresentation(datosVentaModel.getCliente()));
        rep.setTotal(toTotalRepresentation(datosVentaModel.getTotal()));
        rep.setMoneda(toMonedaRepresentation(datosVentaModel.getMoneda()));
        rep.setTotalImpuestos(toTotalImpuestosRepresentation(datosVentaModel.getImpuestos()));
        rep.setTotalInformacionAdicional(toTotalInformacionAdicional(datosVentaModel.getTotalInformacionAdicional()));

        rep.setDetalle(datosVentaModel.getDetalle().stream().map(ModelToRepresentation::toRepresentation).collect(Collectors.toList()));

        EstadoSunatModel estadoSunatModel = model.getEstadoSunat();
        EstadoSunatRepresentation estadoSunatRep = new EstadoSunatRepresentation();
        estadoSunatRep.setEstado(estadoSunatModel.getEstado());
        estadoSunatRep.setCodigo(estadoSunatModel.getCodigo());
        estadoSunatRep.setDescripcion(estadoSunatModel.getDescripcion());
        rep.setValidacion(estadoSunatRep);

        if (fullInfo) {
            rep.setEnviarCliente(model.getEnviarCliente());
            rep.setEnviarSunat(model.getEnviarSunat());
        }

        return rep;
    }

    public static NotaRepresentation toRepresentation(NotaModel model, boolean fullInfo) {
        NotaRepresentation rep = new NotaRepresentation();

        DatosVentaModel datosVentaModel =  model.getDatosVenta();

        rep.setId(model.getId());
        rep.setSerie(model.getSerie());
        rep.setNumero(model.getNumero());
        rep.setFecha(toFechaRepresentation(datosVentaModel.getFecha()));
        rep.setCliente(toClienteRepresentation(datosVentaModel.getCliente()));
        rep.setTotal(toTotalRepresentation(datosVentaModel.getTotal()));
        rep.setMoneda(toMonedaRepresentation(datosVentaModel.getMoneda()));
        rep.setTotalImpuestos(toTotalImpuestosRepresentation(datosVentaModel.getImpuestos()));
        rep.setTotalInformacionAdicional(toTotalInformacionAdicional(datosVentaModel.getTotalInformacionAdicional()));

        rep.setDetalle(datosVentaModel.getDetalle().stream().map(ModelToRepresentation::toRepresentation).collect(Collectors.toList()));

        EstadoSunatModel estadoSunatModel = model.getEstadoSunat();
        EstadoSunatRepresentation estadoSunatRep = new EstadoSunatRepresentation();
        estadoSunatRep.setEstado(estadoSunatModel.getEstado());
        estadoSunatRep.setCodigo(estadoSunatModel.getCodigo());
        estadoSunatRep.setDescripcion(estadoSunatModel.getDescripcion());
        rep.setValidacion(estadoSunatRep);

        if (fullInfo) {
            rep.setEnviarCliente(model.getEnviarCliente());
            rep.setEnviarSunat(model.getEnviarSunat());
        }

        return rep;
    }

    public static BajaRepresentation toRepresentation(BajaModel model, boolean fullInfo) {
        BajaRepresentation rep = new BajaRepresentation();

        return rep;
    }

    public static ComprobanteDetalleRepresentation toRepresentation(DatosVentaDetalleModel model) {
        ComprobanteDetalleRepresentation rep = new ComprobanteDetalleRepresentation();

        rep.setCantidad(model.getCantidad());
        rep.setDescripcion(model.getDescripcion());
        rep.setPrecioUnitario(model.getPrecioUnitario());
        rep.setSubtotal(model.getSubtotal());
        rep.setTipoIgv(model.getTipoIgv());
        rep.setTotal(model.getTotal());
        rep.setTotalIgv(model.getTotalIgv());
        rep.setTotalIsc(model.getTotalIsc());
        rep.setUnidadMedida(model.getUnidadMedida());
        rep.setValorUnitario(model.getValorUnitario());

        return rep;
    }

}
