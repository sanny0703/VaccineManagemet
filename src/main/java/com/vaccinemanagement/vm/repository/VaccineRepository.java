package com.vaccinemanagement.vm.repository;


import com.vaccinemanagement.vm.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

    List<Vaccine> findByPatientNameContaining(String name);
}

