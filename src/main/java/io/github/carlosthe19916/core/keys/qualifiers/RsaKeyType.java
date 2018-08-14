package io.github.carlosthe19916.core.keys.qualifiers;

import io.github.carlosthe19916.core.keys.GeneratedRsaKeyProviderFactory;
import io.github.carlosthe19916.core.keys.ImportedRsaKeyProviderFactory;
import io.github.carlosthe19916.core.keys.JavaKeystoreKeyProviderFactory;

import java.util.Optional;
import java.util.stream.Stream;

public enum RsaKeyType {

    /**
     * rsa-generated
     */
    GENERATED(GeneratedRsaKeyProviderFactory.ID),

    /**
     * java-keystore
     */
    JAVA_KEYSTORE(JavaKeystoreKeyProviderFactory.ID),

    /**
     * rsa
     */
    IMPORTED(ImportedRsaKeyProviderFactory.ID);

    private final String providerId;

    RsaKeyType(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public static Optional<RsaKeyType> findByProviderId(String providerId) {
        return Stream.of(RsaKeyType.values())
                .filter(p -> p.getProviderId().equals(providerId))
                .findFirst();
    }
}
