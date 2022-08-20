package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.Company;
import com.vaccinemanagement.vm.model.DailyReport;
import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.repository.DailyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DailyReportService {

    private static final String REPORT_SUCCESS = "Successfully vaccinated";
    private static final String REPORT_FAIL = "Vaccination was incomplete";

    @Autowired
    private DailyReportRepository dailyReportRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VaccineService vaccineService;
    @Autowired
    private CompanyService companyService;


    public DailyReport addReport(DailyReport report, int userId, int vaccineId, int companyId) {
        Optional<User> user = userService.searchUserById(userId);
        Optional<Vaccine> vaccine = vaccineService.searchVaccineById(vaccineId);
        Optional<Company> company = companyService.searchCompanyById(companyId);
        if (user.isPresent() && vaccine.isPresent() && company.isPresent()) {
            report.setUser(user.get());
            report.setVaccine(vaccine.get());
            report.setCompany(company.get());
        }
        buildReport(report);
        return dailyReportRepository.save(report);

    }

    public List<DailyReport> getReports() {
        return dailyReportRepository.findAll();

    }

    private void buildReport(DailyReport dailyReport) {

        Vaccine vaccine = dailyReport.getVaccine();
        String disc = "Name: " + vaccine.getPatientName() + ";Age: " + vaccine.getPatientAge() + ";Gender: " + vaccine.getPatientGender() + ";vaccine: " + dailyReport.getVaccineName();
        dailyReport.setReportDescription(disc);
        if (dailyReport.getReportStatus().equals("C")) dailyReport.setReportStatus(REPORT_SUCCESS);
        else dailyReport.setReportStatus(REPORT_FAIL);
    }

    public Optional<DailyReport> searchById(int id) {
        return dailyReportRepository.findById(id);
    }

    public List<DailyReport> searchByVaccineName(String vaccineName) {
        return dailyReportRepository.findByVaccineNameContainingOrderByReportDate(vaccineName);
    }

    public List<DailyReport> searchByDate(Date date) {
        return dailyReportRepository.findByReportDateOrderByReportDate(date);
    }
}
