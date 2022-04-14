package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.FishingInstructor;
import com.example.projectmrsisa.model.RetreatOwner;
import com.example.projectmrsisa.model.ShipOwner;
import com.example.projectmrsisa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.isActive = ?1")
    public List<User> findUsersByActivatedStatus(Boolean isActivated);

    @Query("select f from FishingInstructor f where f.id = ?1")
    public FishingInstructor findFishingInstructorById(Integer id);

    @Query("select r from RetreatOwner r where r.id = ?1")
    public RetreatOwner findRetreatOwnerById(Integer id);

    @Query("select s from ShipOwner s where s.id = ?1")
    public ShipOwner findShipOwnerById(Integer id);
}
