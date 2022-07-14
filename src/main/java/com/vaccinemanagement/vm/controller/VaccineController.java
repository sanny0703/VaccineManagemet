package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vaccine")
@CrossOrigin("*")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @PostMapping("/{userId}/{vaccineCompanyId}")
    public Vaccine addVaccine(@RequestBody Vaccine vaccine, @PathVariable("userId") int userId, @PathVariable("vaccineCompanyId") int vaccineCompanyId) {
        return vaccineService.addVaccine(vaccine, userId, vaccineCompanyId);
    }

    @GetMapping()
    public List<Vaccine> getAllUsedVaccines() {
        return vaccineService.getAllVaccines();
    }

    @GetMapping("/patientName/{name}")
    public List<Vaccine> searchVaccinesByPatientName(@PathVariable("name") String name) {
        return vaccineService.searchByPatientName(name);
    }

}
