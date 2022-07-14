package com.vaccinemanagement.vm.repository;


import com.vaccinemanagement.vm.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findByCompanyName(String name);
}

