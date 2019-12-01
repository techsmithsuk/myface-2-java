package uk.co.techswitch.myface.models.api.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import uk.co.techswitch.myface.models.database.Post;

import java.util.Date;

public class PostModel {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final Post post;

    public PostModel(Post post) {
        this.post = post;
    }

    public long getId() {
        return post.getId();
    }

    public long getSender() {
        return post.getSender();
    }

    public String getMessage() {
        return post.getMessage();
    }

    public String getImageUrl() {
        return post.getImageUrl();
    }

    @JsonFormat(pattern = DATE_PATTERN)
    public Date getTimestamp() {
        return post.getTimestamp();
    }
}
