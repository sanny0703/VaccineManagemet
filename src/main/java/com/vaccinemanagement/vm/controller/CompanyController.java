package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping()
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{name}")
    public Company getCompany(@PathVariable("name") String name) {
        return companyService.getCompany(name);
    }
}
