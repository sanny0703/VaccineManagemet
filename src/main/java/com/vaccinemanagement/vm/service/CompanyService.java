package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(String name) {
        List<Company> companies = companyRepository.findByCompanyName(name);
        if (!companies.isEmpty()) return companies.get(0);
        return null;
    }


    public Optional<Company> searchCompanyById(int id) {
        return companyRepository.findById(id);
    }
}
