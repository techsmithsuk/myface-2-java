package uk.co.techswitch.myface.models.api;

import org.springframework.web.util.UriBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter {
    private List<Long> ids = new ArrayList<>();
    private Integer page = 1;
    private Integer pageSize = 10;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public abstract void appendQueryParams(UriBuilder builder);
}
