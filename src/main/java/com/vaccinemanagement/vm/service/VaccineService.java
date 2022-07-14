package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    private final String ADD_VACCINE = "1 st Dose vaccinated";
    private final String UPDATE_VACCINE = "2 nd Dose vaccinated";
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VaccineCompanyService vaccineCompanyService;

    public Vaccine addVaccine(Vaccine vaccine, int userId, int vaccineCompanyId) {
        User user = userService.searchUserById(userId);
        VaccineCompany vaccineCompany = vaccineCompanyService.searchVaccineCompanyById(vaccineCompanyId);
        vaccine.setUser(user);
        vaccine.setVaccineCompany(vaccineCompany);
        if (vaccine.getVaccineDate2() == null)
            vaccine.setVaccineStatus(ADD_VACCINE);
        else vaccine.setVaccineStatus(UPDATE_VACCINE);
        return vaccineRepository.save(vaccine);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public Vaccine searchVaccineById(int id) {
        return vaccineRepository.findById(id).get();
    }

    public List<Vaccine> searchByPatientName(String name) {
        return vaccineRepository.findByPatientNameContaining(name);
    }

}
