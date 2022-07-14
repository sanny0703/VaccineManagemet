package com.vaccinemanagement.vm.repository;

import com.vaccinemanagement.vm.model.VaccineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineCompanyRepository extends JpaRepository<VaccineCompany, Integer> {


    List<VaccineCompany> findByVaccineNameContaining(String name);

}
