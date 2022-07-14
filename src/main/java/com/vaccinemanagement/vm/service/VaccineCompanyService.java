package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.repository.CompanyRepository;
import com.vaccinemanagement.vm.repository.VaccineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineCompanyService {

    @Autowired
    private VaccineCompanyRepository vaccineCompanyRepository;
    @Autowired
    private CompanyService companyService;

    public VaccineCompany addVaccine(VaccineCompany vaccineCompany, int companyId) {
        Company company = companyService.getCompany(companyId);
        vaccineCompany.setCompany(company);
        return vaccineCompanyRepository.save(vaccineCompany);
    }

    public List<VaccineCompany> getAllVaccines() {
        return vaccineCompanyRepository.findAll();
    }

    public List<VaccineCompany> searchVaccines(String name) {
        return vaccineCompanyRepository.findByVaccineNameContaining(name);
    }

    public VaccineCompany searchVaccineCompanyById(int id) {
        return vaccineCompanyRepository.findById(id).get();
    }
}
