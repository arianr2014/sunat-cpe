package io.github.carlosthe19916.core.files.filesystem;

import io.github.carlosthe19916.ConfigConstants;
import io.github.carlosthe19916.core.files.FileException;
import io.github.carlosthe19916.core.files.FileModel;
import io.github.carlosthe19916.core.files.FileProvider;
import io.github.carlosthe19916.core.files.FileProviderVendor;
import org.apache.camel.CamelContext;
import org.apache.camel.cdi.ContextName;
import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.FILESYSTEM)
public class FileSystemFileProvider implements FileProvider {

    private static final Logger logger = Logger.getLogger(FileSystemFileProvider.class);

    @Inject
    @ContextName("cdi-context")
    private CamelContext camelContext;

    @Inject
    @ConfigurationValue(ConfigConstants.FILESYSTEM_PROVIDER_FOLDER_NAME)
    private Optional<String> folder;

    @Override
    public FileModel addFile(File file) throws FileException {
        return null;
    }

    @Override
    public FileModel addFile(String fileName, byte[] bytes) throws FileException {
        String folderName = folder.orElse(ConfigConstants.DEFAULT_FILESYSTEM_FOLDER);
        Path path = Paths.get(folderName, fileName);
        if (Files.exists(path)) {
            throw new FileException("File already exists");
        }

        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new FileException(e);
            }
        }

        try {
            Files.write(path, bytes);
        } catch (Throwable e) {
            throw new FileException(e);
        }

        return new FileAdapter(path.toFile());
    }

    @Override
    public boolean removeFile(String fileName) {
        String folderName = folder.orElse(ConfigConstants.DEFAULT_FILESYSTEM_FOLDER);
        Path path = Paths.get(folderName, fileName);
        try {
            Files.delete(path);
            return true;
        } catch (Throwable e) {
            logger.errorf("Could not delete the file %s", path.toString());
            return false;
        }
    }

    @Override
    public boolean removeFile(FileModel file) {
        return false;
    }

}
