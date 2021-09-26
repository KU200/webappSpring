package com.example.webapp.entity;

import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.List;

import static com.example.webapp.entity.Status.Active;
import static com.example.webapp.entity.Status.Planned;

@Entity
@Table(name = "campaigns")
@EqualsAndHashCode(of = {"name"})
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Ad> ads;

    public Campaign() {
    }

    public Campaign(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        if(startDate.isAfter(LocalDate.now()))
            status=Planned;
        else status=null;
    }

    public void setAds(List<Ad> ad) {
        this.ads = ad;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}