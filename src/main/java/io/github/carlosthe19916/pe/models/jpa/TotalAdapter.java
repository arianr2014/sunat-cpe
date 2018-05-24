package io.github.carlosthe19916.pe.models.jpa;

import io.github.carlosthe19916.pe.models.TotalModel;
import io.github.carlosthe19916.pe.models.jpa.entities.TotalEntity;
import io.github.carlosthe19916.pe.models.TotalModel;
import io.github.carlosthe19916.pe.models.jpa.entities.TotalEntity;

import java.math.BigDecimal;

public class TotalAdapter implements TotalModel {

    private final TotalEntity total;

    public TotalAdapter(TotalEntity total) {
        this.total = total;
    }

    @Override
    public BigDecimal getTotalPagar() {
        return total.getPagar();
    }

    @Override
    public void setTotalPagar(BigDecimal pagar) {
        total.setPagar(pagar);
    }

    @Override
    public BigDecimal getDescuentoGlobal() {
        return total.getDescuento();
    }

    @Override
    public void setDescuentoGlobal(BigDecimal descuentoGlobal) {
        total.setDescuento(descuentoGlobal);
    }

    @Override
    public BigDecimal getOtrosCargos() {
        return total.getOtrosCargos();
    }

    @Override
    public void setOtrosCargos(BigDecimal otrosCargos) {
        total.setOtrosCargos(otrosCargos);
    }

}
