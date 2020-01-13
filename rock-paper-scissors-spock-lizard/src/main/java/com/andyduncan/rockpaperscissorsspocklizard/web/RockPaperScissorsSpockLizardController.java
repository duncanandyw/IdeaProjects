package com.andyduncan.rockpaperscissorsspocklizard.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.lang.Thread.sleep;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RockPaperScissorsSpockLizardController {

    // Get all the choices that are usable for the UI.
    @RequestMapping(value = "/choices", method = GET, produces = "application/json")
    public String getChoices() {
        String choiceString = "";

        // order below is from specification
        choiceString = "[{\"id\":1,\"name\":\"rock\"},"
                + "{\"id\":2,\"name\":\"paper\"},"
                + "{\"id\":3,\"name\":\"scissors\"},"
                + "{\"id\":4,\"name\":\"lizard\"},"
                + "{\"id\":5,\"name\":\"spock\"}]";

        return choiceString;
    }

    // Get a randomly generated choice.
    @RequestMapping(value = "/choice", method = GET, produces = "application/json")
    public String getChoice() {
        BodyResponse bodyResponse = new BodyResponse();
        int id = -1;
        String choice = "";

        JsonObject convertedObject = new Gson().fromJson(
                retrieveRandom(bodyResponse).getText(), JsonObject.class);

        id = Math.round(
                (int) (convertedObject.get("random_number").getAsDouble() / 20) + 1
        );

        switch (id) {
            case 1:
                choice = "{\"id\":1,\"name\":\"rock\"}";
                break;
            case 2:
                choice = "{\"id\":2,\"name\":\"paper\"}";
                break;
            case 3:
                choice = "{\"id\":3,\"name\":\"scissors\"}";
                break;
            case 4:
                choice = "{\"id\":4,\"name\":\"lizard\"}";
                break;
            case 5:
                choice = "{\"id\":5,\"name\":\"spock\"}";
                break;
            default:
        }

        return choice;
    }

    //  Play a round against a computer opponent.
    @RequestMapping(value = "/play", method = POST, consumes = "application/json",
            produces = "application/json")
    public String postPlay(@RequestBody String requestBody) {
        BodyResponse bodyResponse = new BodyResponse();
        ConstantValues cV = new ConstantValues();
        SingletonScore singletonScore = SingletonScore.getInstance();
        int computer = -1;
        int player = -1;
        int result = -1;
        String resultStr = "";

        // generate computer choice
        JsonObject convertedObject = new Gson().fromJson(
                retrieveRandom(bodyResponse).getText(), JsonObject.class);

        computer = Math.round(
                (int) (convertedObject.get("random_number").getAsDouble() / 20) + 1
        );

        switch(requestBody) {
            case "{\"player\":1}":
                player = cV.ROCK;
                break;
            case "{\"player\":2}":
                player = cV.PAPER;
                break;
            case "{\"player\":3}":
                player = cV.SCISSORS;
                break;
            case "{\"player\":4}":
                player = cV.LIZARD;
                break;
            case "{\"player\":5}":
                player = cV.SPOCK;
                break;
            default:
                System.err.println("malformed post");
        }

        result = determineWinner(player, computer);

        switch (result) {
            case 6:
                resultStr = "\"win\"";
                singletonScore.setPlayerWins(
                        singletonScore.getPlayerWins() + 1
                );
                break;
            case 7:
                singletonScore.setComputerWins(
                        singletonScore.getComputerWins() + 1
                );
                resultStr = "\"lose\"";
                break;
            case 8:
                singletonScore.setTies(
                        singletonScore.getTies() + 1
                );
                resultStr = "\"tie\"";
                break;
        }

        requestBody = "{\"results\":" + resultStr + ","
                        + "\"player\":" + player + ","
                        + "\"computer\":" + computer + "}";

        return requestBody;
    }

    // find who won our game between player and computer
    // for input, 1 = rock, 2 = paper, 3 = scissors, 4 = lizard, 5 = spock
    // for output, 0 = player wins, 1 = computer wins, 2 = tie
    // bitmask?
    public int determineWinner(int player, int computer) {
        ConstantValues cV = new ConstantValues();

        if (player == computer)
            return cV.TIE; // tie, we don't need to check further

    // player threw rock 1
        // 1 == 1, tie already handled
        // computer threw paper 2
            // paper covers rock, computer wins
        if (cV.ROCK == player && cV.PAPER == computer)
            return cV.COMPUTER_WINS;
        // computer threw scissors 3
            // rock breaks scissors, player wins
        if (cV.ROCK == player && cV.SCISSORS == computer)
            return cV.PLAYER_WINS;
        // computer threw lizard 4
            // rock crushes lizard, player wins
        if (cV.ROCK == player && cV.LIZARD == computer)
            return cV.PLAYER_WINS;
        // computer threw spock 5
            // spock vaporizes rock, computer wins
        if (cV.ROCK == player && cV.SPOCK == computer)
            return cV.COMPUTER_WINS;

    // player threw paper 2
        // computer threw rock 1
            // paper covers rock, player wins
        if (cV.PAPER == player && cV.ROCK == computer)
            return cV.PLAYER_WINS;
        // 2 == 2, tie already handled
        // computer threw scissors 3
            // scissors cuts paper, computer wins
        if (cV.PAPER == player && cV.SCISSORS == computer)
            return cV.COMPUTER_WINS;
        // computer threw lizard 4
            // lizard eats paper, computer wins
        if (cV.PAPER == player && cV.LIZARD == computer)
            return cV.COMPUTER_WINS;
        // computer threw spock 5
            // paper disproves spock, player wins
        if (cV.PAPER == player && cV.SPOCK == computer)
            return cV.PLAYER_WINS;

    // player threw scissors 3
        // computer threw rock 1
            // rock breaks scissors, computer wins
        if (cV.SCISSORS == player && cV.ROCK == computer)
            return cV.COMPUTER_WINS;
        // computer threw paper 2
            // scissors cut paper, player wins
        if (cV.SCISSORS == player && cV.PAPER == computer)
            return cV.PLAYER_WINS;
        // 3 == 3, tie already handled
        // computer threw lizard 4
            // scissors decapitate lizard, player wins
        if (cV.SCISSORS == player && cV.LIZARD == computer)
            return cV.PLAYER_WINS;
        // computer threw spock 5
            // spock smashes scissors, computer wins
        if (cV.SCISSORS == player && cV.SPOCK == computer)
            return cV.COMPUTER_WINS;

    // player threw lizard 4
        // computer threw rock 1
            // rock crushes lizard, computer wins
        if (cV.LIZARD == player && cV.ROCK == computer)
            return cV.COMPUTER_WINS;
        // computer threw paper 2
            // lizard eats paper, player wins
        if (cV.LIZARD == player && cV.PAPER == computer)
            return cV.PLAYER_WINS;
        // computer threw scissors 3
            // scissors decapitate lizard, computer wins
        if (cV.LIZARD == player && cV.SCISSORS == computer)
            return cV.COMPUTER_WINS;
        // 4 == 4, tie already handled
        // computer threw spock 5
            // lizard poisons spock, player wins
        if (cV.LIZARD == player && cV.SPOCK == computer)
            return cV.PLAYER_WINS;

    // player threw spock 5
        // computer threw rock 1
            // spock vaporizes rock, player wins
        if (cV.SPOCK == player && cV.ROCK == computer)
            return cV.PLAYER_WINS;
        // computer threw paper 2
            // paper disproves spock, computer wins
        if (cV.SPOCK == player && cV.PAPER == computer)
            return cV.COMPUTER_WINS;
        // computer threw scissors 3
            // spock smashes scissors, player wins
        if (cV.SPOCK == player && cV.SCISSORS == computer)
            return cV.PLAYER_WINS;
        // computer threw lizard 4
            // lizard poisons spock, computer wins
        if (cV.SPOCK == player && cV.LIZARD == computer)
            return cV.COMPUTER_WINS;
        // 5 == 5, tie already handled

        // else error
        return -1;
    }

// adapted from
// https://stackoverflow.com/questions/50223891/how-to-extract-response-header-status-code-from-spring-5-webclient-clientrespo/50403667
// network latency?  requirements?
    public BodyResponse retrieveRandom(BodyResponse bodyResponse) {

        Mono<ClientResponse> clientResponse = WebClient.builder().build()
                .get().uri("http://codechallenge.boohma.com/random")
                .exchange();

        clientResponse.subscribe((response) -> {

            // here you can access headers and status code
            ClientResponse.Headers headers = response.headers();
            HttpStatus statusCode = response.statusCode();

            Mono<String> bodyToMono = response.bodyToMono(String.class);
            // the second subscribe to access the body
            bodyToMono.subscribe((body) -> {

                // here you can access the body
                bodyResponse.setText(body);
            }, (ex) -> {
                // handle error
            });
        }, (ex) -> {
            // handle network error
        });

        // give network time to respond, otherwise errors, executions out of order
        // as webclient is non-blocking
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bodyResponse;
    }

    @RequestMapping(value = "/score", method = GET, produces = "json/application")
    public String getScore() {
        SingletonScore singletonScore = SingletonScore.getInstance();
        int player = singletonScore.getPlayerWins();
        int computer = singletonScore.getComputerWins();
        int ties = singletonScore.getTies();

        return "{\"player\":" + player + ","
                + "\"computer\":" + computer + ","
                + "\"ties\":" + ties + "}";
    }

    @RequestMapping(value = "/reset", method = GET)
    public void doReset() {
        SingletonScore singletonScore = SingletonScore.getInstance();

        singletonScore.reset();
    }
}
