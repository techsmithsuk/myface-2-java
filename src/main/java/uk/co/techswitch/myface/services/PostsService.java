package uk.co.techswitch.myface.services;

import org.springframework.stereotype.Service;
import uk.co.techswitch.myface.models.database.Post;

@Service
public class PostsService extends DatabaseService {

    public Post getById(long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM posts WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Post.class)
                        .one()
        );
    }
}
