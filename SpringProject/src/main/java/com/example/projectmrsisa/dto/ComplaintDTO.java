package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Complaint;

import java.util.Set;

public class ComplaintDTO {

    private Integer id;
    private String complaint;
    private String response;
    private boolean isAnswered;
    private String clientName;
    private String clientSurname;
    private String privilegedUserName;
    private String privilegedUserSurname;
    private String serviceName;
    private Set<String> servicePictures;
    private Integer reservationId;

    public ComplaintDTO(){

    }

    public ComplaintDTO(Complaint complaint){
        id = complaint.getId();
        this.complaint = complaint.getComplaint();
        response = complaint.getResponse();
        isAnswered = false;
        clientName = complaint.getReservation().getClient().getName();
        clientSurname = complaint.getReservation().getClient().getSurname();
        privilegedUserName = complaint.getReservation().getService().getOwner().getName();
        privilegedUserSurname = complaint.getReservation().getService().getOwner().getSurname();
        serviceName = complaint.getReservation().getService().getName();
        servicePictures = complaint.getReservation().getService().getPictures();
        reservationId = complaint.getReservation().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getPrivilegedUserName() {
        return privilegedUserName;
    }

    public void setPrivilegedUserName(String privilegedUserName) {
        this.privilegedUserName = privilegedUserName;
    }

    public String getPrivilegedUserSurname() {
        return privilegedUserSurname;
    }

    public void setPrivilegedUserSurname(String privilegedUserSurname) {
        this.privilegedUserSurname = privilegedUserSurname;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<String> getServicePictures() {
        return servicePictures;
    }

    public void setServicePictures(Set<String> servicePictures) {
        this.servicePictures = servicePictures;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
