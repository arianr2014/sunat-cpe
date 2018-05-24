package io.github.carlosthe19916.core.models.query;

public class MatchAllQuery implements SimpleQuery {

    @Override
    public String getQueryName() {
        return "MatchAll";
    }

}
