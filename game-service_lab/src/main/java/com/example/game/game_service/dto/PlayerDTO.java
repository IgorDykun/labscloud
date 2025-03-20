package com.example.game.game_service.dto;

import lombok.*;

import java.util.List;
import jakarta.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String id;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Min(value = 10, message = "Age must be at least 10")
    private int age;

    private List<String> gameIds;
}
