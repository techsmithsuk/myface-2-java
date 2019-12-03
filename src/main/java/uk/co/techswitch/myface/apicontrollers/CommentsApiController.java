package uk.co.techswitch.myface.apicontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.ResultsPageBuilder;
import uk.co.techswitch.myface.models.api.comments.CommentModel;
import uk.co.techswitch.myface.models.api.comments.CommentsFilter;
import uk.co.techswitch.myface.models.api.comments.CreateComment;
import uk.co.techswitch.myface.models.api.comments.UpdateComment;
import uk.co.techswitch.myface.models.database.Comment;
import uk.co.techswitch.myface.services.CommentsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentsApiController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsApiController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultsPage<CommentModel> searchComments(CommentsFilter filter) {
        List<Comment> comments = commentsService.searchComments(filter);
        int numberMatchingSearch = commentsService.countComments(filter);

        return new ResultsPageBuilder<CommentModel>()
                .withItems(comments.stream().map(CommentModel::new).collect(Collectors.toList()))
                .withFilter(filter)
                .withNumberMatchingSearch(numberMatchingSearch)
                .withBaseUrl("/comments")
                .build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentModel getCommentDetails(@PathVariable("id") long id) {
        Comment comment = commentsService.getById(id);
        return new CommentModel(comment);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommentModel createComment(@RequestBody @Valid CreateComment createComment) {
        Comment comment = commentsService.createComment(createComment);
        return new CommentModel(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CommentModel updateComment(@PathVariable("id") long id, @RequestBody @Valid UpdateComment updateComment) {
        Comment comment = commentsService.updateComment(id, updateComment);
        return new CommentModel(comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") long id) {
        commentsService.deleteComment(id);
    }
}
