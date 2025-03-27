package com.example.game.game_service.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class Game {

    private String id;
    private String title;
    private String genre;
    private String developer;
    private int releaseYear;

    @DBRef
    private List<Player> playerIds;

    public Game(String title, String genre, String developer, int releaseYear, List<Player> playerIds) {
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.playerIds = playerIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Player> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Player> playerIds) {
        this.playerIds = playerIds;
    }
}
