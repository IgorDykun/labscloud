package com.example.game.game_service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class ReleaseYearValidator implements ConstraintValidator<ValidReleaseYear, Integer> {

    @Override
    public void initialize(ValidReleaseYear constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer releaseYear, ConstraintValidatorContext context) {
        if (releaseYear == null) {
            return true;
        }

        int currentYear = Year.now().getValue();
        return releaseYear <= currentYear;
    }
}
