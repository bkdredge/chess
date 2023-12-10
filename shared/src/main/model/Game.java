package model;

import chess.ChessGameImpl;

/**
 * A model for a game.
 */
public class Game {
    /**
     * The game's game ID. (Int)
     */
    private int gameID;

    /**
     * The username of the game's white player. (String)
     */
    private String whiteUsername;

    /**
     * The username of the game's black player. (String)
     */
    private String blackUsername;

    /**
     * The game's name. (String)
     */
    private String gameName;

    /**
     * The game itself. (ChessGame)
     */
    private ChessGameImpl game;

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
    public Game(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGameImpl game) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.game = game;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    public String getWhiteUsername() {return whiteUsername;}
    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }
    public String getBlackUsername() {
        return blackUsername;
    }
    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public ChessGameImpl getGame() {
        return game;
    }
    public void setGame(ChessGameImpl game) {
        this.game = game;
    }
}
