package hr.fer.ruazosa.network_memory_game.repository;
import hr.fer.ruazosa.network_memory_game.entity.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    // update challenger time
    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.challengerTime = ?2 WHERE g.id = ?1")
    void updateChallengerTime(Long gameId, LocalDateTime challengerTime);

    // update challenged time
    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.challengedTime = ?2 WHERE g.id = ?1")
    void updateChallengedTime(Long gameId, LocalDateTime challengedTime);


}
