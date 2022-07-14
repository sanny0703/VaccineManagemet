package com.vaccinemanagement.vm.model;

import javax.persistence.*;

@Entity
@Table(name = "vaccine_company")
public class VaccineCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vaccine_company_id")
    private Integer vaccineCompanyId;

    @ManyToOne
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "vaccine_company_company_fk6"))
    private Company company;

    private String vaccineDetails;

    private String vaccineName;

    public Integer getVaccineCompanyId() {
        return vaccineCompanyId;
    }

    public void setVaccineCompanyId(Integer vaccineCompanyId) {
        this.vaccineCompanyId = vaccineCompanyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineDetails() {
        return vaccineDetails;
    }

    public void setVaccineDetails(String vaccineDetails) {
        this.vaccineDetails = vaccineDetails;
    }

    @Override
    public String toString() {
        return "VaccineCompany{" +
                "vaccineCompanyId=" + vaccineCompanyId +
                ", company=" + company +
                ", vaccineDetails='" + vaccineDetails + '\'' +
                ", vaccineName='" + vaccineName + '\'' +
                '}';
    }
}
