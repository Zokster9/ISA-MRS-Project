package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.*;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
import com.example.projectmrsisa.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RegistrationReasoningService registrationReasoningService;

    @Autowired
    private TerminationReasoningService terminationReasoningService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private RevisionService revisionService;
    
    private Validator validator = new Validator();

    @GetMapping(value="/inactive", produces = "application/json")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<List<UserDTO>> getInactiveUsers(){
        List<User> inactiveUsers = userService.findUsersByActivatedStatus(false, false);
        List<UserDTO> inactiveUsersDTO = new ArrayList<UserDTO>();
        for (User iu : inactiveUsers){
            PrivilegedUser privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
            List<Role> userRoles = iu.getRoles();
            Role role = userRoles.get(0);
            switch (role.getName()) {
                case "ROLE_fishingInstructor":
                    privilegedUser = PrivilegedUser.FISHING_INSTRUCTOR;
                    break;
                case "ROLE_retreatOwner":
                    privilegedUser = PrivilegedUser.RETREAT_OWNER;
                    break;
                case "ROLE_shipOwner":
                    privilegedUser = PrivilegedUser.SHIP_OWNER;
                    break;
            }
            RegistrationReasoningDTO registrationReasoningDTO = new RegistrationReasoningDTO(userService.findRegistrationReasoningByUserId(iu.getId()));
            if (!registrationReasoningDTO.isAnswered()){
                inactiveUsersDTO.add(new UserDTO(iu, privilegedUser, registrationReasoningDTO));
            }
        }
        return new ResponseEntity<>(inactiveUsersDTO, HttpStatus.OK);
    }

    @PostMapping(value="/accept")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> acceptUser(@RequestBody RegistrationChoiceDTO registrationChoiceDTO){
        User user = userService.findUserById(registrationChoiceDTO.getUserId());
        userService.updateUserActivatedStatusById(user.getId());
        userService.updateRegistrationReasoningStatus(user);
        UserDTO userDTO = new UserDTO(user);
        try{
            emailService.sendRegistrationAcceptedEmail(userDTO);
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/activate-client/{id}")
    public ResponseEntity<UserDTO> activateClient(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        userService.updateUserActivatedStatusById(user.getId());
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/decline")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> declineUser(@RequestBody RegistrationChoiceDTO registrationChoiceDTO){
        User user = userService.findUserById(registrationChoiceDTO.getUserId());
        userService.updateUserDeletedStatusById(registrationChoiceDTO.getUserId());
        userService.updateRegistrationReasoningStatus(user);
        UserDTO userDTO = new UserDTO(user);
        try {
            emailService.sendRegistrationDeclinedEmail(userDTO, registrationChoiceDTO.getDeclineReasoning());
        } catch( Exception e ){
            return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/changeInfo")
    @PreAuthorize("hasAnyRole('admin', 'client', 'retreatOwner', 'shipOwner', 'fishingInstructor', 'mainAdmin')")
    public ResponseEntity<UserDTO> changeInfo(@RequestBody UserDTO userDTO, Principal user){
        if (!validator.validChangedUserInfo(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!validator.validAddress(userDTO.getAddressDTO().getCountry(), userDTO.getAddressDTO().getCity(), userDTO.getAddressDTO().getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!userDTO.getEmail().equals(user.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        try {
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User u;
        try {
            u = userService.findUserByEmail(userDTO.getEmail());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!u.getName().equals(userDTO.getName())){
            userService.updateUserName(userDTO.getName(), userDTO.getEmail());
        }
        if (!u.getSurname().equals(userDTO.getSurname())){
            userService.updateUserSurname(userDTO.getSurname(), userDTO.getEmail());
        }
        if (!u.getAddress().getStreet().equals(userDTO.getAddressDTO().getStreet()) || !u.getAddress().getCity().equals(userDTO.getAddressDTO().getCity()) ||
            !u.getAddress().getCountry().equals(userDTO.getAddressDTO().getCountry())){
            userService.updateUserAddress(a, userDTO.getEmail());
        }
        if (!u.getPhoneNumber().equals(userDTO.getPhoneNumber())){
            userService.updateUserPhoneNumber(userDTO.getPhoneNumber(), userDTO.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        if (!validator.validUser(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validAddress(userDTO.getAddressDTO().getCountry(), userDTO.getAddressDTO().getCity(), userDTO.getAddressDTO().getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validRegistrationData(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Role role;
        try {
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
            role = roleService.findRoleByName("ROLE_" + userDTO.getPrivilegedUserType());
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userDTO.getRegistrationType().equals("client")) {
            try {
                if (role == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                Client client = clientService.save(new Client(userDTO, a, role));
                ClientDTO clientDTO = new ClientDTO(client);
                emailService.sendActivationEmail(clientDTO);
                return new ResponseEntity<>(new ClientDTO(client), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                if (role == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                User user = userService.save(new User(userDTO, a, role));
                RegistrationReasoning registrationReasoning = registrationReasoningService.addRegistrationReasoning(new RegistrationReasoning(user, userDTO.getRegistrationExplanation()));
                return new ResponseEntity<>(new UserDTO(user, PrivilegedUser.RETREAT_OWNER, new RegistrationReasoningDTO(registrationReasoning)), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/registerAdmin")
    @PreAuthorize("hasRole('mainAdmin')")
    public ResponseEntity<AdminDTO> registerAdmin(@RequestBody UserDTO userDTO){
        if (!validator.validAdmin(userDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!validator.validAddress(userDTO.getAddressDTO().getCountry(), userDTO.getAddressDTO().getCity(), userDTO.getAddressDTO().getStreet())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Address a;
        Role role;
        try{
            a = addressService.getAddress(new Address(userDTO.getAddressDTO()));
            role = roleService.findRoleByName("ROLE_admin");
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            User user = userService.save(new User(userDTO, a, role, true));
            Admin admin = adminService.saveAdmin(new Admin(user));
            return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.CREATED);
        } catch ( Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/findByEmail/{email}")
    //TODO: Autorizacija
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/sendTerminationReason")
    @PreAuthorize("hasAnyRole('client', 'retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<UserDTO> sendTerminationReason(@RequestBody TerminationReasonDTO terminationReasonDTO, Principal principal){
        if (terminationReasonDTO.getTerminationReason() == null || terminationReasonDTO.getTerminationReason().equals("")) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.findUserByEmail(principal.getName());
        TerminationReasoning terminationReasoning = terminationReasoningService.addTerminationReasoning(new TerminationReasoning(user, terminationReasonDTO.getTerminationReason()));
        userService.deactivateUserById(user.getId()); // deaktiviram korisnika
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value="/changePassword")
    @PreAuthorize("hasAnyRole('admin', 'client', 'retreatOwner', 'shipOwner', 'fishingInstructor', 'mainAdmin')")
    public ResponseEntity<UserDTO> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO, Principal loggedUser){
        User user = userService.findUserByEmail(loggedUser.getName());
        if (passwordChangeDTO.getNewPassword() == null || passwordChangeDTO.getConfirmPassword() == null || !passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUserPassword(passwordChangeDTO.getNewPassword(), user.getEmail());
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/activateAdmin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDTO> activateAdmin(@RequestBody PasswordChangeDTO passwordChangeDTO, Principal loggedUser){
        User user = userService.findUserByEmail(loggedUser.getName());
        if (passwordChangeDTO.getNewPassword() == null || passwordChangeDTO.getConfirmPassword() == null || !passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUserPassword(passwordChangeDTO.getNewPassword(), user.getEmail());
        adminService.updatePasswordChangedStatus(user.getId());
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getLoggedUser")
    @PreAuthorize("hasAnyRole('admin', 'client', 'retreatOwner', 'shipOwner', 'fishingInstructor', 'mainAdmin')")
    public ResponseEntity<UserDTO> getLoggedUser(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value= "/getLoggedAdmin")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<AdminUserDTO> getLoggedAdmin(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Admin admin = adminService.findAdminByUserId(user.getId());
        return new ResponseEntity<>(new AdminUserDTO(admin, user), HttpStatus.OK);
    }

    @GetMapping(value="/findMyEntities")
    @PreAuthorize("hasAnyRole('retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<List<ServiceDTO>> findMyEntities(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        List<Service> ownersServices = serviceService.findOwnersServices(user);
        List<ServiceDTO> ownersServicesDTO = new ArrayList<>();
        for (Service s : ownersServices){
            if (s.isDeleted()) continue;
            ownersServicesDTO.add(new ServiceDTO(s));
        }
        return new ResponseEntity<>(ownersServicesDTO, HttpStatus.OK);
    }

    @GetMapping(value="/findOwnerOfService/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> findOwnerOfService(@PathVariable Integer id){
        User user = userService.findUserById(id);
        List<Role> roles = user.getRoles();
        Role userRole = roles.get(0);
        PrivilegedUser privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
        if (userRole.getName().equals("ROLE_fishingInstructor")){
            privilegedUser = PrivilegedUser.FISHING_INSTRUCTOR;
        }
        else if (userRole.getName().equals("ROLE_shipOwner")){
            privilegedUser = PrivilegedUser.SHIP_OWNER;
        }
        else if (userRole.getName().equals("ROLE_retreatOwner")){
            privilegedUser = PrivilegedUser.RETREAT_OWNER;
        }
        UserDTO userDTO = new UserDTO(user, privilegedUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value="/findAllUsers")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<List<UserDTO>> findAllUsers(Principal principal){
        List<User> users = userService.findAll();
        User mainAdmin = userService.findUserByEmail(principal.getName());
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users){
            if (user.isDeleted()) continue;
            if (user.getId().equals(mainAdmin.getId())) continue;
            List<Role> roles = user.getRoles();
            Role userRole = roles.get(0);
            PrivilegedUser privilegedUser;
            if (userRole.getName().equals("ROLE_fishingInstructor")){
                privilegedUser = PrivilegedUser.FISHING_INSTRUCTOR;
            }
            else if (userRole.getName().equals("ROLE_shipOwner")){
                privilegedUser = PrivilegedUser.SHIP_OWNER;
            }
            else if (userRole.getName().equals("ROLE_retreatOwner")){
                privilegedUser = PrivilegedUser.RETREAT_OWNER;
            }
            else if (userRole.getName().equals("ROLE_client")){
                privilegedUser = PrivilegedUser.NOT_PRIVILEGED_USER;
            }
            else {
                privilegedUser = PrivilegedUser.ADMIN;
            }
            usersDTO.add(new UserDTO(user, privilegedUser));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id){
        User user = userService.findUserById(id);
        userService.updateUserDeletedStatusById(id);
        serviceService.deleteServicesByOwner(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value="/getAllInstructors")
    public ResponseEntity<List<UserDTO>> getAllInstructors() {
        List<User> instructors = userService.getAllInstructors("ROLE_fishingInstructor");
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User instructor : instructors) {
            userDTOS.add(new UserDTO(instructor, revisionService.getAverageRatingForServiceOwner(instructor.getId())));
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/searchInstructors")
    public ResponseEntity<List<UserDTO>> searchInstructors(FishingInstructorQueryDTO fishingInstructorQueryDTO) {
        List<User> instructors = userService.getAllInstructors("ROLE_fishingInstructor");
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User instructor : instructors) {
            if (containsName(instructor, fishingInstructorQueryDTO.getName()) && containsSurname(instructor, fishingInstructorQueryDTO.getSurname())
            && containsAddress(instructor, fishingInstructorQueryDTO.getAddress()) && containsCity(instructor, fishingInstructorQueryDTO.getCity())
            && containsCountry(instructor, fishingInstructorQueryDTO.getCountry()) && containsPhoneNumber(instructor, fishingInstructorQueryDTO.getPhoneNumber()))
            {
                userDTOS.add(new UserDTO(instructor, revisionService.getAverageRatingForServiceOwner(instructor.getId())));
            }
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    private boolean containsName(User user, String name) {
        if (name.equals("")) {
            return true;
        } else {
            String userName = user.getName().toLowerCase();
            return userName.contains(name.toLowerCase());
        }
    }
    private boolean containsSurname(User user, String surname) {
        if (surname.equals("")) {
            return true;
        } else {
            String userSurname = user.getSurname().toLowerCase();
            return userSurname.contains(surname.toLowerCase());
        }
    }


    private boolean containsCity(User user, String city) {
        if (city.equals("")) {
            return true;
        } else {
            String userCity = user.getAddress().getCity().toLowerCase();
            return userCity.contains(city.toLowerCase());
        }
    }

    private boolean containsAddress(User user, String address) {
        if (address.equals("")) {
            return true;
        } else {
            String userAddress = user.getAddress().getStreet().toLowerCase();
            return userAddress.contains(address.toLowerCase());
        }
    }

    private boolean containsCountry(User user, String country) {
        if (country.equals("")) {
            return true;
        } else {
            String userCountry = user.getAddress().getCountry().toLowerCase();
            return userCountry.contains(country.toLowerCase());
        }
    }

    private boolean containsPhoneNumber(User user, String phoneNumber) {
        if (phoneNumber.equals("")) {
            return true;
        } else {
            String userPhoneNumber = user.getPhoneNumber().toLowerCase();
            return userPhoneNumber.contains(phoneNumber.toLowerCase());
        }
    }
}
