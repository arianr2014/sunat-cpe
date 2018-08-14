package io.github.carlosthe19916.core.models.graph.vertex;

import com.syncleus.ferma.annotations.Property;

@TypeValue(LinkVertex.TYPE)
public interface LinkVertex {

    String TYPE = "LinkVertex";
    String PROPERTY_LINK = "href";
    String PROPERTY_DESCRIPTION = "description";

    /**
     * The description of the link.
     */
    @Property(PROPERTY_DESCRIPTION)
    String getDescription();

    /**
     * The description of the link.
     */
    @Property(PROPERTY_DESCRIPTION)
    void setDescription(String description);

    /**
     * The Link URI itself.
     */
    @Property(PROPERTY_LINK)
    String getLink();

    /**
     * The Link URI itself.
     */
    @Property(PROPERTY_LINK)
    void setLink(String link);

}
