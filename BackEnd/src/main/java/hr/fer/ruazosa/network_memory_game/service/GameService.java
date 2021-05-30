package hr.fer.ruazosa.network_memory_game.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import hr.fer.ruazosa.network_memory_game.entity.Game;
import hr.fer.ruazosa.network_memory_game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameService implements  IGameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public boolean sendNotifToChallenged(Game players) {
        String response = null;

        Message message= Message.builder()
                .putData("Call for play", "Do you want to play?")
                .putData("challenger",players.getChallenger().getUsername())
                .setNotification(Notification.builder().setTitle("Call for play").setBody("Do you want to play?").build())
                .setToken(players.getChallenged().getToken())
                .build();
        try {
            response= FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if(response != null)
            return true;
        else return false;
    }

    @Override
    public boolean sendNotifGameCancelled(String token) {
        String response = null;
        Message message= Message.builder()
                .putData("Game cancelled", "Challenger cancelled the game")
                .setToken(token)
                .build();
        try {
            response= FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if(response != null)
            return true;
        else return false;
    }

    // return true if challenger won, false otherwise
    @Override
    public boolean challengerFinished(long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(new Long(gameId));
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            if (game.getChallengedTime() == null) {
                gameRepository.updateChallengerTime(gameId, LocalDateTime.now());
                game.getChallenger().setWins(game.getChallenger().getWins()+1);
                Message message= Message.builder()
                        .putData("Notif for loser", "Game status")
                        .setToken(game.getChallenged().getToken())
                        .build();
                try {
                    String response = FirebaseMessaging.getInstance().send(message);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                }
                return true;
            }
            else {
                gameRepository.updateChallengerTime(gameId, LocalDateTime.now());
                return false;
            }
        }
        else {
            throw new RuntimeException("No started game with specified id:" + gameId);
        }
    }

    // return true if challenged won, false otherwise
    @Override
    public boolean challengedFinished(long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(new Long(gameId));
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            if (game.getChallengerTime() == null) {
                gameRepository.updateChallengedTime(gameId, LocalDateTime.now());
                game.getChallenged().setWins(game.getChallenged().getWins()+1);
                Message message= Message.builder()
                        .putData("Notif for loser", "Game status")
                        .setToken(game.getChallenger().getToken())
                        .build();
                try {
                    String response = FirebaseMessaging.getInstance().send(message);
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                }
                return true;
            }
            else {
                gameRepository.updateChallengedTime(gameId, LocalDateTime.now());
                return false;
            }
        }
        else {
            throw new RuntimeException("No started game with specified id:" + gameId);
        }
    }


}
