package hr.fer.ruazosa.network_memory_game.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import hr.fer.ruazosa.network_memory_game.entity.User;
import hr.fer.ruazosa.network_memory_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkUsernameUnique(User user) {
        if (userRepository.findByUserName(user.getUsername()).isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public User loginUser(User user) {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        if (loggedUserList.isEmpty()) {
            return null;
        }
        return userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword()).get(0);
    }

    @Override
    public boolean updateUserToken(User user) {
        if (userRepository.findByUserName(user.getUsername()).get(0) == null) {
            return false;
        }
        User myUser = userRepository.findByUserName(user.getUsername()).get(0);
        myUser.setToken(user.getToken());
        return true;
    }

    @Override
    public List<User> getUsersList() {
      List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public boolean sendNotifGameAccepted(String challenger_username) {
        if (userRepository.findByUserName(challenger_username).get(0) == null) {
            return false;
        }
        User myUser = userRepository.findByUserName(challenger_username).get(0);
        String response = null;
        Message message= Message.builder()
                .putData("Challenged accepted", "Game accepted")
                .setToken(myUser.getToken())
                .build();
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if(response != null)
            return true;
        else return false;
    }
    @Override
    public boolean sendNotifGameRejected(String challenger_username) {
        if (userRepository.findByUserName(challenger_username).get(0) == null) {
            return false;
        }
        User myUser = userRepository.findByUserName(challenger_username).get(0);
        String response = null;
        Message message= Message.builder()
                .putData("Challenged rejected", "Game rejected")
                .setToken(myUser.getToken())
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
    public boolean sendNotifGameCanceled(String challenged_username) {
        if (userRepository.findByUserName(challenged_username).get(0) == null) {
            return false;
        }
        User myUser = userRepository.findByUserName(challenged_username).get(0);
        String response = null;
        Message message= Message.builder()
                .putData("Challenger canceled the game", "Game canceled")
                .setToken(myUser.getToken())
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
}
