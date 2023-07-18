package com.cristian.ec.explorecali.domain;

public enum Region {
    NorthernCalifornia("Northern California"),
    CentralCoast("Central Coast"),
    SouthernCalifornia("Southern California"),
    Varies("Varies");

    private final String label;

    Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String s) {
        for (Region value : Region.values()) {
            if (value.label.equalsIgnoreCase(s)) return value;
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}
