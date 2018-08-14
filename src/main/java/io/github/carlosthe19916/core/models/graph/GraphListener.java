package io.github.carlosthe19916.core.models.graph;

import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public interface GraphListener {

    void vertexAdded(Vertex vertex);

    void vertexPropertyChanged(Vertex element, Property oldValue, Object setValue, Object... vertexPropertyKeyValues);

}
