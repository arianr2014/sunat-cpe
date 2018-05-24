package io.github.carlosthe19916.core.utils.files;

import io.github.carlosthe19916.core.utils.files.exceptions.NotReadableCompressFileException;
import io.github.carlosthe19916.core.utils.files.exceptions.NotReadableCompressFileException;

import java.util.Map;

public interface UncompressFileProvider {

    String getSupportedExtension();

    Map<String, byte[]> uncompress(byte[] bytes) throws NotReadableCompressFileException;

}
