package com.vaccinemanagement.vm.service;

import com.vaccinemanagement.vm.model.User;
import com.vaccinemanagement.vm.model.Vaccine;
import com.vaccinemanagement.vm.model.VaccineCompany;
import com.vaccinemanagement.vm.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {


    private static final String ADD_VACCINE = "1 st Dose vaccinated";
    private static final String UPDATE_VACCINE = "2 nd Dose vaccinated";
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private VaccineCompanyService vaccineCompanyService;

    public Vaccine addVaccine(Vaccine vaccine, int userId, int vaccineCompanyId) {

        Optional<User> user = userService.searchUserById(userId);
        Optional<VaccineCompany> vaccineCompany = vaccineCompanyService.searchVaccineCompanyById(vaccineCompanyId);
        if (user.isPresent() && vaccineCompany.isPresent()) {
            vaccine.setUser(user.get());
            vaccine.setVaccineCompany(vaccineCompany.get());
        }
        if (vaccine.getVaccineDate2() == null) vaccine.setVaccineStatus(ADD_VACCINE);
        else vaccine.setVaccineStatus(UPDATE_VACCINE);
        return vaccineRepository.save(vaccine);

    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public Optional<Vaccine> searchVaccineById(int id) {
        return vaccineRepository.findById(id);
    }

    public List<Vaccine> searchByPatientName(String name) {
        return vaccineRepository.findByPatientNameContaining(name);
    }

}
