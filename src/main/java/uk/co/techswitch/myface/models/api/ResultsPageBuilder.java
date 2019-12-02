package uk.co.techswitch.myface.models.api;

import java.util.List;

public class ResultsPageBuilder<TItem, TFilter extends Filter> {
    private List<TItem> items;
    private TFilter filter;
    private int numberMatchingSearch;
    private String baseUrl;

    public ResultsPageBuilder<TItem, TFilter> withItems(List<TItem> items) {
        this.items = items;
        return this;
    }

    public ResultsPageBuilder<TItem, TFilter> withFilter(TFilter filter) {
        this.filter = filter;
        return this;
    }

    public ResultsPageBuilder<TItem, TFilter> withNumberMatchingSearch(int numberMatchingSearch) {
        this.numberMatchingSearch = numberMatchingSearch;
        return this;
    }

    public ResultsPageBuilder<TItem, TFilter> withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ResultsPage build() {
        return new ResultsPage<TItem, TFilter>(items, filter, numberMatchingSearch, baseUrl);
    }
}
