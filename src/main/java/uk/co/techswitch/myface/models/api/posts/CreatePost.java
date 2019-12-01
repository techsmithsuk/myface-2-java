package uk.co.techswitch.myface.models.api.posts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePost {
    @NotNull
    private long sender;
    @NotBlank
    private String message;
    private String imageUrl;

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
