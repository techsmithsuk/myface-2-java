package uk.co.techswitch.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.co.techswitch.myface.models.api.CreateUser;
import uk.co.techswitch.myface.models.api.UserFilter;
import uk.co.techswitch.myface.models.api.UserModel;
import uk.co.techswitch.myface.models.api.UserResultsPage;
import uk.co.techswitch.myface.models.database.User;
import uk.co.techswitch.myface.services.UsersService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView searchUsers(UserFilter filter) {
        List<User> users = usersService.searchUsers(filter);
        int numberMatchingSearch = usersService.countUsers(filter);

        UserResultsPage resultsPage = new UserResultsPage(users, filter, numberMatchingSearch);

        return new ModelAndView("users/search");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDetails(@PathVariable("id") long id) {
        User user = usersService.getById(id);

        UserModel model = new UserModel(user);

        return new ModelAndView("users/details");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute @Valid CreateUser user) {
        usersService.createUser(user);
        return new ModelAndView("index");
    }
}
