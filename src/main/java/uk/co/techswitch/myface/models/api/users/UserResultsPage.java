package uk.co.techswitch.myface.models.api.users;

import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.database.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResultsPage extends ResultsPage<UserModel, UserFilter> {
    public UserResultsPage(List<User> users, UserFilter filter, int totalNumberOfResults) {
        super(users.stream().map(UserModel::new).collect(Collectors.toList()), filter, totalNumberOfResults);
    }

    @Override
    protected String getRootUrl() {
        return "/api/users";
    }
}
