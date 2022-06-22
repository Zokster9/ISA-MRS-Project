package com.example.projectmrsisa.dto;

import java.util.List;

public class ServiceAverageRatingDTO {

    private int id;

    private String serviceName;

    private double averageRating;

    private List<String> servicePictures;

    public ServiceAverageRatingDTO() {
    }

    public ServiceAverageRatingDTO(int id, String serviceName, double averageRating, List<String> servicePictures) {
        this.id = id;
        this.serviceName = serviceName;
        this.averageRating = averageRating;
        this.servicePictures = servicePictures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getServicePictures() {
        return servicePictures;
    }

    public void setServicePictures(List<String> servicePictures) {
        this.servicePictures = servicePictures;
    }
}
