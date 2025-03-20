package com.example.game.game_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "players")
public class Player {
    @Id
    private String id;
    private String username;
    private int age;

    @DBRef
    private List<Game> games;
}
