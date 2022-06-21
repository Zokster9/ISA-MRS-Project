package com.example.projectmrsisa.controller;

import com.example.projectmrsisa.dto.PenalDTO;
import com.example.projectmrsisa.dto.ReportDTO;
import com.example.projectmrsisa.model.Report;
import com.example.projectmrsisa.model.Reservation;
import com.example.projectmrsisa.model.ReservationStatus;
import com.example.projectmrsisa.service.ClientService;
import com.example.projectmrsisa.service.EmailService;
import com.example.projectmrsisa.service.ReportService;
import com.example.projectmrsisa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/reports")
public class ReportController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientService clientService;

    @PostMapping(value="/penalizeClient")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> penalizeClient(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.FINISHED_REPORTED, penalDTO.getReservationId());
            reservation = reservationService.findReservationById(penalDTO.getReservationId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Report report = new Report(penalDTO.getComment(), true, false, true, true, reservation);
        try{
            reportService.save(report);
            clientService.updatePenaltyPoints(reservation.getClient().getId());
            if (reservation.getClient().getPenaltyPoints() >= 3) {
                clientService.updatePenaltyStatus(reservation.getClient().getId());
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReportDTO reportDTO = new ReportDTO(report);
        return new ResponseEntity<>(reportDTO, HttpStatus.OK);
    }

    @PostMapping(value="/sendReport")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> sendReport(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.FINISHED_REPORTED, penalDTO.getReservationId());
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

    @PostMapping(value="/askForPenal")
    @PreAuthorize("hasAnyRole('fishingInstructor','shipOwner', 'retreatOwner')")
    public ResponseEntity<ReportDTO> askForPenal(@RequestBody PenalDTO penalDTO){
        Reservation reservation;
        try{
            reservationService.changeReservationStatus(ReservationStatus.FINISHED_WAITING, penalDTO.getReservationId());
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

    @GetMapping(value="/findAll")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<List<ReportDTO>> findAll(){
        List<Report> reports;
        try{
            reports = reportService.findNegativeUnansweredReports();
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<ReportDTO> reportDTOS = new ArrayList<>();
        for (Report report : reports){
            reportDTOS.add(new ReportDTO(report));
        }
        return new ResponseEntity<>(reportDTOS, HttpStatus.OK);
    }

    @PutMapping(value="/updateReport")
    @PreAuthorize("hasAnyRole('admin','mainAdmin')")
    public ResponseEntity<ReportDTO> updateReport(@RequestBody ReportDTO reportDTO){
        Report report;
        try{
            report = reportService.findReportById(reportDTO.getId());
            reportService.setReportAnswered(reportDTO.getId());
            reservationService.changeReservationStatus(ReservationStatus.FINISHED_REPORTED, reportDTO.getReservationId());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (reportDTO.isPenalized()){
            try{
                reportService.setReportPenalized(reportDTO.getId());
                clientService.updatePenaltyPoints(report.getReservation().getClient().getId());
                if (report.getReservation().getClient().getPenaltyPoints() >= 3) {
                    clientService.updatePenaltyStatus(report.getReservation().getClient().getId());
                }
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            emailService.confirmReport(reportDTO);
        } else {
            emailService.declineReport(reportDTO);
        }
        ReportDTO reportDTO1 = new ReportDTO(report);
        return new ResponseEntity<>(reportDTO1, HttpStatus.OK);
    }
}
