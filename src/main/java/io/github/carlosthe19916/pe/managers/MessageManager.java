package io.github.carlosthe19916.pe.managers;

import io.github.carlosthe19916.pe.models.BajaModel;
import io.github.carlosthe19916.pe.models.InvoiceModel;
import io.github.carlosthe19916.pe.models.NotaModel;
import io.github.carlosthe19916.pe.models.BajaModel;
import io.github.carlosthe19916.pe.models.InvoiceModel;
import io.github.carlosthe19916.pe.models.NotaModel;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MessageManager {

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/topic/my-topic")
    private Topic topic;


    public void enviarInvoice(InvoiceModel invoice) {

    }

    public void enviarNota(NotaModel nota) {

    }

    public void enviarBaja(BajaModel baja) {


    }
}
