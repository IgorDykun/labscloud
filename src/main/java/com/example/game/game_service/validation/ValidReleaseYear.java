package com.example.game.game_service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Створюємо кастомну анотацію для валідації року
@Constraint(validatedBy = ReleaseYearValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReleaseYear {
    String message() default "Release year must not be greater than the current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
