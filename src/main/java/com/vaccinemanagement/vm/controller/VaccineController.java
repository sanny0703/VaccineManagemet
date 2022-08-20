package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {


    @Autowired
    private VaccineService vaccineService;

    @PostMapping("/{userId}/{vaccineCompanyId}")
    public ResponseEntity<Vaccine> addVaccine(@RequestBody Vaccine vaccine, @PathVariable("userId") int userId, @PathVariable("vaccineCompanyId") int vaccineCompanyId) {

        Vaccine savedVaccine = vaccineService.addVaccine(vaccine, userId, vaccineCompanyId);
        URI uri = URI.create("/vaccine/" + savedVaccine.getVaccineId());
        return ResponseEntity.created(uri).body(savedVaccine);

    }

    @GetMapping()
    public List<Vaccine> getAllUsedVaccines() {
        return vaccineService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVaccine(@PathVariable("id") int vaccineId) {
        Optional<Vaccine> vaccine = vaccineService.searchVaccineById(vaccineId);
        return vaccine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patientName/{name}")
    public List<Vaccine> searchVaccinesByPatientName(@PathVariable("name") String name) {
        return vaccineService.searchByPatientName(name);
    }

}
