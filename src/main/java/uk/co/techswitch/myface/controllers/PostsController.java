package uk.co.techswitch.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import uk.co.techswitch.myface.models.api.posts.*;
import uk.co.techswitch.myface.models.database.Post;
import uk.co.techswitch.myface.services.DatabaseService;
import uk.co.techswitch.myface.services.PostsService;

import javax.validation.Valid;
import java.util.List;

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

        PostResultsPage results = new PostResultsPage(posts, filter, numberMatchingSearch);

        return new ModelAndView("/posts/search", "results", results);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {
        Post post = postsService.getById(id);

        PostModel postModel = new PostModel(post);

        return new ModelAndView("posts/detail", "postModel", postModel);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createPost(@ModelAttribute @Valid CreatePost createPost) {
        Post post = postsService.createPost(createPost);
        return new RedirectView("/posts/" + post.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updatePost(@PathVariable("id") long id, @ModelAttribute @Valid UpdatePost updatePost) {
        Post post = postsService.updatePost(id, updatePost);
        return new RedirectView("/posts/" + post.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RedirectView deletePost(@PathVariable("id") long id) {
        postsService.deletePost(id);
        return new RedirectView("/posts");
    }
}
