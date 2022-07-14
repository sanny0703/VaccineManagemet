package com.vaccinemanagement.vm.model;


import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vaccineId;
    private Date vaccineDate1;
    private Date vaccineDate2;
    private String vaccineStatus;
    private String patientName;
    private Integer patientAge;
    private String patientGender;

    @ManyToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_vaccine_fk1"))
    private User user;
    @OneToOne
    @JoinColumn(name = "vaccine_company_id", foreignKey = @ForeignKey(name = "vaccine_company_vaccine_fk2"))
    private VaccineCompany vaccineCompany;

    public Vaccine() {
    }

    public Integer getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public Date getVaccineDate1() {
        return vaccineDate1;
    }

    public void setVaccineDate1(Date vaccineDate1) {
        this.vaccineDate1 = vaccineDate1;
    }

    public Date getVaccineDate2() {
        return vaccineDate2;
    }

    public void setVaccineDate2(Date vaccineDate2) {
        this.vaccineDate2 = vaccineDate2;
    }

    public String getVaccineStatus() {
        return vaccineStatus;
    }

    public void setVaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VaccineCompany getVaccineCompany() {
        return vaccineCompany;
    }

    public void setVaccineCompany(VaccineCompany vaccineCompany) {
        this.vaccineCompany = vaccineCompany;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "vaccineId=" + vaccineId +
                ", vaccineDate1=" + vaccineDate1 +
                ", vaccineDate2=" + vaccineDate2 +
                ", vaccineStatus='" + vaccineStatus + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", patientGender='" + patientGender + '\'' +
                ", user=" + user +
                ", vaccineCompany=" + vaccineCompany +
                '}';
    }
}

