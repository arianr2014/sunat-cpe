package io.github.carlosthe19916.core.models.graph;

import io.github.carlosthe19916.core.models.graph.vertex.SunatFrame;
import io.github.carlosthe19916.core.models.graph.vertex.TypeValue;

public class GraphTypeManager {

    /**
     * Returns the type discriminator value for given Frames model class, extracted from the @TypeValue annotation.
     */
    public static String getTypeValue(Class<? extends SunatFrame> clazz)
    {
        TypeValue typeValueAnnotation = clazz.getAnnotation(TypeValue.class);
        if (typeValueAnnotation == null)
            throw new IllegalArgumentException("Class " + clazz.getCanonicalName() + " lacks a @TypeValue annotation");

        return typeValueAnnotation.value();
    }

}
