package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.ActionDTO;
import com.example.projectmrsisa.dto.ServiceAvailabilityDTO;
import com.example.projectmrsisa.dto.ServiceDTO;
import com.example.projectmrsisa.dto.ServiceQueryDTO;
import com.example.projectmrsisa.model.*;
import com.example.projectmrsisa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceAvailabilityService serviceAvailabilityService;

    @Autowired
    private ActionService actionService;

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
    private RevisionService revisionService;

    private final String retreatType = "retreat";
    private final String shipType = "ship";
    private final String adventureType = "adventure";

    @DeleteMapping(value="/delete/{id}")
    @PreAuthorize("hasAnyRole('admin', 'mainAdmin')")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        try {
            if (!reservationService.pendingReservationForServiceExists(id)) return new ResponseEntity<>(HttpStatus.CONFLICT);
            serviceService.deleteServiceById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-service-availability/{id}")
    @PreAuthorize("hasAnyRole('retreatOwner', 'shipOwner', 'fishingInstructor')")
    public ResponseEntity<List<ServiceAvailabilityDTO>> getServiceAvailability(@PathVariable Integer id) {
        try {
            Optional<Service> service = serviceService.findServiceById(id);
            if (!service.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<ServiceAvailability> availabilities = serviceAvailabilityService.getServiceAvailabilitiesForService(id);
            List<ServiceAvailabilityDTO> serviceAvailabilityDTOS = new ArrayList<>();
            for (ServiceAvailability sa: availabilities) {
                serviceAvailabilityDTOS.add(new ServiceAvailabilityDTO(sa));
            }
            return new ResponseEntity<>(serviceAvailabilityDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/allSubscriptions")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ServiceDTO>> getAllSubscriptions(Principal principal) {
        Client client = (Client) userService.findUserByEmail(principal.getName());
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        for (Service service: client.getSubscriptions()) {
            serviceDTOs.add(new ServiceDTO(service, getServiceType(service), revisionService.getAverageRatingForService(service.getId())));
        }
        return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
    }

    private String getServiceType(Service service) {
        if (adventureService.findAdventureById(service.getId()) != null)
            return adventureType;
        else if (shipService.findShipById(service.getId()) != null)
            return shipType;
        else
            return retreatType;
    }

    @GetMapping(value="/getAvailableFastReservations/{serviceId}")
    @PreAuthorize("hasRole('client')")
    public ResponseEntity<List<ActionDTO>> getAvailableFastReservations(Principal principal, @PathVariable Integer serviceId) {
        Client client;
        try {
            client = (Client) userService.findUserByEmail(principal.getName());
            List<Action> actions = actionService.getActionsByServiceAndCurrentDate(serviceId);
            List<ActionDTO> actionDTOs = new ArrayList<>();
            for (Action action: actions) {
                if (!reservationService.isReserved(serviceId, action.getDateFrom(),
                        action.getDateTo(), action.getTimeFrom(), action.getTimeTo(), client.getId())) {
                    actionDTOs.add(new ActionDTO(action));
                }
            }
            return new ResponseEntity<>(actionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<ServiceDTO>> searchServices(ServiceQueryDTO serviceQueryDTO) {
        try {
            List<ServiceDTO> serviceDTOS = new ArrayList<>();
            if (serviceQueryDTO.getServiceType().equals(retreatType)) {
                searchRetreats(serviceQueryDTO, serviceDTOS);
            } else if (serviceQueryDTO.getServiceType().equals(shipType)) {
                searchShips(serviceQueryDTO, serviceDTOS);
            } else {
                searchAdventures(serviceQueryDTO, serviceDTOS);
            }
            return new ResponseEntity<>(serviceDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void searchAdventures(ServiceQueryDTO serviceQueryDTO, List<ServiceDTO> serviceDTOS) {
        List<Adventure> adventures;
        adventures = adventureService.getAdventures();
        for (Adventure adventure : adventures) {
            if (containsName(adventure, serviceQueryDTO.getName()) && containsAddress(adventure, serviceQueryDTO.getAddress())
                    && containsCity(adventure, serviceQueryDTO.getCity()) && containsCountry(adventure, serviceQueryDTO.getCountry())
                    && containsDescription(adventure, serviceQueryDTO.getPromotionalDescription())) {
                serviceDTOS.add(new ServiceDTO(adventure, adventureType, revisionService.getAverageRatingForService(adventure.getId())));
            }
        }
    }

    private void searchShips(ServiceQueryDTO serviceQueryDTO, List<ServiceDTO> serviceDTOS) {
        List<Ship> ships;
        ships = shipService.getShips();
        for (Ship ship : ships) {
            if (containsName(ship, serviceQueryDTO.getName()) && containsAddress(ship, serviceQueryDTO.getAddress())
                    && containsCity(ship, serviceQueryDTO.getCity()) && containsCountry(ship, serviceQueryDTO.getCountry())
                    && containsDescription(ship, serviceQueryDTO.getPromotionalDescription())) {
                serviceDTOS.add(new ServiceDTO(ship, shipType, revisionService.getAverageRatingForService(ship.getId())));
            }
        }
    }

    private void searchRetreats(ServiceQueryDTO serviceQueryDTO, List<ServiceDTO> serviceDTOS) {
        List<Retreat> retreats;
        retreats = retreatService.getRetreats();
        for (Retreat retreat : retreats) {
            if (containsName(retreat, serviceQueryDTO.getName()) && containsAddress(retreat, serviceQueryDTO.getAddress())
            && containsCity(retreat, serviceQueryDTO.getCity()) && containsCountry(retreat, serviceQueryDTO.getCountry())
            && containsDescription(retreat, serviceQueryDTO.getPromotionalDescription())) {
                serviceDTOS.add(new ServiceDTO(retreat, retreatType, revisionService.getAverageRatingForService(retreat.getId())));
            }
        }
    }

    private boolean containsName(Service service, String name) {
        if (name.equals("")) {
            return true;
        } else {
            String serviceName = service.getName().toLowerCase();
            return serviceName.contains(name.toLowerCase());
        }
    }

    private boolean containsCity(Service service, String city) {
        if (city.equals("")) {
            return true;
        } else {
            String serviceCity = service.getAddress().getCity().toLowerCase();
            return serviceCity.contains(city.toLowerCase());
        }
    }

    private boolean containsAddress(Service service, String address) {
        if (address.equals("")) {
            return true;
        } else {
            String serviceAddress = service.getAddress().getStreet().toLowerCase();
            return serviceAddress.contains(address.toLowerCase());
        }
    }

    private boolean containsCountry(Service service, String country) {
        if (country.equals("")) {
            return true;
        } else {
            String serviceCountry = service.getAddress().getCountry().toLowerCase();
            return serviceCountry.contains(country.toLowerCase());
        }
    }

    private boolean containsDescription(Service service, String description) {
        if (description.equals("")) {
            return true;
        } else {
            String serviceDescription = service.getDescription().toLowerCase();
            return serviceDescription.contains(description.toLowerCase());
        }
    }
}
