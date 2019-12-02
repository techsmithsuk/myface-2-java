package uk.co.techswitch.myface.models.api.comments;

import java.util.Date;

public class CreateComment {
    private long sender;
    private long post;
    private String message;

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getPost() {
        return post;
    }

    public void setPost(long post) {
        this.post = post;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
