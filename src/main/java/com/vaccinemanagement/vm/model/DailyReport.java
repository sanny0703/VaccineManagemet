package com.vaccinemanagement.vm.model;


import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "daily_report")
public class DailyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reportId;
    private Date reportDate;
    private Time reportTime;
    private String reportStatus;
    private String reportDescription;
    private String vaccineName;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "report_user_fk3"))
    private User user;
    @OneToOne
    @JoinColumn(name = "vaccine_id", foreignKey = @ForeignKey(name = "report_vaccine_fk4"))
    private Vaccine vaccine;
    @OneToOne
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "report_company_fk5"))
    private Company company;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Time getReportTime() {
        return reportTime;
    }

    public void setReportTime(Time reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

