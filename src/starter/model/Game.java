package model;

import chess.ChessGame;
import chess.ChessGameImpl;

import java.util.Objects;

/**
 * A model for a game.
 */
public class Game {
    /**
     * The game's game ID. (Int)
     */
    int gameID = 0;

    /**
     * The username of the game's white player. (String)
     */
    String whiteUsername = null;

    /**
     * The username of the game's black player. (String)
     */
    String blackUsername = null;

    /**
     * The game's name. (String)
     */
    String gameName = null;

    /**
     * The game itself. (ChessGame)
     */
    ChessGame game = new ChessGameImpl();

    /**
     * An empty Game constructor.
     */
    public Game() {}

    /**
     * A fully-parametrized Game constructor.
     * @param gameID
     * @param whiteUsername
     * @param blackUsername
     * @param gameName
     * @param game
     */
    public Game(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.game = game;
    }

    public int getGameID() {return gameID;}
    public void setGameID(int gameID) {this.gameID = gameID;}
    public String getWhiteUsername() {return whiteUsername;}
    public void setWhiteUsername(String whiteUsername) {this.whiteUsername = whiteUsername;}
    public String getBlackUsername() {return blackUsername;}
    public void setBlackUsername(String blackUsername) {this.blackUsername = blackUsername;}
    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}
    public ChessGame getGame() {return game;}
    public void setGame(ChessGame game) {this.game = game;}

    /**
     * A boolean equals function, to compare games.
     * @param o, the object
     * @return true or false
     */
    @Override public boolean equals(Object o) {
        if(o==null) return false; if (o==this) return true;
        if(o.getClass()!=this.getClass()) return false;
        Game d = (Game) o;
        if(d.getGameID()!=this.getGameID()) return false;
        if(d.getGameName()!=this.getGameName()) return false;
        if(d.getWhiteUsername()!=this.getWhiteUsername()) return false;
        if(d.getBlackUsername()!=this.getBlackUsername()) return false;
        if(d.getGame()!=this.getGame()) return false;
        return true;
    }

    /**
     * A custom hashcode function
     * @return hash
     */
    @Override public int hashCode() {return Objects.hash(gameID, whiteUsername, blackUsername, gameName, game);}

    /**
     * A custom toString function
     * @return string
     */
    @Override public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", whiteUsername='" + whiteUsername + '\'' +
                ", blackUsername='" + blackUsername + '\'' +
                ", gameName='" + gameName + '\'' +
                ", game=" + game +
                '}';
    }
}
