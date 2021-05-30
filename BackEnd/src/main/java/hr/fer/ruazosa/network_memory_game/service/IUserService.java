package hr.fer.ruazosa.network_memory_game.service;

import hr.fer.ruazosa.network_memory_game.entity.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    boolean updateUserToken(User user);
    List<User> getUsersList();
    // player accepted the game request
    boolean sendNotifGameAccepted(String challenger_username);
    // player declined the game request
    boolean sendNotifGameRejected(String challenger_username);
    //challeneger decided to cancel the game request
    boolean sendNotifGameCanceled(String challenged_username);
}
