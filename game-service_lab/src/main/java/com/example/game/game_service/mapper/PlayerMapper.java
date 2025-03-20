package com.example.game.game_service.mapper;

import com.example.game.game_service.dto.PlayerDTO;
import com.example.game.game_service.model.Player;
import com.example.game.game_service.model.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {
    public PlayerDTO toDTO(Player player) {
        List<String> gameIds = player.getGames().stream()
                .map(Game::getId)
                .collect(Collectors.toList());
        return new PlayerDTO(player.getId(), player.getUsername(), player.getAge(), gameIds);
    }
}
