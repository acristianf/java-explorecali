package com.cristian.ec.explorecali.domain;

public enum Difficulty {
    Easy, Medium, Difficult, Varies;

    public static Difficulty parseDifficulty(String s) throws RuntimeException {
        if (s == null) {
            throw new NullPointerException("String cannot be null");
        }
        s = s.toLowerCase();
        switch (s) {
            case "easy" -> {
                return Difficulty.Easy;
            }
            case "medium" -> {
                return Difficulty.Medium;
            }
            case "difficult" -> {
                return Difficulty.Difficult;
            }
            case "varies" -> {
                return Difficulty.Varies;
            }
            default -> throw new RuntimeException("Couldn't convert to difficulty " + s);
        }
    }
}
