package com.example.game.game_service.controller;

import com.example.game.game_service.dto.PlayerDTO;
import com.example.game.game_service.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.createPlayer(playerDTO));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable String id) {
        Optional<PlayerDTO> playerDTO = playerService.getPlayerById(id);
        return playerDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable String id, @Valid @RequestBody PlayerDTO playerDTO) {
        PlayerDTO updatedPlayerDTO = playerService.updatePlayer(id, playerDTO);
        return ResponseEntity.ok(updatedPlayerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
