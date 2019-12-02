package uk.co.techswitch.myface.models.api.comments;

import javax.validation.constraints.NotBlank;

public class UpdateComment {
    @NotBlank
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
