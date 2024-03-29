package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.exception.VaccineCompanyNotFoundException;
import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.service.VaccineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine_company")
@SuppressWarnings("unused")
public class VaccineCompanyController {

    @Autowired
    private VaccineCompanyService vaccineCompanyService;

    @PostMapping()
    public ResponseEntity<VaccineCompany> addVaccineCompany(@RequestBody VaccineCompany vaccineCompany) {
        VaccineCompany savedCompany = vaccineCompanyService.addVaccine(vaccineCompany);
        return ResponseEntity.ok(savedCompany);
    }

    @GetMapping()
    public List<VaccineCompany> getAllVaccines() {
        return vaccineCompanyService.getAllVaccines();
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<VaccineCompany> getVaccine(@PathVariable("id") int vaccineCompanyId) {
        Optional<VaccineCompany> vaccineCompany = vaccineCompanyService.searchVaccineCompanyById(vaccineCompanyId);
        return vaccineCompany.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByName/{name}")
    public List<VaccineCompany> searchVaccineAndCompany(@PathVariable("name") String name) {
        return vaccineCompanyService.searchVaccines(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            vaccineCompanyService.deleteVaccineCompany(id);
            return ResponseEntity.ok("Vaccine Company is deleted");
        } catch (VaccineCompanyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
