package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{email}")
    public User searchUser(@PathVariable("email") String email) {
        System.out.println(email);
        System.out.println(userService.searchUser(email));
        return userService.searchUser(email);
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable("userId") int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/userName/{name}")
    public List<User> searchByName(@PathVariable("name") String name) {
        return userService.searchUsersByName(name);
    }
}
