package request;

import model.Game;

/**
 * The request to create a game.
 */
public class CreateGameRequest {
    String gameName;
    public CreateGameRequest(){}
    public String getGameName(){return gameName;}
}
