package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.FacetModel;
import io.github.carlosthe19916.core.models.SearchResultModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EmptySearchResultAdapter<T> implements SearchResultModel<T> {

    @Override
    public List<T> getItems() {
        return Collections.emptyList();
    }

    @Override
    public int getTotalResults() {
        return 0;
    }

    @Override
    public Map<String, List<FacetModel>> getFacets() {
        return Collections.emptyMap();
    }

}
