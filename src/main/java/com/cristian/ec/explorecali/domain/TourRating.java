package com.cristian.ec.explorecali.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TourRating {

    @EmbeddedId
    private TourRatingPk pk;
    @Column(nullable = false)
    private Integer rating;
    @Column
    private String comment;

    public TourRating() {
    }

    public TourRating(TourRatingPk pk, Integer rating, String comment) {
        this.pk = pk;
        this.rating = rating;
        this.comment = comment;
    }

    public TourRating(Tour tour, Integer customerId, Integer rating) {
        this.pk = new TourRatingPk(tour, customerId);
        this.comment = null;
        this.rating = rating;
    }

    public TourRatingPk getPk() {
        return pk;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
