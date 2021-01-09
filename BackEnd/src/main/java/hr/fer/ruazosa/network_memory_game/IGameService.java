package hr.fer.ruazosa.network_memory_game;

public interface IGameService {
    Game createGame(Game game);
    // return true if challenger did win, false otherwise
    boolean challengerFinished(long userId);
    // return true if challenged did win, false otherwise
    boolean challengedFinished(long userId);
}
