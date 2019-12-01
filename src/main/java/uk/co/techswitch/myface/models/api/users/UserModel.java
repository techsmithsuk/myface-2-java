package uk.co.techswitch.myface.models.api.users;

import uk.co.techswitch.myface.models.database.User;

public class UserModel {
    private final User user;

    public UserModel(User user) {
        this.user = user;
    }

    public long getId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getDisplayName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
