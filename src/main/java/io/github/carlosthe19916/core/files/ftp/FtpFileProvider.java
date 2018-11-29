package io.github.carlosthe19916.core.files.ftp;

import io.github.carlosthe19916.core.files.FileException;
import io.github.carlosthe19916.core.files.FileModel;
import io.github.carlosthe19916.core.files.FileProvider;
import io.github.carlosthe19916.core.files.FileProviderVendor;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;

@ApplicationScoped
@FileProviderVendor(type = FileProviderVendor.Type.FTP)
public class FtpFileProvider implements FileProvider {

    @Override
    public FileModel addFile(File file) throws FileException {
        return null;
    }

    @Override
    public FileModel addFile(String filename, byte[] bytes) throws FileException {
        return new FileModel(filename, bytes);
    }

    @Override
    public boolean removeFile(String fileId) {
        return false;
    }

    @Override
    public boolean removeFile(FileModel file) {
        return false;
    }

}
