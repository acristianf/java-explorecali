package com.cristian.ec.explorecali.domain;

import jakarta.persistence.*;

@Entity
public class TourPackage {
    @Id
    private String code;
    @Column
    private String title;

    public TourPackage(String code, String title) {
        this.code = code;
        this.title = title;
    }

    protected TourPackage() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
