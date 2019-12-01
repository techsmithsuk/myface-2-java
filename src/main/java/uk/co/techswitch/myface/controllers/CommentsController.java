package uk.co.techswitch.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.co.techswitch.myface.models.api.comments.CommentModel;
import uk.co.techswitch.myface.models.database.Comment;
import uk.co.techswitch.myface.services.CommentsService;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getCommentDetails(@PathVariable("id") long id) {
        Comment comment = commentsService.getById(id);

        CommentModel model = new CommentModel(comment);

        return new ModelAndView("/comments/detail", "commentModel", model);
    }
}
