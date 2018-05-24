package io.github.carlosthe19916.core.files;

public interface FileProvider {

    FileModel addFile(String filename, byte[] bytes) throws FileException;

    boolean removeFile(String id);

}
