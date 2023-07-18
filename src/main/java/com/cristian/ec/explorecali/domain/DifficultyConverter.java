package com.cristian.ec.explorecali.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DifficultyConverter implements AttributeConverter<Difficulty, String> {
    @Override
    public String convertToDatabaseColumn(Difficulty difficulty) {
        return difficulty.getLabel();
    }

    @Override
    public Difficulty convertToEntityAttribute(String s) {
        return Difficulty.parseDifficulty(s);
    }
}
