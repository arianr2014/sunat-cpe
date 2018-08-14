package io.github.carlosthe19916.core.models.graph.vertex;

import com.syncleus.ferma.ElementFrame;
import com.syncleus.ferma.WrappedFramedGraph;
import io.github.carlosthe19916.core.models.graph.DefaultValueInitializer;
import io.github.carlosthe19916.core.models.graph.JavaHandler;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.janusgraph.core.JanusGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface SunatFrame<T extends Element> extends ElementFrame {
    /**
     * Name of the property where vertex/frame types are stored.
     *
     * @see io.github.carlosthe19916.core.models.graph.GraphTypeManager
     */
    String TYPE_PROP = "w:sunattype";

    @JavaHandler(handler = Impl.class)
    String toString();

    @JavaHandler(handler = Impl.class)
    void init();

    @JavaHandler(handler = Impl.class)
    @Override
    boolean equals(Object other);

    /**
     * Gets the wrapped graph itself, allowing access to the underlying JanusGraph for raw queries.
     */
    default WrappedFramedGraph<JanusGraph> getWrappedGraph()
    {
        return (WrappedFramedGraph<JanusGraph>)getGraph();
    }

    /**
     * A string representation of this vertex, showing it's properties in a JSON-like format.
     */
    default String toPrettyString()
    {
        Element v = getElement();
        StringBuilder result = new StringBuilder();
        result.append("[").append(v.toString()).append("=");
        result.append("{");

        boolean hasSome = false;
        for (String propKey : v.keys())
        {
            hasSome = true;
            Iterator<? extends Property<Object>> propVal = v.properties(propKey);
            List<Object> propValues = new ArrayList<>();
            propVal.forEachRemaining(prop -> propValues.add(prop.value()));

            if (propValues.size() == 1)
                result.append(propKey).append(": ").append(propValues.get(0));
            else
                result.append(propKey).append(": ").append(propValues);

            result.append(", ");
        }

        if (hasSome)
        {
            result.delete(result.length() - 2, result.length());
        }

        result.append("}]");
        return result.toString();
    }

    class Impl {
        public String toString(ElementFrame frame)
        {
            if (frame instanceof SunatFrame)
                return ((SunatFrame) frame).toPrettyString();
            else
                return frame.toString();
        }

        public void init(ElementFrame frame)
        {
            new DefaultValueInitializer().initalize(frame);
        }

        public boolean equals (ElementFrame thiz, Object o)
        {
            Element element;
            if (o instanceof Element)
                element = (Element)o;
            else if (o instanceof ElementFrame)
                element = ((ElementFrame) o).getElement();
            else
                return false;

            return thiz.getElement().equals(element);
        }
    }
}
