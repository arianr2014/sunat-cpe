package io.github.carlosthe19916.core.files.filesystem;

import io.github.carlosthe19916.core.files.FileModel;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileAdapter implements FileModel {

    private File file;

    public FileAdapter(File file) {
        this.file = file;
    }

    @Override
    public String getFilename() {
        return file.getName();
    }

    @Override
    public byte[] getFileAsBytes() {
        InputStream is;
        try {
            is = new FileInputStream(file);
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public InputStream getFileStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
