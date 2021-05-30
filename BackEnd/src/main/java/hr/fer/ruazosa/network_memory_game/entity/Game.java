package hr.fer.ruazosa.network_memory_game.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;

    @NotNull
    @ManyToOne
    private User challenger;

    @NotNull
    @ManyToOne
    private User challenged;

    @Column(name="challenger_time",  columnDefinition = "TIMESTAMP")
    private LocalDateTime challengerTime;

    @Column(name="challenged_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime challengedTime;

    public User getChallenger() {
        return challenger;
    }

    public void setChallenger(User challenger) {
        this.challenger = challenger;
    }

    public User getChallenged() {
        return challenged;
    }

    public void setChallenged(User challenged) {
        this.challenged = challenged;
    }

    public LocalDateTime getChallengerTime() {
        return challengerTime;
    }

    public void setChallengerTime(LocalDateTime challengerTime) {
        this.challengerTime = challengerTime;
    }

    public LocalDateTime getChallengedTime() {
        return challengedTime;
    }

    public void setChallengedTime(LocalDateTime challengedTime) {
        this.challengedTime = challengedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

