package com.vaccinemanagement.vm.exception;

public class VaccineCompanyNotFoundException extends  RuntimeException {
    public VaccineCompanyNotFoundException(String msg){
        super(msg);
    }
}
