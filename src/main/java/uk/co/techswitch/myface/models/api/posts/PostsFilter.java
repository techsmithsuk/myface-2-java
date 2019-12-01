package uk.co.techswitch.myface.models.api.posts;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.util.UriBuilder;
import uk.co.techswitch.myface.models.api.Filter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostsFilter extends Filter {
    private Long sender;
    private Date before;
    private Date after;

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Date getBefore() {
        return before;
    }

    @DateTimeFormat(pattern = PostModel.DATE_PATTERN)
    public void setBefore(Date before) {
        this.before = before;
    }

    public Date getAfter() {
        return after;
    }

    @DateTimeFormat(pattern = PostModel.DATE_PATTERN)
    public void setAfter(Date after) {
        this.after = after;
    }

    @Override
    public void appendQueryParams(UriBuilder builder) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (sender != null) {
            builder.queryParam("sender", sender);
        }

        if (before != null) {
            builder.queryParam("before", before);
        }

        if (after != null) {
            builder.queryParam("after", after);
        }
    }
}
