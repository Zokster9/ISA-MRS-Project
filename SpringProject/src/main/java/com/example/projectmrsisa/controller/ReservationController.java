package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.*;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
import com.example.projectmrsisa.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {
    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RetreatService retreatService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TagService tagService;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private RevisionService revisionService;

    private final String dateFormat = "yyyy-MM-dd";
    
    private Validator validator = new Validator();

    @GetMapping(value = "/getPrivilegedUserReservations")
    @PreAuthorize("hasAnyRole('fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity<List<ReservationDTO>> getPrivilegedUserReservations(Principal principal) {
        User user;
        try {
            user = userService.findUserByEmail(principal.getName());
            List<Service> services = serviceService.findOwnersServices(user);
            List<ReservationDTO> reservationsDTO = new ArrayList<>();
            for (Service service : services) {
                List<Reservation> reservations = reservationService.findReservationByServiceId(service.getId());
                for (Reservation r : reservations) {
                    reservationsDTO.add(new ReservationDTO(r));
                }
            }
            return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/retreat/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<RetreatDTO>> getAvailableRetreats(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            if (client.isPenalized())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            List<Retreat> retreats = retreatService.getRetreats();
            List<RetreatDTO> retreatDTOs = new ArrayList<>();
            for (Retreat retreat : retreats) {
                if (serviceAvailabilityService.isAvailable(retreat.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime()) &&
                        !reservationService.isReserved(retreat.getId(), reservationQueryDTO.getFromDate(),
                                reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime(), client.getId()) &&
                        retreatSuitableForReservation(retreat, reservationQueryDTO)) {
                    retreatDTOs.add(new RetreatDTO(retreat, revisionService.getAverageRatingForService(retreat.getId())));
                }
            }
            return new ResponseEntity<>(retreatDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean retreatSuitableForReservation(Retreat retreat, ReservationQueryDTO reservationQueryDTO) {
        int maxCapacity = retreat.getNumOfBeds() + 1;
        if (maxCapacity < reservationQueryDTO.getNumOfPeople() ||
                reservationQueryDTO.getNumOfRooms() != retreat.getNumOfRooms())
            return false;
        return serviceHasTag(retreat, reservationQueryDTO);
    }

    @GetMapping(value = "/ship/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ShipDTO>> getAvailableShips(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            if (client.isPenalized())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            List<Ship> ships = shipService.getShips();
            List<ShipDTO> shipDTOs = new ArrayList<>();
            for (Ship ship : ships) {
                if (serviceAvailabilityService.isAvailable(ship.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime()) &&
                        !reservationService.isReserved(ship.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(),
                        reservationQueryDTO.getToTime(), client.getId()) &&
                        serviceHasTag(ship, reservationQueryDTO)) {
                    shipDTOs.add(new ShipDTO(ship, revisionService.getAverageRatingForService(ship.getId())));
                }
            }
            return new ResponseEntity<>(shipDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/adventure/getAvailableReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<AdventureDTO>> getAvailableAdventures(Principal principal, ReservationQueryDTO reservationQueryDTO) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            if (client.isPenalized())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            List<Adventure> adventures = adventureService.getAdventures();
            List<AdventureDTO> adventureDTOs = new ArrayList<>();
            for (Adventure adventure : adventures) {
                if (serviceAvailabilityService.isAvailable(adventure.getId(), reservationQueryDTO.getFromDate(),
                        reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(), reservationQueryDTO.getToTime()) &&
                        !reservationService.isReserved(adventure.getId(), reservationQueryDTO.getFromDate(),
                                reservationQueryDTO.getToDate(), reservationQueryDTO.getFromTime(),
                                reservationQueryDTO.getToTime(), client.getId()) && adventureSuitableForReservation(adventure, reservationQueryDTO)) {
                    adventureDTOs.add(new AdventureDTO(adventure, revisionService.getAverageRatingForService(adventure.getId())));
                }
            }
            return new ResponseEntity<>(adventureDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean adventureSuitableForReservation(Adventure adventure, ReservationQueryDTO reservationQueryDTO) {
        int maxCapacity = adventure.getMaxNumOfPeople();
        if (maxCapacity < reservationQueryDTO.getNumOfPeople())
            return false;
        return serviceHasTag(adventure, reservationQueryDTO);
    }

    private boolean serviceHasTag(Service service, ReservationQueryDTO reservationQueryDTO) {
        boolean hasTag = false;
        if (reservationQueryDTO.getAdditionalServices() == null) return true;
        for (String tagName : reservationQueryDTO.getAdditionalServices()) {
            for (Tag tag : service.getAdditionalServices()) {
                if (tag.getName().equals(tagName)) {
                    hasTag = true;
                    break;
                } else {
                    hasTag = false;
                }
            }
            if (!hasTag)
                break;
        }
        return hasTag;
    }

    @PostMapping(value = "/makeAReservation")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ReservationDTO> makeAReservation(Principal principal, @RequestBody ReservationDTO reservationDTO) {
        Client client;
        Service service;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            if (client.isPenalized())
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            service = serviceService.findById(reservationDTO.getServiceId());
            Set<Tag> additionalServices = new HashSet<>();
            if (reservationDTO.getServiceType() != null) {
                additionalServices = tagService.findTags(new ArrayList<>(reservationDTO.getAdditionalServices()), reservationDTO.getServiceType());
            } else {
                for (Tag tag : service.getAdditionalServices()) {
                    if (reservationDTO.getAdditionalServices().contains(tag.getName())) {
                        additionalServices.add(tag);
                    }
                }
            }
            LoyaltyProgram loyaltyProgram = loyaltyProgramService.findActiveLoyaltyProgram();
            double discount = 0;
            if (client.getLoyaltyStatus() == LoyaltyStatus.SILVER) {
                discount = loyaltyProgram.getSilverClientBonus();
            } else if (client.getLoyaltyStatus() == LoyaltyStatus.GOLD) {
                discount = loyaltyProgram.getGoldClientBonus();
            }
            reservationDTO.setPrice(reservationDTO.getPrice() * (1 - discount));
            Reservation reservation = reservationService.addReservation(new Reservation(reservationDTO, service, client, additionalServices));
            ReservationDTO resDTO = new ReservationDTO(reservation);
            emailService.sendReservationConfirmation(resDTO);
            return new ResponseEntity<>(resDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/changeStatus")
    @PreAuthorize("hasAnyRole('fishingInstructor', 'shipOwner', 'retreatOwner')")
    public ResponseEntity<ReservationDTO> changeReservationStatus(@RequestBody ReservationDTO reservationDTO) {
        User client;
        User privilegedUser;
        LoyaltyProgram loyaltyProgram;
        try {
            client = userService.findUserById(reservationDTO.getClientId());
            privilegedUser = userService.findUserById(reservationDTO.getPrivilegedUserId());
            loyaltyProgram = loyaltyProgramService.findActiveLoyaltyProgram();

            client.addPoints(loyaltyProgram.getClientPointsPerReservation());
            privilegedUser.addPoints(loyaltyProgram.getPrivilegedPointsPerReservation());

            if (client.getLoyaltyStatus() == LoyaltyStatus.REGULAR && client.getLoyaltyPoints() >= loyaltyProgram.getSilverPointsRequired()) {
                client.setLoyaltyStatus(LoyaltyStatus.SILVER);
            } else if (client.getLoyaltyStatus() == LoyaltyStatus.SILVER && client.getLoyaltyPoints() >= loyaltyProgram.getGoldPointsRequired()) {
                client.setLoyaltyStatus(LoyaltyStatus.GOLD);
            }

            if (privilegedUser.getLoyaltyStatus() == LoyaltyStatus.REGULAR && privilegedUser.getLoyaltyPoints() >= loyaltyProgram.getSilverPointsRequired()) {
                privilegedUser.setLoyaltyStatus(LoyaltyStatus.SILVER);
            } else if (privilegedUser.getLoyaltyStatus() == LoyaltyStatus.SILVER && privilegedUser.getLoyaltyPoints() >= loyaltyProgram.getGoldPointsRequired()) {
                privilegedUser.setLoyaltyStatus(LoyaltyStatus.GOLD);
            }
            reservationService.changeReservationStatus(ReservationStatus.FINISHED, reservationDTO.getId());
            reservationDTO.setStatus(ReservationStatus.FINISHED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/make-for-client/{serviceId}", produces = "application/json")
    @PreAuthorize("hasAnyRole('retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<ReservationDTO> makeReservationForClient(@RequestBody ReservationDTO reservationDTO, @PathVariable Integer serviceId) {
        if (!validator.validReservationDTO(reservationDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Client client = (Client) userService.findUserByEmail(reservationDTO.getClientEmail());
            Service service = serviceService.findById(serviceId);
            if (!reservationService.currentReservationForClientAndService(serviceId, client))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            if (!serviceAvailabilityService.isAvailable(serviceId, reservationDTO.getFromDate(), reservationDTO.getToDate(),
                    reservationDTO.getFromTime(), reservationDTO.getToTime()))
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            if (!reservationService.checkIfReservationsExistForDate(serviceId, reservationDTO.getFromDate(), reservationDTO.getToDate()))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            Set<Tag> additionalServices = tagService.findTags(new ArrayList<>(reservationDTO.getAdditionalServices()), reservationDTO.getServiceName());
            LoyaltyProgram loyaltyProgram = loyaltyProgramService.findActiveLoyaltyProgram();
            double discount = 0;
            if (client.getLoyaltyStatus() == LoyaltyStatus.SILVER) {
                discount = loyaltyProgram.getSilverClientBonus();
            } else if (client.getLoyaltyStatus() == LoyaltyStatus.GOLD) {
                discount = loyaltyProgram.getGoldClientBonus();
            }
            double finalPrice = (double) ((reservationDTO.getToDate().getTime() - reservationDTO.getFromDate().getTime())
                    / (1000 * 60 * 60 * 24)) * service.getPrice() * (1 - discount);
            DecimalFormat df = new DecimalFormat("0.00");
            finalPrice = Double.parseDouble(df.format(finalPrice));
            reservationDTO.setPrice(finalPrice);
            Reservation reservation = new Reservation(reservationDTO, service, client, additionalServices);
            reservation = reservationService.addReservation(reservation);
            reservationDTO = new ReservationDTO(reservation);
            emailService.sendReservationConfirmation(reservationDTO);
            return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getNonComplainedReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getNonComplainedReservations(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsFinishedReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (complaintService.findComplaintByReservationId(reservation.getId()) == null) {
                    reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/shipReservationHistory")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getShipReservationHistory(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsFinishedReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (shipService.findShipById(reservation.getService().getId()) != null) {
                    Revision revision = revisionService.findClientsRevisionForReservation(reservation.getId(), client.getId());
                    if (revision != null)
                        reservationDTOS.add(new ReservationDTO(reservation, revision.getRating().getServiceRating()));
                    else
                        reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/retreatReservationHistory")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getRetreatReservationHistory(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsFinishedReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                try {
                    if (retreatService.getRetreatById(reservation.getService().getId()) != null) {
                        Revision revision = revisionService.findClientsRevisionForReservation(reservation.getId(), client.getId());
                        if (revision != null)
                            reservationDTOS.add(new ReservationDTO(reservation, revision.getRating().getServiceRating()));
                        else
                            reservationDTOS.add(new ReservationDTO(reservation));
                    }
                } catch (ClassCastException ignored) {
                }
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/adventureReservationHistory")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getAdventureReservationHistory(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsFinishedReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (adventureService.findAdventureById(reservation.getService().getId()) != null) {
                    Revision revision = revisionService.findClientsRevisionForReservation(reservation.getId(), client.getId());
                    if (revision != null)
                        reservationDTOS.add(new ReservationDTO(reservation, revision.getRating().getServiceRating()));
                    else
                        reservationDTOS.add(new ReservationDTO(reservation));
                }
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getPendingReservations")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ReservationDTO>> getPendingReservations(Principal principal) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Reservation> reservations = reservationService.findClientsPendingReservations(client.getId());
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation : reservations) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/findAllNotCancelled")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservations;
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        try {
            reservations = reservationService.findNonCancelledReservations();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/findInDateSpan")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<List<ReservationDTO>> getReservationsInDateSpan(@RequestParam(name = "fromDate") String fromDate, @RequestParam(name = "toDate") String toDate) {
        List<Reservation> reservations;
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        try {
            reservations = reservationService.findReservationsInDateSpan(getDate(fromDate), getDate(toDate));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    public Date getDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    @PostMapping(value = "/calculateSystemIncome")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<Double> getSystemIncome(@RequestBody ReservationsDTO reservationsDTO) {
        List<Discount> discounts;
        double systemIncome = 0;
        try {
            discounts = discountService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Discount discount : discounts) {
            for (ReservationDTO reservationDTO : reservationsDTO.getReservationsDTO()) {
                if (reservationDTO.getFromDate().after(discount.getFromDate()) && reservationDTO.getFromDate().before(discount.getToDate())) {
                    systemIncome += reservationDTO.getPrice() * discount.getDiscountRate();
                }
            }
        }
        return new ResponseEntity<>(systemIncome, HttpStatus.OK);
    }

    @GetMapping(value = "/findUsersNonCancelledReservations")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<List<ReservationDTO>> findPrivilegedUsersReservations(Principal principal) {
        User owner;
        List<Reservation> reservations;
        try {
            owner = userService.findUserByEmail(principal.getName());
            reservations = reservationService.findPrivilegedUsersReservations(owner.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/calculateMyIncome")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<Double> calculateMyIncome(@RequestBody ReservationsDTO reservationsDTO, Principal principal) {
        User owner;
        LoyaltyProgram loyaltyProgram;
        List<Discount> discounts;
        try {
            owner = userService.findUserByEmail(principal.getName());
            loyaltyProgram = loyaltyProgramService.findActiveLoyaltyProgram();
            discounts = discountService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double income = 0;
        for (Discount discount : discounts) {
            for (ReservationDTO reservationDTO : reservationsDTO.getReservationsDTO()) {
                if (reservationDTO.getFromDate().after(discount.getFromDate()) && reservationDTO.getFromDate().before(discount.getToDate())) {
                    if (owner.getLoyaltyStatus() == LoyaltyStatus.SILVER) {
                        income += reservationDTO.getPrice() * (1 - discount.getDiscountRate() + loyaltyProgram.getSilverPrivilegedBonus());
                    } else if (owner.getLoyaltyStatus() == LoyaltyStatus.GOLD) {
                        income += reservationDTO.getPrice() * (1 - discount.getDiscountRate() + loyaltyProgram.getGoldPrivilegedUserBonus());
                    } else {
                        income += reservationDTO.getPrice() * (1 - discount.getDiscountRate());
                    }
                }
            }
        }
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping(value = "/findInDateSpanPrivilegedUser")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<List<ReservationDTO>> getReservationsInDateSpanForPrivilegedUser(@RequestParam(name = "fromDate") String fromDate, @RequestParam(name = "toDate") String toDate, Principal principal) {
        List<Reservation> reservations;
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        User owner;
        try {
            owner = userService.findUserByEmail(principal.getName());
            reservations = reservationService.findReservationsInDateSpanForPrivilegedUser(getDate(fromDate), getDate(toDate), owner.getId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(reservation));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    private boolean isCancellationValid(Date fromDate) {
        Date today = new Date();
        long differenceInTime = fromDate.getTime() - today.getTime();
        long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;
        return differenceInDays >= 3;
    }

    @PutMapping(value = "/cancelReservation/{reservationId}")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Integer reservationId) {
        try {
            Reservation reservation = reservationService.findReservationById(reservationId);
            if (isCancellationValid(reservation.getFromDate())) {
                reservationService.changeReservationStatus(ReservationStatus.CANCELLED, reservationId);
                reservation.setStatus(ReservationStatus.CANCELLED);
                return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/yearlyReport")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<GraphDTO> yearlyReport(Principal principal){
        User owner;
        List<Reservation> reservations;
        try{
            owner = userService.findUserByEmail(principal.getName());
            reservations = reservationService.findPrivilegedUsersReservations(owner.getId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Integer> values = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat(dateFormat);
        for (Reservation reservation : reservations){
            if (reservation.getStatus() != ReservationStatus.CANCELLED){
                String year = df.format(reservation.getFromDate()).substring(0, 4);
                addYears(values, dates, year);
            }
        }
        GraphDTO graphDTO = new GraphDTO(dates, values);
        return new ResponseEntity<>(graphDTO, HttpStatus.OK);
    }

    private void addYears(List<Integer> values, List<String> dates, String year) {
        if (dates.contains(year)){
            for (int i = 0; i < dates.size(); i++) {
                if (year.equals(dates.get(i))) {
                    int currVal = values.get(i);
                    values.set(i, currVal + 1);
                }
            }
        } else {
            dates.add(year);
            values.add(1);
        }
    }

    @GetMapping(value="/monthlyReport")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<GraphDTO> monthlyReport(Principal principal){
        User owner;
        List<Reservation> reservations;
        try{
            owner = userService.findUserByEmail(principal.getName());
            reservations = reservationService.findPrivilegedUsersReservations(owner.getId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Integer> values = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat(dateFormat);
        for (Reservation reservation : reservations){
            if (reservation.getStatus() != ReservationStatus.CANCELLED){
                String yearMonth = df.format(reservation.getFromDate()).substring(0, 7);
                addYears(values, dates, yearMonth);
            }
        }
        GraphDTO graphDTO = new GraphDTO(dates, values);
        return new ResponseEntity<>(graphDTO, HttpStatus.OK);
    }

    @GetMapping(value="/weeklyReport")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<GraphDTO> weeklyReport(Principal principal) throws ParseException {
        User owner;
        List<Reservation> reservations;
        try{
            owner = userService.findUserByEmail(principal.getName());
            reservations = reservationService.findPrivilegedUsersReservations(owner.getId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Integer> values = new ArrayList<>();
        List<String> dates;
        dates = getDates();
        for (int i = 0; i< 8; i++){
            values.add(0);
        }

        for (Reservation reservation : reservations){
            if (reservation.getStatus() != ReservationStatus.CANCELLED){
                for (int i = 0; i < dates.size(); i++){
                    Date startDate = new SimpleDateFormat(dateFormat).parse(dates.get(i).substring(0, 10));
                    Date endDate = new SimpleDateFormat(dateFormat).parse(dates.get(i).substring(12));
                    if ((reservation.getFromDate().getTime() > startDate.getTime() && reservation.getFromDate().getTime() < endDate.getTime()) || reservation.getFromDate().getTime() == startDate.getTime()){
                        int currVal = values.get(i);
                        values.set(i, currVal + 1);
                    }
                }
            }
        }
        GraphDTO graphDTO = new GraphDTO(dates, values);
        return new ResponseEntity<>(graphDTO, HttpStatus.OK);
    }

    public List<String> getDates(){
        List<String> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat(dateFormat);
        Date today = new Date();

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -28);
        Date dateMinus28 = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -21);
        Date dateMinus21 = cal2.getTime();
        dates.add(df.format(dateMinus28) + " - " + df.format(dateMinus21));

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, -14);
        Date dateMinus14 = cal3.getTime();
        dates.add(df.format(dateMinus21) + " - " + df.format(dateMinus14));

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, -7);
        Date dateMinus7 = cal4.getTime();
        dates.add(df.format(dateMinus14) + " - " + df.format(dateMinus7));
        dates.add(df.format(dateMinus7) + " - " + df.format(today));

        Calendar cal5 = Calendar.getInstance();
        cal5.add(Calendar.DATE, +7);
        Date datePlus7 = cal5.getTime();
        dates.add(df.format(today) + " - " + df.format(datePlus7));

        Calendar cal6 = Calendar.getInstance();
        cal6.add(Calendar.DATE, +14);
        Date datePlus14 = cal6.getTime();
        dates.add(df.format(datePlus7) + " - " + df.format(datePlus14));

        Calendar cal7 = Calendar.getInstance();
        cal7.add(Calendar.DATE, +21);
        Date datePlus21 = cal7.getTime();
        dates.add(df.format(datePlus14) + " - " + df.format(datePlus21));

        Calendar cal8 = Calendar.getInstance();
        cal8.add(Calendar.DATE, +28);
        Date datePlus28 = cal8.getTime();
        dates.add(df.format(datePlus21) + " - " + df.format(datePlus28));

        return dates;
    }

    @GetMapping(path = "/reservations-for-service/{serviceId}")
    @PreAuthorize("hasAnyRole('fishingInstructor','retreatOwner','shipOwner')")
    public ResponseEntity<List<ReservationDTO>> getReservationsForService(@PathVariable Integer serviceId) {
        try {
            List<Reservation> reservations = reservationService.findReservationByServiceId(serviceId);
            List<ReservationDTO> reservationDTOS = new ArrayList<>();
            for (Reservation reservation: reservations) {
                reservationDTOS.add(new ReservationDTO(reservation));
            }
            return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
