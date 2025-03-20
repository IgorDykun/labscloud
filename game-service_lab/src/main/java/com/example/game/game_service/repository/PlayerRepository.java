    package com.example.game.game_service.repository;

    import com.example.game.game_service.model.Player;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface PlayerRepository extends MongoRepository<Player, String> {
    }
