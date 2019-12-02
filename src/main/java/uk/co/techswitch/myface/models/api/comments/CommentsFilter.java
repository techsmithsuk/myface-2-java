package uk.co.techswitch.myface.models.api.comments;

import org.springframework.web.util.UriBuilder;
import uk.co.techswitch.myface.models.api.Filter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentsFilter extends Filter {
    private Long sender;
    private Long post;
    private Date before;
    private Date after;

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    @Override
    public void appendQueryParams(UriBuilder builder) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (sender != null) {
            builder.queryParam("sender", sender);
        }

        if (post != null) {
            builder.queryParam("post", post);
        }

        if (before != null) {
            builder.queryParam("before", before);
        }

        if (after != null) {
            builder.queryParam("after", after);
        }
    }
}
