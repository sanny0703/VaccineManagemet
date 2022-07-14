package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.DailyReport;
import com.vaccinemanagement.vm.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("report")
@CrossOrigin("*")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    @PostMapping("/{userId}/{vaccineId}/{companyId}")
    public DailyReport addReport(@RequestBody DailyReport report, @PathVariable("userId") int userId, @PathVariable("vaccineId") int vaccineId, @PathVariable("companyId") int companyId) {
        return dailyReportService.addReport(report, userId, vaccineId, companyId);
    }

    @GetMapping()
    public List<DailyReport> getReports() {
        return dailyReportService.getReports();
    }

    @GetMapping("vaccine/{vaccineName}")
    public List<DailyReport> searchByVaccineName(@PathVariable("vaccineName") String vaccineName) {
        return dailyReportService.searchByVaccineName(vaccineName);
    }

    @GetMapping("date/{dt}")
    public List<DailyReport> searchByDate(@PathVariable("dt") Date date) {
        return dailyReportService.searchByDate(date);
    }
}
