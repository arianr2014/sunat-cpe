package io.github.carlosthe19916.core.models.graph;

import com.syncleus.ferma.ClassInitializer;
import com.syncleus.ferma.ElementFrame;
import io.github.carlosthe19916.core.models.graph.frames.FrameBooleanDefaultValue;
import io.github.carlosthe19916.core.models.graph.vertex.SunatFrame;
import org.apache.tinkerpop.gremlin.structure.Element;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DefaultValueInitializer implements ClassInitializer {

    private Map<Class<?>, LinkedList<PropertyDefaultValue>> cachedValues = new HashMap<>();

    @Override
    public Class getInitializationType() {
        return SunatFrame.class;
    }

    @Override
    public void initalize(Object frame) {
        Class<?> kind = frame.getClass();
        ElementFrame elementFrame = (ElementFrame) frame;

        if (!cachedValues.containsKey(kind)) {
            cacheFrameInterface(kind, kind);
        }
        setupDefaults(elementFrame.getElement(), cachedValues.get(kind));
    }

    private void cacheFrameInterface(Class<?> originalKind, Class<?> kind) {
        if (kind == null) {
            return;
        }

        cacheFrameInterface(originalKind, kind.getSuperclass());
        for (Class<?> iface : kind.getInterfaces()) {
            cacheFrameInterface(originalKind, iface);
        }


        LinkedList<PropertyDefaultValue> values = cachedValues.get(originalKind);
        if (values == null)
            values = new LinkedList<>();

        for (Method m : kind.getMethods()) {
            Annotation[] annotations = m.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof FrameBooleanDefaultValue) {
                    PropertyDefaultValue pDefault = new PropertyDefaultValue();
                    pDefault.value = ((FrameBooleanDefaultValue) annotation).value();
                    pDefault.key = m.getAnnotation(Property.class).value();
                    values.add(pDefault);
                }
            }
        }

        cachedValues.put(originalKind, values);
    }

    private void setupDefaults(Element element, LinkedList<PropertyDefaultValue> values) {
        for (PropertyDefaultValue pValue : values) {
            element.property(pValue.key, pValue.value);
        }
    }

    private class PropertyDefaultValue {
        private String key;
        private Object value;
    }

}
