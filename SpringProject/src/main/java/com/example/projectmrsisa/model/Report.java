package com.example.projectmrsisa.model;

import javax.persistence.*;

@Entity
@Table(name="reports")
public class Report {
    @Id
    @SequenceGenerator(name = "myReportSeqGen", sequenceName = "myReportSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myReportSeqGen")
    @Column(name="id", unique = true, nullable = false)
    private Integer id;
    @Column(name="report", nullable = false)
    private String report;
    @Column(name="isNegative")
    private boolean isNegative;
    @Column(name="hasShowedUp")
    private boolean hasShowedUp;
    @Column(name="isPenalized")
    private boolean isPenalized;
    @Column(name="isAnswered")
    private boolean isAnswered;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    private Client client;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privilegedUser", nullable = false)
    private User privilegedUser;

    public Report() {
    }

    public Report(Integer id, String report, boolean isNegative, boolean hasShowedUp, boolean isPenalized,
                  boolean isAnswered, Client client, User privilegedUser) {
        this.id = id;
        this.report = report;
        this.isNegative = isNegative;
        this.hasShowedUp = hasShowedUp;
        this.isPenalized = isPenalized;
        this.isAnswered = isAnswered;
        this.client = client;
        this.privilegedUser = privilegedUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }

    public boolean isHasShowedUp() {
        return hasShowedUp;
    }

    public void setHasShowedUp(boolean hasShowedUp) {
        this.hasShowedUp = hasShowedUp;
    }

    public boolean isPenalized() {
        return isPenalized;
    }

    public void setPenalized(boolean penalized) {
        isPenalized = penalized;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getPrivilegedUser() {
        return privilegedUser;
    }

    public void setPrivilegedUser(User privilegedUser) {
        this.privilegedUser = privilegedUser;
    }
}