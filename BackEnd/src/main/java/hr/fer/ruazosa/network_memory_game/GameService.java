package hr.fer.ruazosa.network_memory_game;

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

    // return true if challenger won, false otherwise
    @Override
    public boolean challengerFinished(long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(new Long(gameId));
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            if (game.getChallengedTime() == null) {
                gameRepository.updateChallengerTime(gameId, LocalDateTime.now());
                game.getChallenger().setWins(game.getChallenger().getWins()+1);
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
