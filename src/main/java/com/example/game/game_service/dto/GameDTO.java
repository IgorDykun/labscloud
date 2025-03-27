package com.example.game.game_service.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;
import com.example.game.game_service.validation.ValidReleaseYear;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private String id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Genre cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Genre can only contain alphabetic characters and spaces")
    private String genre;

    @NotBlank(message = "Developer cannot be empty")
    @Size(min = 3, max = 100, message = "Developer name must be between 3 and 100 characters")
    private String developer;

    @ValidReleaseYear(message = "Release year cannot be greater than the current year")
    @Min(value = 1900, message = "Release year must be greater than 1900")
    private int releaseYear;

    private List<String> playerIds;  // зберігаємо playerIds
}

