package io.github.carlosthe19916.pe.representations.idm;

public class SUNATGenericTypeRepresentation {

    private String id;
    private String codigo;
    private String abreviatura;
    private String denominacion;
    private String grupo;
    private Integer longitud;
    private Boolean afectaIGV;
    private Double valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Boolean getAfectaIGV() {
        return afectaIGV;
    }

    public void setAfectaIGV(Boolean afectaIGV) {
        this.afectaIGV = afectaIGV;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
