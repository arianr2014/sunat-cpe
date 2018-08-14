package io.github.carlosthe19916.core.models.graph;

import io.github.carlosthe19916.core.models.graph.vertex.SunatFrame;
import io.github.carlosthe19916.core.utils.exception.WindupException;

import java.util.HashMap;
import java.util.Map;

public class TypeRegistry
{
    Map<String, Class<?>> typeDiscriminators = new HashMap<>();

    public Class<?> getType(String typeName)
    {
        return typeDiscriminators.get(typeName);
    }

    /**
     * @param type Add the interface to the registry. The interface should have a {@link io.github.carlosthe19916.core.models.graph.vertex.TypeValue} annotation, and there should be a
     *            {@link TypeField} annotation on the interface or its parents.
     */
    public TypeRegistry add(Class<? extends SunatFrame> type)
    {
        String typeString = GraphTypeManager.getTypeValue(type);
        if (typeString == null) {
            throw new WindupException(String.format("The type does not have a @TypeValue annotation: %s", type.getName()));
        }
        typeDiscriminators.put(typeString, type);
        return this;
    }
}
