package uk.co.techswitch.myface.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import uk.co.techswitch.myface.models.api.ResultsPage;
import uk.co.techswitch.myface.models.api.ResultsPageBuilder;
import uk.co.techswitch.myface.models.api.users.*;
import uk.co.techswitch.myface.models.database.User;
import uk.co.techswitch.myface.services.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView searchUsers(UsersFilter filter) {
        List<User> users = usersService.searchUsers(filter);
        int numberMatchingSearch = usersService.countUsers(filter);

        ResultsPage results = new ResultsPageBuilder<UserModel, UsersFilter>()
                .withItems(users.stream().map(UserModel::new).collect(Collectors.toList()))
                .withFilter(filter)
                .withNumberMatchingSearch(numberMatchingSearch)
                .withBaseUrl("/posts")
                .build();

        return new ModelAndView("users/search", "results", results);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDetails(@PathVariable("id") long id) {
        User user = usersService.getById(id);

        UserModel userModel = new UserModel(user);

        return new ModelAndView("users/detail", "userModel", userModel);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView createUser(@ModelAttribute @Valid CreateUser createUser) {
        User user = usersService.createUser(createUser);
        return new RedirectView("/users/" + user.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView updateUser(@PathVariable("id") long id, @ModelAttribute @Valid UpdateUser updateUser) {
        User user = usersService.updateUser(id, updateUser);
        return new RedirectView("/users/" + user.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteUser(@PathVariable("id") long id) {
        usersService.deleteUser(id);
        return new RedirectView("/users");
    }
}
