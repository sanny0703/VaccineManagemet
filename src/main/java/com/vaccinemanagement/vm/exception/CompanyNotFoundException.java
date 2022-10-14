package com.vaccinemanagement.vm.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String msg){
        super(msg);
    }
}
