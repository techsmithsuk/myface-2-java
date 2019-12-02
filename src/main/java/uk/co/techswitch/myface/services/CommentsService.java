package uk.co.techswitch.myface.services;

import org.springframework.stereotype.Service;
import uk.co.techswitch.myface.models.api.comments.CommentsFilter;
import uk.co.techswitch.myface.models.database.Comment;
import uk.co.techswitch.myface.models.database.Post;

import java.util.List;

@Service
public class CommentsService extends DatabaseService {

    public List<Comment> searchComments(CommentsFilter filter) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(
                                "SELECT * " +
                                        "FROM comments " +
                                        "WHERE (:sender is NULL OR sender = :sender) " +
                                        "AND (:post is NULL OR post = :post) " +
                                        "AND (:before is NULL OR timestamp < :before) " +
                                        "AND (:after is NULL OR timestamp > :after) " +
                                        "ORDER BY post.timestamp DESC " +
                                        "LIMIT :limit " +
                                        "OFFSET :offset")
                        .bind("sender", filter.getSender())
                        .bind("post", filter.getPost())
                        .bind("before", filter.getBefore())
                        .bind("after", filter.getAfter())
                        .bind("limit", filter.getPageSize())
                        .bind("offset", filter.getOffset())
                        .mapToBean(Comment.class)
                        .list()
        );
    }

    public int countComments(CommentsFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT COUNT(*) FROM comments " +
                                "WHERE (:sender is NULL OR sender = :sender) " +
                                "AND (:post is NULL OR post = :post) " +
                                "AND (:before is NULL OR timestamp < :before) " +
                                "AND (:after is NULL OR timestamp > :after) ")
                        .bind("sender", filter.getSender())
                        .bind("post", filter.getPost())
                        .bind("before", filter.getBefore())
                        .bind("after", filter.getAfter())
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public Comment getById(long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM comments WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Comment.class)
                        .one()
        );
    }
}
