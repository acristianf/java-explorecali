package com.cristian.ec.explorecali.domain;

import jakarta.persistence.*;

@Entity
public class Tour {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column(length = 2000)
    private String description;
    @Column(length = 2000)
    private String blurb;
    @Column
    private Float price;
    @Column
    private Integer duration;
    @Column
    @Enumerated
    private Difficulty difficulty;
    @Column
    @Enumerated
    private Region region;
    @Column(length = 2000)
    private String bullets;
    @Column
    private String keywords;

    @ManyToOne
    private TourPackage tourPackage;

    public Tour(String title, String description, String blurb, Float price, Integer duration, Difficulty difficulty, Region region, String bullets, String keywords, TourPackage tourPackage) {
        this.title = title;
        this.description = description;
        this.blurb = blurb;
        this.price = price;
        this.duration = duration;
        this.difficulty = difficulty;
        this.region = region;
        this.bullets = bullets;
        this.keywords = keywords;
        this.tourPackage = tourPackage;
    }

    protected Tour() {
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

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getBullets() {
        return bullets;
    }

    public void setBullets(String bullets) {
        this.bullets = bullets;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }
}
