package hr.fer.ruazosa.network_memory_game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class GameController {
    @Autowired
    private IGameService gameService;

    @PostMapping("/createGame")
    public ResponseEntity<Object> createGame(@RequestBody Game game) {
        // validation
        if (game == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no user JSON object in body");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else if (game.getChallenger() == null || game.getChallenged() == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no challenger or challenged in JSON");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else if (game.getChallenger().getId() == null || game.getChallenged().getId() == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no challenger or challenged in JSON");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            Game createdGame = gameService.createGame(game);
            return new ResponseEntity<Object>(createdGame.getId(), HttpStatus.OK);
        }
    }

    @GetMapping("/finishChallenger/{gameId}")
    public ResponseEntity<Object> finishChallenger(@PathVariable Long gameId) {
        // validation
        if (gameId == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no game id request path");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            boolean didChallengerWin = gameService.challengerFinished(gameId);
            return new ResponseEntity<Object>(new Boolean(didChallengerWin), HttpStatus.OK);
        }
    }

    @GetMapping("/finishChallenged/{gameId}")
    public ResponseEntity<Object> finishChallenged(@PathVariable Long gameId) {
        // validation
        if (gameId == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no game id request path");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            boolean didChallengedWin = gameService.challengedFinished(gameId);
            return new ResponseEntity<Object>(new Boolean(didChallengedWin), HttpStatus.OK);
        }
    }

    @PostMapping("/sendNotifToChallenged")
    public ResponseEntity<Object> sendNotifToChallenged(@RequestBody Game game) {
        if(gameService.sendNotifToChallenged(game)){
            return new ResponseEntity<Object>(game, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(game, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/gameCancelled")
    public ResponseEntity<Object> sendNotifGameCanceled(@RequestBody String token) {
        if(gameService.sendNotifGameCancelled(token)){
            return new ResponseEntity<Object>(token, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(token, HttpStatus.NOT_ACCEPTABLE);
    }
}
