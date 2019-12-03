package uk.co.techswitch.myface.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.ResultsPageBuilder;
import uk.co.techswitch.myface.models.api.comments.CommentsFilter;
import uk.co.techswitch.myface.models.api.comments.CommentModel;
import uk.co.techswitch.myface.models.api.comments.CreateComment;
import uk.co.techswitch.myface.models.api.comments.UpdateComment;
import uk.co.techswitch.myface.models.database.Comment;
import uk.co.techswitch.myface.services.CommentsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView searchComments(CommentsFilter filter) {
        List<Comment> comments = commentsService.searchComments(filter);
        int numberMatchingSearch = commentsService.countComments(filter);

        ResultsPage results = new ResultsPageBuilder<CommentModel>()
                .withItems(comments.stream().map(CommentModel::new).collect(Collectors.toList()))
                .withFilter(filter)
                .withNumberMatchingSearch(numberMatchingSearch)
                .withBaseUrl("/comments")
                .build();

        return new ModelAndView("comments/search", "results", results);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getCommentDetails(@PathVariable("id") long id) {
        Comment comment = commentsService.getById(id);

        CommentModel model = new CommentModel(comment);

        return new ModelAndView("/comments/detail", "commentModel", model);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createComment(@ModelAttribute @Valid CreateComment createComment) {
        Comment comment = commentsService.createComment(createComment);
        return new RedirectView("/comments/" + comment.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updateComment(@PathVariable("id") long id, @ModelAttribute @Valid UpdateComment updateComment) {
        Comment comment = commentsService.updateComment(id, updateComment);
        return new RedirectView("/comments/" + comment.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteComment(@PathVariable("id") long id) {
        commentsService.deleteComment(id);
        return new RedirectView("/comments");
    }
}
