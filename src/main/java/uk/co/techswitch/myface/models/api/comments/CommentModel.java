package uk.co.techswitch.myface.models.api.comments;

import uk.co.techswitch.myface.models.database.Comment;

import java.util.Date;

public class CommentModel {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final Comment comment;

    public CommentModel(Comment comment) {
        this.comment = comment;
    }

    public long getId() {
        return comment.getId();
    }

    public long getSender() {
        return comment.getSender();
    }

    public long getPost() {
        return comment.getPost();
    }

    public String getMessage() {
        return comment.getMessage();
    }

    public Date getTimestamp() {
        return comment.getTimestamp();
    }
}
