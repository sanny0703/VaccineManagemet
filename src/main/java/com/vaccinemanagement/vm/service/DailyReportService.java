package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.model.DailyReport;
import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.repository.DailyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.sql.Time;

@Service
public class DailyReportService {

    private final String REPORT_SUCCESS = "Successfully vaccinated";
    private final String REPORT_FAIL = "Vaccination was incomplete";
    @Autowired
    private DailyReportRepository dailyReportRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private CompanyService companyService;

    public DailyReport addReport(DailyReport report, int userId, int vaccineId, int companyId) {
        User user = userService.searchUserById(userId);
        Vaccine vaccine = vaccineService.searchVaccineById(vaccineId);
        Company company = companyService.searchCompanyById(companyId);
        report.setUser(user);
        report.setVaccine(vaccine);
        report.setCompany(company);
        buildReport(report, vaccine);
        return dailyReportRepository.save(report);
    }

    public List<DailyReport> getReports() {
        return dailyReportRepository.findAll();

    }

    private void buildReport(DailyReport dailyReport, Vaccine vaccine) {

        String disc = "Name: " + vaccine.getPatientName() +
                "Age: " + vaccine.getPatientAge() +
                "Gender: " + vaccine.getPatientGender() +
                "vaccine: " + dailyReport.getVaccineName();
        dailyReport.setReportDescription(disc);
        if (dailyReport.getReportStatus().equals("C"))
            dailyReport.setReportStatus(REPORT_SUCCESS);
        else dailyReport.setReportStatus(REPORT_FAIL);
    }

    public List<DailyReport> searchByVaccineName(String vaccineName) {
        return dailyReportRepository.findByVaccineNameContainingOrderByReportDate(vaccineName);
    }

    public List<DailyReport> searchByDate(Date date) {
        return dailyReportRepository.findByReportDateOrderByReportDate(date);
    }
}
