package io.github.carlosthe19916.core.models.graph.vertex;

import com.syncleus.ferma.VertexFrame;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 * The base {@link SunatVertexFrame} type implemented by all model types.
 */
public interface SunatVertexFrame extends VertexFrame, SunatFrame<Vertex> {
}
