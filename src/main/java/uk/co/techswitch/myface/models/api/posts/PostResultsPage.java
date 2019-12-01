package uk.co.techswitch.myface.models.api.posts;

import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.users.UserModel;
import uk.co.techswitch.myface.models.api.users.UsersFilter;
import uk.co.techswitch.myface.models.database.Post;
import uk.co.techswitch.myface.models.database.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostResultsPage extends ResultsPage<PostModel, PostsFilter> {
    public PostResultsPage(List<Post> posts, PostsFilter filter, int totalNumberOfResults) {
        super(posts.stream().map(PostModel::new).collect(Collectors.toList()), filter, totalNumberOfResults);
    }

    @Override
    protected String getRootUrl() {
        return "/posts";
    }
}
