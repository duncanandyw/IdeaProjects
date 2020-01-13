This is a RESTful API implementation of the game known as Rock, Paper, Scissors, Spock, Lizard (or as some call it -- Rock, Paper, Scissors, Lizard, Spock).  It produces and consumes JSONs.

It was developed in IntelliJ, using Spring Boot (Web and Webflux), as well as Gradle, and integrated with a Tomcat server (on port 8080).  It also incorporates Google's GSON for manipulating JSONs.

Testing was done using Postman and curl.  It was tracked using Git.

After cloning from Git and loading into IntelliJ, just click on the green play button to run it.  Then the endpoints can be tested in a web browser or in something like Postman or curl, as mentioned.

All endpoints described in the specification work.  Players are able to get a list of choices, retrieve a random choice over the network and play their own choice against a computer choice.  As a bonus, a scoreboard singleton keeps a tally of games between player and computer and can show results.  We can also reset the results.

The endpoints are described below, followed by some example commands and output from curl.

GET
/choices
Returns a JSON to the player of possible choices.

GET
/choice
Returns a JSON to the player of a random choice.

POST
/play
Receives a user-selected choice as a JSON and plays it against a randomly selected computer choice.  It then returns a JSON result of win, lose, or tie.

GET
/score
Returns a JSON that contains a tally of the results from play versus the computer (since the last reset).

GET
/reset
Clears the scoreboard and returns a JSON showing the results of this action.

Below are some sample curl commands / output.  Please note that using reset has no visible output, but the results can be viewed using the score endpoint.

curl http://localhost:8080/choices --request GET
[{"id":1,"name":"rock"},{"id":2,"name":"paper"},{"id":3,"name":"scissors"},{"id":4,"name":"lizard"},{"id":5,"name":"spock"}]
curl http://localhost:8080/choice --request GET
{"id":5,"name":"spock"}
curl http://localhost:8080/reset --request GET

curl http://localhost:8080/score --request GET
{"player":0,"computer":0,"ties":0}
curl -d "{\"player\":1}" -H "Content-Type: application/json" http://localhost:8080/play --request POST
{"results":"tie","player":1,"computer":1}
curl -d "{\"player\":2}" -H "Content-Type: application/json" http://localhost:8080/play --request POST
{"results":"lose","player":2,"computer":3}
curl -d "{\"player\":3}" -H "Content-Type: application/json" http://localhost:8080/play --request POST
{"results":"tie","player":3,"computer":3}
curl -d "{\"player\":4}" -H "Content-Type: application/json" http://localhost:8080/play --request POST
{"results":"win","player":4,"computer":5}
curl -d "{\"player\":5}" -H "Content-Type: application/json" http://localhost:8080/play --request POST
{"results":"win","player":5,"computer":1}
curl http://localhost:8080/score --request GET
{"player":2,"computer":1,"ties":2}
curl http://localhost:8080/reset --request GET

curl http://localhost:8080/score --request GET
{"player":0,"computer":0,"ties":0}
