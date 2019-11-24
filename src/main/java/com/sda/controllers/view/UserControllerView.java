package com.sda.controllers.view;

import com.sda.entities.User;
import com.sda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
class UserControllerView {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * List all products.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.listAllUsers().collect(Collectors.toList()));
        System.out.println("Returning users:");
        return "users";
    }

    /**
     * View a specific product by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("user/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "usershow";
    }

    // Afficher le formulaire de modification du User
    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id).get());
        return "userform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("user/new")
    public String newProduct(Model model) {
        model.addAttribute("user", new User());
        return "userform";
    }

    /**
     * Save user to database.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/user/" + user.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
