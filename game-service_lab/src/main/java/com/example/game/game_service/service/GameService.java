package com.example.game.game_service.service;

import com.example.game.game_service.model.Game;
import com.example.game.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }

    public Game updateGame(String id, Game updatedGame) {
        return gameRepository.findById(id).map(game -> {
            game.setTitle(updatedGame.getTitle());
            game.setGenre(updatedGame.getGenre());
            game.setDeveloper(updatedGame.getDeveloper());
            game.setReleaseYear(updatedGame.getReleaseYear());
            return gameRepository.save(game);
        }).orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

}
