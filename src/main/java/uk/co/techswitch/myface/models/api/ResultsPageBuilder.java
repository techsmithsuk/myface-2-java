package uk.co.techswitch.myface.models.api;

import java.util.List;

public class ResultsPageBuilder<TItem> {
    private List<TItem> items;
    private Filter filter;
    private int numberMatchingSearch;
    private String baseUrl;

    public ResultsPageBuilder<TItem> withItems(List<TItem> items) {
        this.items = items;
        return this;
    }

    public ResultsPageBuilder<TItem> withFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public ResultsPageBuilder<TItem> withNumberMatchingSearch(int numberMatchingSearch) {
        this.numberMatchingSearch = numberMatchingSearch;
        return this;
    }

    public ResultsPageBuilder<TItem> withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ResultsPage<TItem> build() {
        return new ResultsPage<>(items, filter, numberMatchingSearch, baseUrl);
    }
}
