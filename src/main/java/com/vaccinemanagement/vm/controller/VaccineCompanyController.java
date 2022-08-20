package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.service.VaccineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine_company")
public class VaccineCompanyController {

    @Autowired
    private VaccineCompanyService vaccineCompanyService;

    @PostMapping("/{id}")
    public ResponseEntity<VaccineCompany> addVaccineCompany(@RequestBody VaccineCompany vaccineCompany, @PathVariable("id") int companyId) {
        VaccineCompany savedCompany = vaccineCompanyService.addVaccine(vaccineCompany, companyId);
        URI uri = URI.create("/vaccine_company/"+savedCompany.getVaccineCompanyId());
        return ResponseEntity.created(uri).body(savedCompany);
    }

    @GetMapping
    public List<VaccineCompany> getAllVaccines() {
        return vaccineCompanyService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineCompany> getVaccine(@PathVariable("id")int vaccineCompanyId){
        Optional<VaccineCompany>vaccineCompany = vaccineCompanyService.searchVaccineCompanyById(vaccineCompanyId);
        return vaccineCompany.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}")
    public List<VaccineCompany> searchVaccineAndCompany(@PathVariable("name") String name) {
        System.out.println(name);
        return vaccineCompanyService.searchVaccines(name);
    }
}
