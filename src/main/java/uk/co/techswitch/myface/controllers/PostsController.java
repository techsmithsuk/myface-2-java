package uk.co.techswitch.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.ResultsPageBuilder;
import uk.co.techswitch.myface.models.api.posts.*;
import uk.co.techswitch.myface.models.database.Post;
import uk.co.techswitch.myface.services.DatabaseService;
import uk.co.techswitch.myface.services.PostsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/posts")
public class PostsController extends DatabaseService {
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView searchPosts(PostsFilter filter) {
        List<Post> posts = postsService.searchPosts(filter);
        int numberMatchingSearch = postsService.countPosts(filter);

        ResultsPage results = new ResultsPageBuilder<PostModel, PostsFilter>()
                .withItems(posts.stream().map(PostModel::new).collect(Collectors.toList()))
                .withFilter(filter)
                .withNumberMatchingSearch(numberMatchingSearch)
                .withBaseUrl("/posts")
                .build();

        return new ModelAndView("/posts/search", "results", results);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {
        Post post = postsService.getById(id);

        PostModel postModel = new PostModel(post);

        return new ModelAndView("posts/detail", "postModel", postModel);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createPost(@RequestBody @Valid CreatePost createPost) {
        Post post = postsService.createPost(createPost);
        return new RedirectView("/posts/" + post.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updatePost(@PathVariable("id") long id, @RequestBody @Valid UpdatePost updatePost) {
        Post post = postsService.updatePost(id, updatePost);
        return new RedirectView("/posts/" + post.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RedirectView deletePost(@PathVariable("id") long id) {
        postsService.deletePost(id);
        return new RedirectView("/posts");
    }
}
