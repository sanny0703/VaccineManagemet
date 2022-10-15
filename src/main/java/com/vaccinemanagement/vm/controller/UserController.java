package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.exception.UserNotFoundException;
import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Validated
@SuppressWarnings("unused")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") int id) {
        Optional<User> user = userService.searchUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User " + id + " successfully deleted");
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/userName/{name}")
    public List<User> searchByName(@PathVariable("name") String name) {
        return userService.searchUsersByName(name);
    }
}
