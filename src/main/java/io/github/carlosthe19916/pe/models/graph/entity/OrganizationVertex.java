package io.github.carlosthe19916.pe.models.graph.entity;

@TypeValue(OrganizationVertex.TYPE)
public interface OrganizationVertex {

    String TYPE = "OrganizationModel";
    String TYPE_PREFIX = TYPE + "-";
    String NAME = TYPE_PREFIX + "name";
    String LINKS = TYPE_PREFIX + "links";
    String ARCHIVE_MODEL = TYPE_PREFIX + "organizationModelToArchiveModel";

    String getName();

    void setName(String name);

}
