package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.service.CompanyService;
import com.vaccinemanagement.vm.service.VaccineCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("vaccine_company")
@CrossOrigin("*")
public class VaccineCompanyController {

    @Autowired
    private VaccineCompanyService vaccineCompanyService;

    @PostMapping("/{id}")
    public VaccineCompany addVaccineCompany(@RequestBody VaccineCompany vaccineCompany, @PathVariable("id") int companyId) {
        return vaccineCompanyService.addVaccine(vaccineCompany, companyId);
    }

    @GetMapping
    public List<VaccineCompany> getAllVaccines() {
        return vaccineCompanyService.getAllVaccines();
    }

    @GetMapping("/{name}")
    public List<VaccineCompany> searchVaccineAndCompany(@PathVariable("name") String name) {
        System.out.println(name);
        return vaccineCompanyService.searchVaccines(name);
    }
}
