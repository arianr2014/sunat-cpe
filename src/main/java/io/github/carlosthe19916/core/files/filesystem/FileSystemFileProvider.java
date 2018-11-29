package io.github.carlosthe19916.core.files.filesystem;

import io.github.carlosthe19916.ConfigConstants;
import io.github.carlosthe19916.core.files.*;
import org.jboss.logging.Logger;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Transactional(value = Transactional.TxType.REQUIRES_NEW)
@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.FILESYSTEM)
public final class FileSystemFileProvider implements FileProvider {

    private static final Logger logger = Logger.getLogger(FileSystemFileProvider.class);

    @PersistenceContext
    private EntityManager em;

    @Inject
    @ConfigurationValue(ConfigConstants.FILESYSTEM_PROVIDER_FOLDER_NAME)
    private Optional<String> baseFolder;

    private Path buildFileName(String fileName) {
        String folderName = baseFolder.orElse(ConfigConstants.DEFAULT_FILESYSTEM_FOLDER);
        return Paths.get(folderName, fileName + ConfigConstants.FILE_SUFIX);
    }

    @Override
    public FileModel getFileById(String fileId) {
        FileAttachEntity entity = em.find(FileAttachEntity.class, fileId);
        if (entity == null) {
            return null;
        }

        Path path = buildFileName(entity.getFileLocation());
        return new FileAdapter(entity, path.toFile());
    }

    @Override
    public FileModel addFile(File file) throws FileException {
        return null;
    }

    @Override
    public FileModel addFile(String fileName, byte[] bytes) throws FileException {
        String id = UUID.randomUUID().toString();
        Path path = buildFileName(id + ConfigConstants.FILE_SUFIX);

        if (Files.exists(path)) {
            throw new FileException("File already exists");
        }
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, bytes);
            } catch (IOException e) {
                throw new FileException(e);
            }
        }

        FileAttachEntity entity = new FileAttachEntity();
        entity.setId(id);
        entity.setFileName(fileName);
        entity.setFileLocation(path.toString());
        em.persist(entity);

        return new FileAdapter(entity, path.toFile());
    }

    @Override
    public boolean removeFile(String fileId) {
        String folderName = baseFolder.orElse(ConfigConstants.DEFAULT_FILESYSTEM_FOLDER);
        Path path = Paths.get(folderName, fileId);
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
        return removeFile(file.getId());
    }

}
