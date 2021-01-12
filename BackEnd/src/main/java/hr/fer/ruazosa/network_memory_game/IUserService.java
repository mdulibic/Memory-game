package hr.fer.ruazosa.network_memory_game;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    boolean updateUserToken(User user);
    boolean sendNotifToChallenged(Game players);
    List<String> getUsersList();
}
