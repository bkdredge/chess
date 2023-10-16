package model;

import chess.ChessGame;
import chess.ChessGameImpl;

import java.util.Objects;

public class Game {
    /**
     *
     */
    int gameID = 0;

    /**
     *
     */
    String whiteUsername = null;

    /**
     *
     */
    String blackUsername = null;

    /**
     *
     */
    String gameName = null;

    /**
     *
     */
    ChessGame game = new ChessGameImpl();

    /**
     *
     */
    public Game() {}

    /**
     *
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

    /**
     *
     * @return
     */
    public int getGameID() {return gameID;}

    /**
     *
     * @param gameID
     */
    public void setGameID(int gameID) {this.gameID = gameID;}

    /**
     *
     * @return
     */
    public String getWhiteUsername() {return whiteUsername;}

    /**
     *
     * @param whiteUsername
     */
    public void setWhiteUsername(String whiteUsername) {this.whiteUsername = whiteUsername;}

    /**
     *
     * @return
     */
    public String getBlackUsername() {return blackUsername;}

    /**
     *
     * @param blackUsername
     */
    public void setBlackUsername(String blackUsername) {this.blackUsername = blackUsername;}

    /**
     *
     * @return
     */
    public String getGameName() {return gameName;}

    /**
     *
     * @param gameName
     */
    public void setGameName(String gameName) {this.gameName = gameName;}

    /**
     *
     * @return
     */
    public ChessGame getGame() {return game;}

    /**
     *
     * @param game
     */
    public void setGame(ChessGame game) {this.game = game;}

    /**
     *
     * @param o, the object
     * @return
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
     *
     * @return
     */
    @Override public int hashCode() {return Objects.hash(gameID, whiteUsername, blackUsername, gameName, game);}

    /**
     *
     * @return
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
