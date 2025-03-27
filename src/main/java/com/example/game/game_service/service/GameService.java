package com.example.game.game_service.service;

import com.example.game.game_service.model.Game;
import com.example.game.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.game.game_service.model.Player;
import com.example.game.game_service.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;



    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(String gameId) {
        List<Player> affectedPlayers = playerRepository.findAll();

        for (Player player : affectedPlayers) {
            boolean removed = player.getGames().removeIf(game -> game.getId().equals(gameId));

            if (removed) {
                playerRepository.save(player);
            }
        }

        gameRepository.deleteById(gameId);
    }




    public Game updateGame(String id, Game updatedGame) {
        return gameRepository.findById(id).map(game -> {
            game.setTitle(updatedGame.getTitle());
            game.setGenre(updatedGame.getGenre());
            game.setDeveloper(updatedGame.getDeveloper());
            game.setReleaseYear(updatedGame.getReleaseYear());

            if (updatedGame.getPlayerIds() != null) {
                game.setPlayerIds(updatedGame.getPlayerIds());
            }

            return gameRepository.save(game);
        }).orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }


}
