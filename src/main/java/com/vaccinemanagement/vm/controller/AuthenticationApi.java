package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.jwt.JwtTokenUtil;
import com.vaccinemanagement.vm.model.AuthenticationRequest;
import com.vaccinemanagement.vm.model.AuthenticationResponse;
import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@SuppressWarnings("unused")
public class AuthenticationApi {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthenticationResponse response = new AuthenticationResponse(accessToken, user.getUserEmail(), user.getUserId());
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try{
            userService.addUser(user);
            return ResponseEntity.ok("User Added");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/admin")
    public ResponseEntity<?> addAdmin(@RequestBody User user) {
        if (!user.getIsAdmin().equals("S@nny0703"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        user.setIsAdmin("Y");
        userService.addUser(user);
        return ResponseEntity.ok("Admin added");
    }
}
