package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    private  final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //shows all users
    @GetMapping(value ="/users")
    public String showAllUsers(Model model) {
        List<User> totalList = userService.getAllUsers();
        model.addAttribute("totalList", totalList);
        return "allUsers";
    }

    //add user getmap
    @GetMapping(value ="/users/add")
    public String newUser(Model model) {
        User usr = new User();
        model.addAttribute("newuser", usr);
        return "addUser";
    }

    //add user postmap
    @PostMapping(value ="/users")
    public String createUser(@ModelAttribute("newuser") User user) { // в этом объекте класса юзер будет лежать польз с данными из форма, со всеми полями заполненными/ благодаря аннотац @ModelAttribute
        userService.saveUser(user);
        return "redirect:/users";
    }

    //delete  user
    @GetMapping(value ="/users/delete/{id}")
    public String deleteUser(@PathVariable(value="id") Long id, Model model) {
        User delusr = userService.getById(id);
        model.addAttribute("deluser", delusr);
        return "delete";
    }

    //delete user
    @DeleteMapping(value ="/users/delete/{id}")
    public String deletePage(@PathVariable(value="id") Long id, Model model) {
        userService.delete(id);
        return "redirect:/users";
    }

    //edit user
    @GetMapping(value ="/users/edit/{id}")
    public String editPage(@PathVariable(value="id") Long id, Model model) {
        User usr = userService.getById(id);
        model.addAttribute("eduser", usr);
        return "edit";
    }
    //edit user
    @PatchMapping(value ="/users/edit/{id}")
    public String editUser(@ModelAttribute("eduser") User eduser) {
        userService.edit(eduser);
        return "redirect:/users";
    }

}
