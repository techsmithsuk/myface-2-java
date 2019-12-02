package uk.co.techswitch.myface.models.api;

import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class ResultsPage<TItem, TFilter extends Filter> {
    private final List<TItem> items;
    private final TFilter filter;
    private final int numberMatchingSearch;
    private final String rootUrl;

    public ResultsPage(List<TItem> items, TFilter filter, int numberMatchingSearch, String rootUrl) {
        this.items = items;
        this.filter = filter;
        this.numberMatchingSearch = numberMatchingSearch;
        this.rootUrl = rootUrl;
    }

    public List<TItem> getItems() {
        return items;
    }

    public int getNumberMatchingSearch() {
        return numberMatchingSearch;
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
        if (filter.getPage() * filter.getPageSize() >= numberMatchingSearch) {
            return null;
        }

        UriBuilder builder = UriComponentsBuilder.fromPath(rootUrl)
                .queryParam("page", filter.getPage() + 1)
                .queryParam("pageSize", filter.getPageSize());

        if (!filter.getIds().isEmpty()) {
            builder.queryParam("id", filter.getIds().toArray());
        }

        filter.appendQueryParams(builder);
        return builder.build();
    }
}
