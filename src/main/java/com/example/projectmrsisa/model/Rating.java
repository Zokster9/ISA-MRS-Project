package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "userRating", nullable = false)
    private double userRating;
    @Column(name = "serviceRating", nullable = false)
    private double serviceRating;

    public Rating() {
    }

    public Rating(Integer id, double userRating, double serviceRating) {
        this.id = id;
        this.userRating = userRating;
        this.serviceRating = serviceRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }
}
