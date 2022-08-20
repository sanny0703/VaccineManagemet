package com.vaccinemanagement.vm.repository;


import com.vaccinemanagement.vm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByUserEmail(String email);

    List<User> findByUserNameContaining(String name);
}

