package hr.fer.ruazosa.network_memory_game.controller;

import hr.fer.ruazosa.network_memory_game.service.IUserService;
import hr.fer.ruazosa.network_memory_game.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        // validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Map<String, Object> body = new LinkedHashMap<>();
        for (ConstraintViolation<User> violation : violations) {
            body.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!body.isEmpty()) {
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        //  check if user exists
        if (userService.checkUsernameUnique(user)) {
            userService.registerUser(user);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(user, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        // validation
        if (user == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no user JSON object in body");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "username or password parameters are empty");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            User loggedUser = userService.loginUser(user);
            if (loggedUser != null) {
                return new ResponseEntity<Object>(loggedUser, HttpStatus.OK);
            }
            else {
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("error", "no user found");
                return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
            }
        }

    }
    @PostMapping("/updateToken")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        if(userService.updateUserToken(user)){
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }return new ResponseEntity<Object>(user, HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/getUsersList")
    public ResponseEntity<List<User>> getUsersList() {
        List<User> users=userService.getUsersList();
          return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/gameAccepted/{username}")
    public ResponseEntity<Object> sendNotifGameAccepted(@PathVariable("username") String challenger_username) {
        if(userService.sendNotifGameAccepted(challenger_username)){
            return new ResponseEntity<Object>(challenger_username, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(challenger_username, HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/gameRejected/{username}")
    public ResponseEntity<Object> sendNotifGameRejected(@PathVariable("username") String challenger_username) {
        if(userService.sendNotifGameRejected(challenger_username)){
            return new ResponseEntity<Object>(challenger_username, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(challenger_username, HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/gameCanceled/{username}")
    public ResponseEntity<Object> sendNotifGameCanceled(@PathVariable("username") String challenged_username) {
        if(userService.sendNotifGameCanceled(challenged_username)){
            return new ResponseEntity<Object>(challenged_username, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(challenged_username, HttpStatus.NOT_ACCEPTABLE);
    }
}
