package com.vaccinemanagement.vm.repository;


import com.vaccinemanagement.vm.model.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Integer> {

    List<DailyReport> findByVaccineNameContainingOrderByReportDate(String name);

    List<DailyReport> findByReportDateOrderByReportDate(Date date);
}

