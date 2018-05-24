package io.github.carlosthe19916.core.files.jpa;

import io.github.carlosthe19916.core.files.jpa.entities.FileEntity;
import io.github.carlosthe19916.core.files.FileException;
import io.github.carlosthe19916.core.files.FileModel;
import io.github.carlosthe19916.core.files.FileProviderVendor;
import io.github.carlosthe19916.core.files.jpa.entities.FileEntity;
import io.github.carlosthe19916.core.files.FileProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.JPA)
public class JpaFileProvider implements FileProvider {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public FileModel addFile(String fileName, byte[] fileBytes) throws FileException {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFilename(fileName);
            fileEntity.setFileBytes(fileBytes);
            em.persist(fileEntity);
        } catch (Throwable e) {
            throw new FileException(e);
        }

        return new FileModel(fileName, fileBytes);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public boolean removeFile(String id) {
        try {
            FileEntity fileEntity = em.find(FileEntity.class, id);
            if (fileEntity == null) return false;
            em.remove(fileEntity);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

}
