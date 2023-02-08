package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userDao.allUsers());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.getById(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userDao.add(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.getById(id));
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.edit(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        User user = userDao.getById(id);
        userDao.delete(user);
        return "redirect:/user";
    }
}
