package hr.fer.ruazosa.network_memory_game;

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
        if(userRepository.findByUserName(user.getUsername()).get(0)==null){
            return false;
        }
        User myUser=userRepository.findByUserName(user.getUsername()).get(0);
        myUser.setToken(user.getToken());
        return true;
    }

    @Override
    public Long chooseWinner(Game game) {
        //koliko ja kužim playerTime je ukupno vrijeme te se ono neće setirati ako je igtrač izašao iz igre
        //sad nez šta ako obojica izađu, onda se niti jedno vrijeme neće settirati
        //i ko je onda pobijedio?
        //zato je ovako napravaljeno kao da izbaci jednog pobijednika tj ako su obojica izašla ili obojica imaju
        //isto vrijeme pobijdnk je player1
        if (game.getPlaytime1() != null && game.getPlayer2() != null) {
            if (game.getPlaytime1().compareTo(game.getPlaytime2()) > 0) {
                return game.getPlayer2();
            }
            else {
                return  game.getPlayer1();
            }
        }
        if (game.getPlayer1() == null) {
            return game.getPlayer2();
        } else {
            return  game.getPlayer1();
        }
    }
}
