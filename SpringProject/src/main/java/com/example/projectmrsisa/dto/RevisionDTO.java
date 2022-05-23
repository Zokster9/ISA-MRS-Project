package com.example.projectmrsisa.dto;

import com.example.projectmrsisa.model.Revision;

import java.util.Set;


public class RevisionDTO {
    private Integer id;
    private String revision;
    private boolean isApproved;
    private boolean isAnswered;
    private double serviceRating;
    private double ownerRating;
    private String clientName;
    private String clientSurname;
    private String ownerName;
    private String ownerSurname;
    private String serviceName;
    private Set<String> servicePictures;
    private Integer reservationId;

    public RevisionDTO(){

    }

    public RevisionDTO(Revision revision){
        this.id = revision.getId();
        this.revision = revision.getRevision();
        this.isApproved = revision.isApproved();
        this.isAnswered = revision.isAnswered();
        this.serviceRating = revision.getRating().getServiceRating();
        this.ownerRating = revision.getRating().getUserRating();
        this.clientName = revision.getReservation().getClient().getName();
        this.clientSurname = revision.getReservation().getClient().getSurname();
        this.ownerName = revision.getReservation().getService().getOwner().getName();
        this.ownerSurname = revision.getReservation().getService().getOwner().getSurname();
        this.serviceName = revision.getReservation().getService().getName();
        this.servicePictures = revision.getReservation().getService().getPictures();
        this.reservationId = revision.getReservation().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean answered) {
        isAnswered = answered;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public double getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(double ownerRating) {
        this.ownerRating = ownerRating;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
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

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
