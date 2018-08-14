package io.github.carlosthe19916.core.models.graph.vertex;

import com.syncleus.ferma.annotations.Adjacency;
import com.syncleus.ferma.annotations.Property;
import org.apache.tinkerpop.gremlin.structure.Direction;

import java.util.List;

@TypeValue(OrganizationVertex.TYPE)
public interface OrganizationVertex extends SunatVertexFrame{

    String TYPE = "OrganizationVertex";
    String TYPE_PREFIX = TYPE + "-";
    String NAME = TYPE_PREFIX + "name";
    String DESCRIPTION = TYPE_PREFIX + "description";
    String TIME_ZONE = TYPE_PREFIX + "timeZone";
    String USE_CUSTOM_CERTIFICATE = TYPE_PREFIX + "useCustomCertificate";
    String USE_CUSTOM_SMTP_CONFIG = TYPE_PREFIX + "useCustomSmtpConfig";
    String SMTP_CONFIG = TYPE_PREFIX + "smtpConfig";
    String LINKS = TYPE_PREFIX + "links";

    @Property(NAME)
    String getName();

    @Property(NAME)
    void setName(String name);

    @Property(DESCRIPTION)
    String getDescription();

    @Property(DESCRIPTION)
    void setDescription(String description);

    @Property(TIME_ZONE)
    String getTimeZone();

    @Property(TIME_ZONE)
    void setTimeZone(String timeZone);

    @Property(USE_CUSTOM_CERTIFICATE)
    String getUseCustomCertificate();

    @Property(USE_CUSTOM_CERTIFICATE)
    void setUseCustomCertificate(String useCustomCertificate);

    @Property(USE_CUSTOM_SMTP_CONFIG)
    String getUseCustomSmtpConfig();

    @Property(USE_CUSTOM_SMTP_CONFIG)
    void setUseCustomSmtpConfig(String useCustomSmtpConfig);

    @Adjacency(label = SMTP_CONFIG, direction = Direction.OUT)
    SmtpConfigVertex getSmtpConfig();

    @Adjacency(label = SMTP_CONFIG, direction = Direction.OUT)
    void addSmtpConfig(SmtpConfigVertex smtpConfig);

    @Adjacency(label = LINKS, direction = Direction.OUT)
    List<LinkVertex> getLinks();

    @Adjacency(label = LINKS, direction = Direction.OUT)
    void addLink(LinkVertex linkDecorator);

}
