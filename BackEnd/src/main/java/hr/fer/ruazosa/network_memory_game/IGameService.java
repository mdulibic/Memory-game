package hr.fer.ruazosa.network_memory_game;

public interface IGameService {
    // initialize game
    Game createGame(Game game);
    // return true if challenger did win, false otherwise
    boolean challengerFinished(long userId);
    // return true if challenged did win, false otherwise
    boolean challengedFinished(long userId);
    // challenge player
    boolean sendNotifToChallenged(Game players);
    // challenger decided to cancel the game request
    boolean sendNotifGameCancelled(String token);
}
