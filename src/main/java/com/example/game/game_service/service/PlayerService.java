package com.example.game.game_service.service;

import com.example.game.game_service.dto.PlayerDTO;
import com.example.game.game_service.mapper.PlayerMapper;
import com.example.game.game_service.model.Game;
import com.example.game.game_service.model.Player;
import com.example.game.game_service.repository.GameRepository;
import com.example.game.game_service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {

        List<Game> games = gameRepository.findAllById(playerDTO.getGameIds());


        if (games.size() != playerDTO.getGameIds().size()) {
            throw new RuntimeException("Some games were not found with the given IDs.");
        }


        Player player = new Player(null, playerDTO.getUsername(), playerDTO.getAge(), games);
        Player savedPlayer = playerRepository.save(player);

        for (Game game : games) {
            game.getPlayerIds().add(savedPlayer);
            gameRepository.save(game);
        }

        return playerMapper.toDTO(savedPlayer);
    }


    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<PlayerDTO> getPlayerById(String id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(playerMapper::toDTO);
    }


    public PlayerDTO updatePlayer(String id, PlayerDTO playerDTO) {
        return playerRepository.findById(id).map(existingPlayer -> {

            existingPlayer.setUsername(playerDTO.getUsername());
            existingPlayer.setAge(playerDTO.getAge());
            existingPlayer.setGames(gameRepository.findAllById(playerDTO.getGameIds()));  // Оновлюємо ігри

            Player updatedPlayer = playerRepository.save(existingPlayer);

            return playerMapper.toDTO(updatedPlayer);
        }).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public void deletePlayer(String playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();


            List<Game> games = gameRepository.findAllById(
                    player.getGames().stream().map(Game::getId).collect(Collectors.toList())
            );


            for (Game game : games) {
                game.getPlayerIds().removeIf(p -> p.getId().equals(playerId));
                gameRepository.save(game);
            }

            playerRepository.deleteById(playerId);
        } else {
            throw new RuntimeException("Player not found with id: " + playerId);
        }
    }

}
