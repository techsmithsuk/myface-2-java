package uk.co.techswitch.myface.models.api;

import uk.co.techswitch.myface.models.api.comments.CommentModel;
import uk.co.techswitch.myface.models.api.comments.CommentsFilter;
import uk.co.techswitch.myface.models.database.Comment;

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

    public ResultsPage<TItem, TFilter> build() {
        return new ResultsPage<>(items, filter, numberMatchingSearch, baseUrl);
    }
}
