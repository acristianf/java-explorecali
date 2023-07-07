package com.cristian.ec.explorecali.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class TourPackage {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column(length = 2000)
    private String description;
    @OneToMany
    private ArrayList<Tour> tour;

    public TourPackage(Integer id, String title, String description, ArrayList<Tour> tour) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tour = tour;
    }

    protected TourPackage() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Tour> getTour() {
        return tour;
    }

    public void setTour(ArrayList<Tour> tour) {
        this.tour = tour;
    }
}
