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
    private Long player1;
    @Column(name="player2_id")
    private Long player2;
    @Column(name="playtime_1")
    private LocalDateTime playtime1;
    @Column(name="playtime_2")
    private LocalDateTime playtime2;

    public Long getId() {
        return id;
    }

    public Long getPlayer1() {
        return player1;
    }

    public Long getPlayer2() {
        return player2;
    }

    public LocalDateTime getPlaytime1() {
        return playtime1;
    }

    public LocalDateTime getPlaytime2() {
        return playtime2;
    }

    public void setPlaytime1(LocalDateTime playtime1) {
        this.playtime1 = playtime1;
    }

    public void setPlaytime2(LocalDateTime playtime2) {
        this.playtime2 = playtime2;
    }
}

