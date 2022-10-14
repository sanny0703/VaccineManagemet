package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.exception.VaccineCompanyNotFoundException;
import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.repository.VaccineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineCompanyService {

    @Autowired
    private VaccineCompanyRepository vaccineCompanyRepository;
    @Autowired
    private CompanyService companyService;

    public VaccineCompany addVaccine(VaccineCompany vaccineCompany) {
        return vaccineCompanyRepository.save(vaccineCompany);
    }

    public List<VaccineCompany> getAllVaccines() {
        return vaccineCompanyRepository.findAll();
    }

    public Optional<VaccineCompany> searchVaccineById(int id) {
        return vaccineCompanyRepository.findById(id);
    }

    public List<VaccineCompany> searchVaccines(String name) {
        return vaccineCompanyRepository.findByVaccineNameContaining(name);
    }

    public Optional<VaccineCompany> searchVaccineCompanyById(int id) {
        return vaccineCompanyRepository.findById(id);
    }

    public void deleteVaccineCompany(int id) throws VaccineCompanyNotFoundException {
        Optional<VaccineCompany> vaccineCompany = vaccineCompanyRepository.findById(id);
        if (vaccineCompany.isPresent()) vaccineCompanyRepository.deleteById(id);
        else throw new VaccineCompanyNotFoundException("Vaccine Company with id " + id + "doesn't exist");
    }
}
