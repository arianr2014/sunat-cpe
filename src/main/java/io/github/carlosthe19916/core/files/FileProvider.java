package io.github.carlosthe19916.core.files;

import java.io.File;

/**
 * This class will persist and upload (if possible) files to a storage server.
 */
public interface FileProvider {

    /**
     * Persist the file.
     *
     * @param file to be persisted.
     */
    FileModel addFile(File file) throws FileException;

    /**
     * Persist the file
     *
     * @param filename of file.
     * @param bytes    file expressed in bytes.
     */
    FileModel addFile(String filename, byte[] bytes) throws FileException;

    /**
     * @param fileName of file
     */
    boolean removeFile(String fileName);

    /**
     * @param file to be removed
     */
    boolean removeFile(FileModel file);

}
