package result;

import chess.ChessGame;

import java.util.List;

/**
 * The result of a List Games request.
 */
public class ListGamesResult {
    private List<ChessGame> games;
    private String message;
    public ListGamesResult(){}
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
    public List<ChessGame> getGames() {
        return games;
    }

    public void setGames(List<ChessGame> games) {
        this.games = games;
    }
}
