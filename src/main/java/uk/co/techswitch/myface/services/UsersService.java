package uk.co.techswitch.myface.services;

import org.springframework.stereotype.Service;
import uk.co.techswitch.myface.models.api.CreateUser;

@Service
public class UsersService extends DatabaseService {

    public long createUser(CreateUser user) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate(
                    "INSERT INTO users " +
                            "(username, email, first_name, last_name) " +
                            "VALUES " +
                            "(:username, :email, :firstName, :lastName)")
                    .bind("username", user.getUsername())
                    .bind("email", user.getEmail())
                    .bind("firstName", user.getFirstName())
                    .bind("lastName", user.getLastName())
                    .execute();

            return handle.createQuery("SELECT LAST_INSERT_ID()")
                    .mapTo(Long.class)
                    .one();
        });
    }
}
