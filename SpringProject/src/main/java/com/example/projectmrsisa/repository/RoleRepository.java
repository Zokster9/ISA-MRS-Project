package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(String name);

    @Query("select r from Role r where r.name = ?1")
    Role findRoleByName(String name);
}
