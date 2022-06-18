package com.example.projectmrsisa.repository;

import com.example.projectmrsisa.model.*;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.isActive = ?1 and u.isDeleted = ?2")
    public List<User> findUsersByActivatedStatus(Boolean isActivated, Boolean isDeleted);

    @Query("select u from User u where u.id = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public User findUserById(Integer id);

    @Modifying
    @Query("update User u set u.isActive = true where u.id = ?1")
    public void updateUserActivatedStatusById(Integer id);

    @Modifying
    @Query("update User u set u.isActive = false where u.id =?1")
    public void deactivateUserById(Integer id);

    @Modifying
    @Query("update User u set u.isDeleted = true where u.id = ?1")
    public void updateUserDeletedStatusById(Integer id);

    @Query("select rr from RegistrationReasoning rr where rr.privilegedUser.id = ?1")
    public RegistrationReasoning findRegistrationReasoningByUserId(Integer id);

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

    @Modifying
    @Query("update RegistrationReasoning  r set r.isAnswered = true where r.privilegedUser = ?1")
    public void updateRegistrationReasoningAnsweredStatus(User user);

    @Modifying
    @Query("update User u set u.loyaltyPoints = 0")
    public void resetLoyaltyPoints();

    @Modifying
    @Query("update User u set u.loyaltyStatus=?1")
    public void resetLoyaltyStatus(LoyaltyStatus status);

    @Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.name = ?1")
    List<User> getAllInstructors(String role);
}
