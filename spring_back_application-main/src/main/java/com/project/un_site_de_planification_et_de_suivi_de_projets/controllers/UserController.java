package com.project.un_site_de_planification_et_de_suivi_de_projets.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.un_site_de_planification_et_de_suivi_de_projets.entities.User;
import com.project.un_site_de_planification_et_de_suivi_de_projets.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseBody
    public User add_New_User(@RequestBody User user) {
        return userService.addUser(user);
    }

    @JsonIgnore
    @GetMapping("/all")
    @ResponseBody
    public List<User> all_users(){
        return userService.findAllUsers();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public User user_id(@PathVariable("id") long id){
        return userService.findUserById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        userService.updateUser(user); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_user(@PathVariable("id") long id){
        userService.deleteUser(id); }


}
