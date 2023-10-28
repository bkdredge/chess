package result;

import chess.ChessGame;

/**
 * The result of a Join Game request.
 */
public class JoinGameResult {
    private ChessGame.TeamColor playerColor;
    private int gameID;
    private String message;
    public JoinGameResult(ChessGame.TeamColor playerColor,int gameID,String message){
        this.playerColor=playerColor; this.gameID=gameID; this.message=message;
    }
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
    public ChessGame.TeamColor getPlayerColor() {return playerColor;}
    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }
    public int getGameID() {return gameID;}
    public void setGameID(int gameID) {this.gameID = gameID;}
}
