package hr.fer.ruazosa.network_memory_game;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

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
    public boolean sendNotifToChallenged(Game players) {
        String response = null;
        Message message= Message.builder()
                .putData("Call for play","Do you want to play?")
                .putData("From:",players.getChallenger().getUsername())
                .setToken(players.getChallenged().getToken())
                .build();
        try {
            response= FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if(response!=null)
        return true;
        else return false;
    }

    @Override
    public List<String> getUsersList() {
      List<User> users= userRepository.findAll();
      List<String> usernames=null;
        for (User user : users) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }
}
