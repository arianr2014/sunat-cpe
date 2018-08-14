package io.github.carlosthe19916.core.models.graph;

import io.github.carlosthe19916.core.models.graph.vertex.SunatFrame;
import io.github.carlosthe19916.core.models.graph.vertex.SunatVertexFrame;
import io.github.carlosthe19916.core.models.graph.vertex.TypeValue;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraphEdge;

import java.util.*;

public class GraphTypeManager {

    /**
     * Returns the type discriminator value for given Frames model class, extracted from the @TypeValue annotation.
     */
    public static String getTypeValue(Class<? extends SunatFrame> clazz) {
        TypeValue typeValueAnnotation = clazz.getAnnotation(TypeValue.class);
        if (typeValueAnnotation == null)
            throw new IllegalArgumentException("Class " + clazz.getCanonicalName() + " lacks a @TypeValue annotation");

        return typeValueAnnotation.value();
    }

    public void addTypeToElement(Class<? extends SunatFrame<?>> kind, Element element) {
        TypeValue typeValueAnnotation = kind.getAnnotation(TypeValue.class);
        if (typeValueAnnotation == null)
            return;

        String typeValue = typeValueAnnotation.value();

        Set<String> types = getTypeProperties(element);

        // LOG.info("Adding type to element: " + element + " type: " + kind + " property is already present? " + types);
        for (String typePropertyValue : types) {
            if (typePropertyValue.equals(typeValue)) {
                // this is already in the list, so just exit now
                return;
            }
        }

        addProperty(element, SunatVertexFrame.TYPE_PROP, typeValue);
        addSuperclassType(kind, element);
    }

    private static Set<String> getTypeProperties(Element abstractElement) {
        Set<String> results = new HashSet<>();
        Iterator<? extends org.apache.tinkerpop.gremlin.structure.Property> properties = null;
        if (abstractElement instanceof Vertex) {
            // LOG.info("Getting from standardvertex as properties method");
            properties = ((Vertex) abstractElement).properties(SunatFrame.TYPE_PROP);
        } else if (abstractElement instanceof JanusGraphEdge) {
            Property<String> typeProperty = abstractElement.property(SunatFrame.TYPE_PROP);
            if (typeProperty.isPresent()) {
                List<String> all = Arrays.asList(((String) typeProperty.value()).split("\\|"));
                results.addAll(all);
                return results;
            }
        } else {
            // LOG.info("Using the old style properties method");
            properties = Collections.singleton(abstractElement.property(SunatFrame.TYPE_PROP)).iterator();
        }

        if (properties == null)
            return results;

        properties.forEachRemaining(property -> {
            if (property.isPresent())
                results.add((String) property.value());
        });
        return results;
    }

    private void addProperty(Element abstractElement, String propertyName, String propertyValue) {
        // This uses the direct Titan API which is indexed. See GraphContextImpl.
        if (abstractElement instanceof Vertex)
            ((Vertex) abstractElement).property(propertyName, propertyValue);
            // StandardEdge doesn't have addProperty().
        else if (abstractElement instanceof Edge)
            addTokenProperty(abstractElement, propertyName, propertyValue);
            // For all others, we resort to storing a list
        else {
            Property<List<String>> property = abstractElement.property(propertyName);
            if (property == null) {
                abstractElement.property(propertyName, Collections.singletonList(propertyValue));
            } else {
                List<String> existingList = property.value();
                List<String> newList = new ArrayList<>(existingList);
                newList.add(propertyValue);
                abstractElement.property(propertyName, newList);
            }
        }
    }

    private void addSuperclassType(Class<? extends SunatFrame<?>> kind, Element element) {
        for (Class<?> superInterface : kind.getInterfaces()) {
            if (SunatFrame.class.isAssignableFrom(superInterface)) {
                addTypeToElement((Class<? extends SunatFrame<?>>) superInterface, element);
            }
        }
    }

    private void addTokenProperty(Element el, String propertyName, String propertyValue) {
        Property<String> val = el.property(propertyName);
        if (!val.isPresent())
            el.property(propertyName, propertyValue);
        else
            el.property(propertyName, val.value() + "|" + propertyValue);
    }

    public void removeTypeFromElement(Class<? extends SunatFrame<?>> kind, Element element) {
        TypeValue typeValueAnnotation = kind.getAnnotation(TypeValue.class);
        if (typeValueAnnotation == null)
            return;
        String typeValue = typeValueAnnotation.value();

        List<String> newTypes = new ArrayList<>();
        for (String existingType : getTypeProperties(element)) {
            if (!existingType.equals(typeValue)) {
                newTypes.add(typeValue);
            }
        }
        element.properties(SunatFrame.TYPE_PROP).forEachRemaining(Property::remove);
        for (String newType : newTypes)
            addProperty(element, SunatFrame.TYPE_PROP, newType);

        addSuperclassType(kind, element);
    }

}
