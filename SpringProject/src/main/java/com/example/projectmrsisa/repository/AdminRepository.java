package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Modifying
    @Query("update Admin a set a.isPasswordChanged = true where a.user.id = ?1")
    public void updatePasswordChangedStatus(Integer id);

    @Query("select a from Admin a where a.user.id = ?1")
    public Admin findAdminByUserId(Integer id);
}
