package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.exception.CompanyNotFoundException;
import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company savedCompany = companyService.addCompany(company);
        URI uri = URI.create("/company/" + savedCompany.getCompanyId());
        return ResponseEntity.created(uri).body(savedCompany);
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") int companyId) {
        Optional<Company> company = companyService.searchCompanyById(companyId);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public Company getCompany(@PathVariable("name") String name) {
        return companyService.getCompany(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int id) {
        try {
            companyService.delete(id);
            return ResponseEntity.ok("Company successfully deleted");
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
