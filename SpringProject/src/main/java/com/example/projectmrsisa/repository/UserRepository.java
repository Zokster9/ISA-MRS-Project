package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.isActive = ?1 and u.isDeleted = ?2")
    public List<User> findUsersByActivatedStatus(Boolean isActivated, Boolean isDeleted);

    @Query("select u from User u where u.id = ?1")
    public User findUserById(Integer id);

    @Modifying
    @Query("update User u set u.isActive = true where u.id = ?1")
    public void updateUserActivatedStatusById(Integer id);

    @Modifying
    @Query("update User u set u.isDeleted = true where u.id = ?1")
    public void updateUserDeletedStatusById(Integer id);

    @Query("select rr from RegistrationReasoning rr where rr.privilegedUser = ?1")
    public RegistrationReasoning findRegistrationReasoningByUser(User user);

    @Query("select u from User u where u.email = ?1")
    public User findUserByEmail(String email);

    @Modifying
    @Query("update User u set u.password = ?1 where u.email = ?2")
    public void updateUserPassword(String password, String email);

    @Modifying
    @Query("update User u set u.surname = ?1 where u.email = ?2")
    public void updateUserSurname(String surname, String email);

    @Modifying
    @Query("update User u set u.address = ?1 where u.email = ?2")
    public void updateUserAddress(Address address, String email);

    @Modifying
    @Query("update User u set u.name = ?1 where u.email = ?2")
    public void updateUserName(String name, String email);

    @Modifying
    @Query("update User u set u.phoneNumber = ?1 where u.email = ?2")
    public void updateUserPhoneNumber(String phoneNumber, String email);

    @Modifying
    @Query("update User u set u.isDeleted = ?1 where u.email = ?2")
    public void updateUserDeletedStatusByEmail(boolean isDeleted, String email);
}
