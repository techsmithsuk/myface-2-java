package uk.co.techswitch.myface.apicontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.ResultsPageBuilder;
import uk.co.techswitch.myface.models.api.users.CreateUser;
import uk.co.techswitch.myface.models.api.users.UpdateUser;
import uk.co.techswitch.myface.models.api.users.UserModel;
import uk.co.techswitch.myface.models.api.users.UsersFilter;
import uk.co.techswitch.myface.models.database.User;
import uk.co.techswitch.myface.services.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
public class UsersApiController {
    private final UsersService usersService;

    @Autowired
    public UsersApiController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultsPage<UserModel, UsersFilter> searchUsers(UsersFilter filter) {
        List<User> users = usersService.searchUsers(filter);
        int numberMatchingSearch = usersService.countUsers(filter);

        return new ResultsPageBuilder<UserModel, UsersFilter>()
                .withItems(users.stream().map(UserModel::new).collect(Collectors.toList()))
                .withFilter(filter)
                .withNumberMatchingSearch(numberMatchingSearch)
                .withBaseUrl("/posts")
                .build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserModel getUserDetails(@PathVariable("id") long id) {
        User user = usersService.getById(id);
        return new UserModel(user);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserModel createUser(@RequestBody @Valid CreateUser createUser) {
        User user = usersService.createUser(createUser);
        return new UserModel(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public UserModel updateUser(@PathVariable("id") long id, @RequestBody @Valid UpdateUser updateUser) {
        User user = usersService.updateUser(id, updateUser);
        return new UserModel(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") long id) {
        usersService.deleteUser(id);
    }
}
