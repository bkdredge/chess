package result;

import request.ResponseObject;

/**
 * The result of a Create Game request.
 */
public class CreateGameResult extends _Result {

    /**
     * A string to store a gameID
     */
    private int gameID;
    public CreateGameResult() {}
    public int getGameID() {return gameID;}
    public void setGameID(int gameID) {this.gameID = gameID;}
    public CreateGameResult(Integer gameID){
        this.setGameID(gameID);
        this.setCode(ResponseObject.SUCCESS);
    }
    public CreateGameResult(ResponseObject statusCode, String errorMessage){
        this.setCode(statusCode);
        this.setMessage(errorMessage);
    }
}
