package io.github.carlosthe19916.core.files;

import io.github.carlosthe19916.ConfigConstants;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

/**
 * This class will produce the right implementation of FileProvider
 */
public class FileProviderFactory {

    @Produces
    @ApplicationScoped
    public FileProvider provideSecurityContext(
            @ConfigurationValue(ConfigConstants.FILE_PROVIDER_NAME) Optional<String> fileProvider,
            @FileProviderVendor(type = FileProviderVendor.Type.FILESYSTEM) FileProvider fsProvider,
            @FileProviderVendor(type = FileProviderVendor.Type.FTP) FileProvider ftpProvider,
            @FileProviderVendor(type = FileProviderVendor.Type.JPA) FileProvider jpaProvider
    ) {
        String selectedFileProvider = fileProvider.orElse(FileProviderVendor.Type.FILESYSTEM.toString()).trim().toUpperCase();

        FileProviderVendor.Type selectedFileVendorProvider = FileProviderVendor.Type.valueOf(selectedFileProvider);
        if (FileProviderVendor.Type.FILESYSTEM.equals(selectedFileVendorProvider)) {
            return fsProvider;
        } else if (FileProviderVendor.Type.FTP.equals(selectedFileVendorProvider)) {
            return ftpProvider;
        }
        if (FileProviderVendor.Type.JPA.equals(selectedFileVendorProvider)) {
            return jpaProvider;
        } else {
            throw new UnknownFileProviderTypeException(selectedFileVendorProvider);
        }
    }

    public static class UnknownFileProviderTypeException extends RuntimeException {
        UnknownFileProviderTypeException(FileProviderVendor.Type securityContextType) {
            super("Unknown security context type:" + securityContextType);
        }
    }

}
