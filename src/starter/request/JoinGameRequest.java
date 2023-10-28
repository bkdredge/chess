package request;
import chess.ChessGame;
import model.Game;

/**
 * The request to join a game.
 */
public class JoinGameRequest {
    private ChessGame.TeamColor playerColor;
    private int gameID;
    public JoinGameRequest(){}
    public int getGameID(){return gameID;}
    void setGameID(int gameID){this.gameID=gameID;}
    public ChessGame.TeamColor getPlayerColor() {return playerColor;}
    public void setPlayerColor(ChessGame.TeamColor playerColor) {this.playerColor = playerColor;}
}
