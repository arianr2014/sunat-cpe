package io.github.carlosthe19916.core.files;

import java.io.File;
import java.io.InputStream;

/**
 * Interface that describe a file persisted in one of the available storage supported.
 */
public interface FileModel {

    /**
     * @return the filename
     */
    String getFilename();

    /**
     * Returns the file in bytes. Be careful with this method because it could consume too much memory.
     * In case of large files use getFileStream()
     *
     * @return the file expressed in bytes.
     */
    byte[] getFileAsBytes();

    /**
     * @return a stream of the file.
     */
    InputStream getFileStream();

}
