package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User searchUser(String email) {

        List<User> users = userRepository.findByUserEmail(email);
        if (users.size() != 0)
            return users.get(0);
        return null;
    }

    public User searchUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User deleteUser(int id) {
        User user = searchUserById(id);
        userRepository.deleteById(id);
        return user;
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByUserNameContaining(name);
    }
}
