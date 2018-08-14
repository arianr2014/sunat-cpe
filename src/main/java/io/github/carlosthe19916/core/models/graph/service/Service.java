package io.github.carlosthe19916.core.models.graph.service;

import io.github.carlosthe19916.core.models.graph.service.exception.NonUniqueResultException;
import io.github.carlosthe19916.core.models.graph.vertex.SunatVertexFrame;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.List;

public interface Service<FRAMETYPE extends SunatVertexFrame> {

    void commit();

    FRAMETYPE create();
    FRAMETYPE addTypeToModel(SunatVertexFrame model);

    void remove(FRAMETYPE model);

    List<FRAMETYPE> findAll();

    Iterable<FRAMETYPE> findAllByProperties(String[] keys, String[] vals);
    Iterable<FRAMETYPE> findAllByProperty(String key, Object value);
    Iterable<FRAMETYPE> findAllWithoutProperty(final String key, final Object value);
    Iterable<FRAMETYPE> findAllWithoutProperty(final String key);
    Iterable<FRAMETYPE> findAllByPropertyMatchingRegex(String key, String... regex);

    FRAMETYPE getById(Object id);
    FRAMETYPE getUnique() throws NonUniqueResultException;

    FRAMETYPE getUniqueByProperty(String property, Object value) throws NonUniqueResultException;

    Transaction newTransaction();

    Class<FRAMETYPE> getType();

    FRAMETYPE frame(Vertex vertex);

}
