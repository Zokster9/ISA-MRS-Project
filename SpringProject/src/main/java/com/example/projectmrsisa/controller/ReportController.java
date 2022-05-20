package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.PenalDTO;
import com.example.projectmrsisa.dto.ReportDTO;
import com.example.projectmrsisa.model.Report;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import com.example.projectmrsisa.service.ReportService;
import com.example.projectmrsisa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/reports")
public class ReportController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReportService reportService;

    @Transactional
    @PostMapping(value="/penalizeClient")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> penalizeClient(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.Finished_Reported, penalDTO.getReservationId());
            reservation = reservationService.findReservationById(penalDTO.getReservationId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report report = new Report(penalDTO.getComment(), true, false, true, true, reservation);
        try{
            reportService.save(report);
            reservation.getClient().penalize();
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDTO reportDTO = new ReportDTO(report);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/sendReport")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> sendReport(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.Finished_Reported, penalDTO.getReservationId());
            reservation = reservationService.findReservationById(penalDTO.getReservationId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report report = new Report(penalDTO.getComment(), false, true, false, true, reservation);
        try{
            reportService.save(report);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDTO reportDTO = new ReportDTO(report);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value="/askForPenal")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> askForPenal(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.Finished_Waiting, penalDTO.getReservationId());
            reservation = reservationService.findReservationById(penalDTO.getReservationId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report report = new Report(penalDTO.getComment(), true, true, false, false, reservation);
        try{
            reportService.save(report);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDTO reportDTO = new ReportDTO(report);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }
}
