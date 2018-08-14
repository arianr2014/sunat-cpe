package io.github.carlosthe19916.core.files.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Optional;

@ApplicationScoped
@ContextName("cdi-context")
public class FtpRouter extends RouteBuilder {

    private static final Logger logger = Logger.getLogger(FtpRouter.class);

    public static final String DIRECT_FTP_UPLOAD = "direct:ftpUpload";
    private static final String CAMEL_FTP_PATTERN = "ftp://{0}?username={1}&password={2}{3}";

    @Inject
    @ConfigurationValue("openfact.files.ftp.host")
    private Optional<String> host;

    @Inject
    @ConfigurationValue("openfact.files.ftp.username")
    private Optional<String> username;

    @Inject
    @ConfigurationValue("openfact.files.ftp.password")
    private Optional<String> password;

    @Inject
    @ConfigurationValue("openfact.files.ftp.params")
    private Optional<String> params;

    @Override
    public void configure() throws Exception {
        from(DIRECT_FTP_UPLOAD)
                .log("Uploading file")
                .to(createFtpUri())
                .log("Uploaded file complete.");
    }

    private String createFtpUri() {
        return MessageFormat.format(CAMEL_FTP_PATTERN,
                host.orElse("localhost:21"),
                username.orElse("admin"),
                password.orElse("admin"),
                params.orElse("?autoCreate=false&passiveMode=true&binary=true"));
    }

}
