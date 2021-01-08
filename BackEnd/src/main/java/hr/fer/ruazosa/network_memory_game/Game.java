package hr.fer.ruazosa.network_memory_game;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;
    @Column(name="player1_id")
    private User player1;
    @Column(name="player2_id")
    private User player2;
    private LocalDateTime playtime1;
    private LocalDateTime playtime2;

}
