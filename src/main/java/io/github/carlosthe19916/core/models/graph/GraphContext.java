package io.github.carlosthe19916.core.models.graph;

import com.syncleus.ferma.Traversable;
import com.syncleus.ferma.WrappedFramedGraph;
import io.github.carlosthe19916.core.models.graph.service.Service;
import io.github.carlosthe19916.core.models.graph.vertex.SunatVertexFrame;
import org.janusgraph.core.JanusGraph;

import java.io.Closeable;
import java.nio.file.Path;
import java.util.Map;

public interface GraphContext extends Closeable {

    Path getGraphDirectory();

    JanusGraph getGraph();

    GraphContext create(boolean enableListeners);

    GraphContext load();

    WrappedFramedGraph<JanusGraph> getFramed();

    GraphTypeManager getGraphTypeManager();

    Traversable<?, ?> getQuery(Class<? extends SunatVertexFrame> kind);

    void clear();

    void setOptions(Map<String, Object> options);

    Map<String, Object> getOptionMap();

    <T extends SunatVertexFrame> Service<T> service(Class<T> clazz);

    <T extends SunatVertexFrame> T getUnique(Class<T> clazz);

    <T extends SunatVertexFrame> Iterable<T> findAll(Class<T> clazz);

    <T extends SunatVertexFrame> T create(Class<T> clazz);

    void commit();

    void registerGraphListener(GraphListener listener);
}
