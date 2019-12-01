package uk.co.techswitch.myface.models.api;

import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public abstract class ResultsPage<TItem, TFilter extends Filter> {
    private final List<TItem> items;
    private final TFilter filter;
    private final int totalNumberOfItems;

    public ResultsPage(List<TItem> items, TFilter filter, int totalNumberOfItems) {
        this.items = items;
        this.filter = filter;
        this.totalNumberOfItems = totalNumberOfItems;
    }

    protected abstract String getRootUrl();

    public List<TItem> getItems() {
        return items;
    }

    public int getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public URI getPreviousPage() {
        if (filter.getPage() <= 1) {
            return null;
        }

        UriBuilder builder = UriComponentsBuilder.fromPath("/api/posts")
                .queryParam("page", filter.getPage() - 1)
                .queryParam("pageSize", filter.getPageSize());

        filter.appendQueryParams(builder);
        return builder.build();
    }

    public URI getNextPage() {
        if (filter.getPage() * filter.getPageSize() >= totalNumberOfItems) {
            return null;
        }

        UriBuilder builder = UriComponentsBuilder.fromPath(getRootUrl())
                .queryParam("page", filter.getPage() + 1)
                .queryParam("pageSize", filter.getPageSize());

        filter.appendQueryParams(builder);
        return builder.build();
    }
}
