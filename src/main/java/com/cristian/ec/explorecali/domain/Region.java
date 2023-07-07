package com.cristian.ec.explorecali.domain;

public enum Region {
    NorthernCalifornia, CentralCoast, SouthernCalifornia, Varies;

    public static Region parseRegion(String s) {
        if (s == null) {
            throw new NullPointerException("String cannot be null");
        }
        s = s.toLowerCase();
        switch (s) {
            case "napa/sonoma counties", "northern california" -> {
                return Region.NorthernCalifornia;
            }
            case "central coast" -> {
                return Region.CentralCoast;
            }
            case "southern california" -> {
                return Region.SouthernCalifornia;
            }
            case "varies" -> {
                return Region.Varies;
            }
            default -> throw new RuntimeException("Couldn't convert to difficulty " + s);
        }
    }

}
