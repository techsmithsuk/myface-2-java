package uk.co.techswitch.myface.services;

import org.springframework.stereotype.Service;
import uk.co.techswitch.myface.models.api.posts.PostsFilter;
import uk.co.techswitch.myface.models.database.Post;

import java.util.List;

@Service
public class PostsService extends DatabaseService {

    public List<Post> searchPosts(PostsFilter filter) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(
                                "SELECT * " +
                                        "FROM posts " +
                                        "WHERE (:sender is NULL OR sender = :sender) " +
                                        "AND (:before is NULL OR timestamp < :before) " +
                                        "AND (:after is NULL OR timestamp > :after) " +
                                        "ORDER BY post.timestamp DESC " +
                                        "LIMIT :limit " +
                                        "OFFSET :offset")
                        .bind("sender", filter.getSender())
                        .bind("before", filter.getBefore())
                        .bind("after", filter.getAfter())
                        .bind("limit", filter.getPageSize())
                        .bind("offset", filter.getOffset())
                        .mapToBean(Post.class)
                        .list()
        );
    }

    public int countPosts(PostsFilter filter) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT COUNT(*) FROM posts " +
                                "WHERE (:sender is NULL OR sender = :sender) " +
                                "AND (:before is NULL OR timestamp < :before) " +
                                "AND (:after is NULL OR timestamp > :after)")
                        .bind("sender", filter.getSender())
                        .bind("before", filter.getBefore())
                        .bind("after", filter.getAfter())
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public Post getById(long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM posts WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Post.class)
                        .one()
        );
    }
}
