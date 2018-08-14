package io.github.carlosthe19916.core.keys.component.utils;

import io.github.carlosthe19916.core.keys.qualifiers.RsaKeyProviderType;
import io.github.carlosthe19916.core.keys.qualifiers.RsaKeyType;

import javax.enterprise.util.AnnotationLiteral;

public class RsaKeyProviderLiteral extends AnnotationLiteral<RsaKeyProviderType> implements RsaKeyProviderType {

    private final RsaKeyType type;

    public RsaKeyProviderLiteral(RsaKeyType type) {
        this.type = type;
    }

    @Override
    public RsaKeyType type() {
        return type;
    }

}
