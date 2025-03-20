package com.example.game.game_service.service;

import com.example.game.game_service.dto.PlayerDTO;
import com.example.game.game_service.mapper.PlayerMapper;
import com.example.game.game_service.model.Game;
import com.example.game.game_service.model.Player;
import com.example.game.game_service.repository.GameRepository;
import com.example.game.game_service.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        List<Game> games = gameRepository.findAllById(playerDTO.getGameIds());
        Player player = new Player(null, playerDTO.getUsername(), playerDTO.getAge(), games);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(savedPlayer);
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
