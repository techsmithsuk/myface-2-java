package uk.co.techswitch.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.co.techswitch.myface.models.api.posts.PostModel;
import uk.co.techswitch.myface.models.database.Post;
import uk.co.techswitch.myface.services.PostsService;

@Controller
@RequestMapping("/posts")
public class PostsController {
    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {
        Post post = postsService.getById(id);

        PostModel postModel = new PostModel(post);

        return new ModelAndView("posts/detail", "postModel", postModel);
    }
}
