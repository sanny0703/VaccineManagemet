package com.vaccinemanagement.vm.model;

import javax.validation.constraints.Email;
import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String jwt;
    @Email
    private String emailAddress;
    private Integer id;

    public AuthenticationResponse(String jwt,String email,int id) {
        this.jwt = jwt;
        this.emailAddress = email;
        this.id = id;
    }
    public AuthenticationResponse(){

    }
    public String getEmailAddress(){ return  emailAddress;};

    public String getJwt() {
        return jwt;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwt='" + jwt + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", id=" + id +
                '}';
    }
}
