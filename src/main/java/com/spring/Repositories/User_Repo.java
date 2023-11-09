package com.spring.Repositories;

import com.spring.Entity.Electro_Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface User_Repo extends JpaRepository<Electro_Users, String> {
    Optional<Electro_Users> findByuserEmail(String userEmail);
    List<Electro_Users> findByuserNameContaining(String Keyword);
}
