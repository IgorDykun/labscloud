package com.example.game.game_service.controller;

import com.example.game.game_service.dto.GameDTO;
import com.example.game.game_service.model.Game;
import com.example.game.game_service.service.GameService;
import com.example.game.game_service.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @GetMapping
    public List<GameDTO> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return games.stream()
                .map(gameMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable String id) {
        Optional<Game> game = gameService.getGameById(id);
        return game.map(g -> ResponseEntity.ok(gameMapper.toDTO(g)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameDTO> saveGame(@Valid @RequestBody GameDTO gameDTO) {
        Game game = gameMapper.toModel(gameDTO);
        Game savedGame = gameService.saveGame(game);
        return ResponseEntity.ok(gameMapper.toDTO(savedGame));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable String id, @Valid @RequestBody GameDTO gameDTO) {
        Game game = gameMapper.toModel(gameDTO);
        Game updatedGame = gameService.updateGame(id, game);
        return ResponseEntity.ok(gameMapper.toDTO(updatedGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
