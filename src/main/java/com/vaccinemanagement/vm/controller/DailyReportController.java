package com.vaccinemanagement.vm.controller;

import com.vaccinemanagement.vm.model.DailyReport;
import com.vaccinemanagement.vm.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    @PostMapping("/{userId}/{vaccineId}/{companyId}")
    public ResponseEntity<DailyReport> addReport(@RequestBody DailyReport report, @PathVariable("userId") int userId, @PathVariable("vaccineId") int vaccineId, @PathVariable("companyId") int companyId) {

        DailyReport savedReport = dailyReportService.addReport(report, userId, vaccineId, companyId);
        URI uri = URI.create("/report/" + savedReport.getReportId());
        return ResponseEntity.created(uri).body(savedReport);

    }

    @GetMapping()
    public List<DailyReport> getReports() {
        return dailyReportService.getReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyReport> getReport(@PathVariable("id") int dailyReportId) {
        Optional<DailyReport> dailyReport = dailyReportService.searchById(dailyReportId);
        return dailyReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
