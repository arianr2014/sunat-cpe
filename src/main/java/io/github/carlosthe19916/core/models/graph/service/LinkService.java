package io.github.carlosthe19916.core.models.graph.service;

import io.github.carlosthe19916.core.models.graph.GraphContext;
import io.github.carlosthe19916.core.models.graph.vertex.LinkVertex;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.List;

public class LinkService extends GraphService<LinkVertex> {

    public LinkService(GraphContext context) {
        super(context, LinkVertex.class);
    }

    /**
     * Tries to find a link with the specified description and href. If it cannot, then it will return a new one.
     */
    public LinkVertex getOrCreate(String description, String href) {
        Iterable<Vertex> results = (List<Vertex>) getQuery().traverse(g -> g.has(LinkVertex.PROPERTY_DESCRIPTION, description).has(LinkVertex.PROPERTY_LINK, href)).getRawTraversal().toList();
        if (!results.iterator().hasNext()) {
            LinkVertex model = create();
            model.setDescription(description);
            model.setLink(href);
            return model;
        }
        return frame(results.iterator().next());
    }

}
