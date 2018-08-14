package io.github.carlosthe19916.core.models.graph.service;

import io.github.carlosthe19916.core.models.graph.GraphContext;
import io.github.carlosthe19916.core.models.graph.vertex.LinkVertex;
import io.github.carlosthe19916.core.models.graph.vertex.OrganizationVertex;

public class OrganizationService extends GraphService<OrganizationVertex> {
    
    public OrganizationService(GraphContext context) {
        super(context, OrganizationVertex.class);
    }
    
    public OrganizationVertex attachOrganization(String organizationName) {
        OrganizationVertex model = getUnique(getQuery().traverse(g -> g.has(OrganizationVertex.NAME, organizationName)).getRawTraversal());
        if (model == null) {
            model = create();
            model.setName(organizationName);
        }
        return model;
    }
    
    public OrganizationVertex attachLink(OrganizationVertex OrganizationVertex, LinkVertex linkModel) {
        // check for duplicates
        for (LinkVertex existing : OrganizationVertex.getLinks()) {
            if (existing.equals(linkModel)) {
                return OrganizationVertex;
            }
        }
        OrganizationVertex.addLink(linkModel);
        return OrganizationVertex;
    }
}
