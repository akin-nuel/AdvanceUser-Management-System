package com.example.AdvanceUser.Repository;

import com.example.AdvanceUser.Model.AdvanceUser;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdvanceUserRepository extends JpaRepository<AdvanceUser, Long> {

    List<AdvanceUser> findByFullName(String fullName);
    AdvanceUser findByEmail(String email);

    List<AdvanceUser> findByGender(String gender);

    List<String> findAdvanceUserByFullName(String fullName);



}
