package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.isActive = ?1 and u.isDeleted = ?2")
    public List<User> findUsersByActivatedStatus(Boolean isActivated, Boolean isDeleted);

    @Query("select f from FishingInstructor f where f.id = ?1")
    public FishingInstructor findFishingInstructorById(Integer id);

    @Query("select r from RetreatOwner r where r.id = ?1")
    public RetreatOwner findRetreatOwnerById(Integer id);

    @Query("select s from ShipOwner s where s.id = ?1")
    public ShipOwner findShipOwnerById(Integer id);

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

}
