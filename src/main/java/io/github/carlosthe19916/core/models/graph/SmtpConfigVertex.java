package io.github.carlosthe19916.core.models.graph;

import com.syncleus.ferma.annotations.Property;

@TypeValue(SmtpConfigVertex.TYPE)
public interface SmtpConfigVertex {

    String TYPE = "SmptConfigVertex";
    String HOST = "host";
    String PORT = "port";
    String FROM = "from";
    String SSL = "ssl";
    String START_TLS = "startTls";
    String AUTH = "auth";
    String USER = "user";
    String PASSWORD = "password";

    @Property(HOST)
    String getHost();

    @Property(HOST)
    void setHost(String host);

    @Property(PORT)
    String getPort();

    @Property(PORT)
    void setPort(String port);

    @Property(FROM)
    String getFrom();

    @Property(FROM)
    void setFrom(String from);

    @Property(SSL)
    String getSsl();

    @Property(SSL)
    void setSsl(String ssl);

    @Property(START_TLS)
    String getStartTls();

    @Property(START_TLS)
    void setStarTtls(String starTtls);

    @Property(AUTH)
    String getAuth();

    @Property(AUTH)
    void setAuth(String auth);

    @Property(USER)
    String getUser();

    @Property(AUTH)
    void setUser(String user);

    @Property(PASSWORD)
    String getPassword();

    @Property(AUTH)
    void setPassword(String password);

}
