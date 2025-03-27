package com.example.game.game_service.mapper;

import com.example.game.game_service.dto.GameDTO;
import com.example.game.game_service.model.Game;
import com.example.game.game_service.model.Player;
import com.example.game.game_service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    @Autowired
    private PlayerRepository playerRepository;


    public GameDTO toDTO(Game game) {
        List<String> playerIds = game.getPlayerIds().stream()
                .map(Player::getId)
                .collect(Collectors.toList());

        return new GameDTO(
                game.getId(),
                game.getTitle(),
                game.getGenre(),
                game.getDeveloper(),
                game.getReleaseYear(),
                playerIds
        );
    }


    public Game toModel(GameDTO gameDTO) {
        List<Player> players = playerRepository.findAllById(gameDTO.getPlayerIds());

        return new Game(
                gameDTO.getTitle(),
                gameDTO.getGenre(),
                gameDTO.getDeveloper(),
                gameDTO.getReleaseYear(),
                players
        );
    }
}

