package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.exception.UserNotFoundException;
import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        String rawPassword = user.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> searchUser(String email) {

        return userRepository.findByUserEmail(email);
    }

    public Optional<User> searchUserById(int id) {

        return userRepository.findById(id);
    }

    public void deleteUser(int id) {
        Optional<User> user = searchUserById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with user id: " + id + " is not found");
        }
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByUserNameContaining(name);
    }
}
