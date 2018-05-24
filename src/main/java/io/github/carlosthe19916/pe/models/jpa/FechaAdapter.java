package io.github.carlosthe19916.pe.models.jpa;

import io.github.carlosthe19916.pe.models.FechaModel;
import io.github.carlosthe19916.pe.models.jpa.entities.FechaEntity;
import io.github.carlosthe19916.pe.models.FechaModel;
import io.github.carlosthe19916.pe.models.jpa.entities.FechaEntity;

import java.util.Date;

public class FechaAdapter implements FechaModel {

    private final FechaEntity fecha;

    public FechaAdapter(FechaEntity fecha) {
        this.fecha = fecha;
    }

    @Override
    public Date getEmision() {
        return fecha.getEmision();
    }

    @Override
    public void setEmision(Date emision) {
        fecha.setEmision(emision);
    }

    @Override
    public Date getVencimiento() {
        return fecha.getVencimiento();
    }

    @Override
    public void setVencimiento(Date vencimiento) {
        fecha.setVencimiento(vencimiento);
    }

}
