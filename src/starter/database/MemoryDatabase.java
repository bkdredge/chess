package database;

import model.AuthToken;
import model.Game;
import model.User;
import chess.ChessGameImpl;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A static database object, in place of a real database.
 * The class for this object includes all CRUD operations.
 * (create, read, update, delete; also, clear and empty)
 */
public class MemoryDatabase implements Database{
    private static HashMap<Integer, HashMap<String, Object>> games = new HashMap<>();
    private static HashMap<String, String> authTokens = new HashMap<>();
    private static HashMap<String, HashMap<String, String>> users = new HashMap<>();
    //C R U D

    //CREATE
    public void createGameInDatabase(Game game) {
        games.put(game.getGameID(), new HashMap<String, Object>());
        games.get(game.getGameID()).put("gameName", game.getGameName());
        games.get(game.getGameID()).put("whiteUsername", null);
        games.get(game.getGameID()).put("blackUsername", null);
        games.get(game.getGameID()).put("game", new ChessGameImpl());
    }
    public void createAuthTokenInDatabase(AuthToken authToken) {
        authTokens.put(authToken.getAuthToken(), authToken.getUsername());
    }
    public void createUserInDatabase(User user) {
        users.put(user.getUsername(), new HashMap<String, String>());
        users.get(user.getUsername()).put("password", user.getPassword());
        users.get(user.getUsername()).put("email", user.getEmail());
    }

    //READ
    public Game readGameInDatabase(Integer gameID) {
        if (noGamesInDatabase()) {return null;}
        if (games.get(gameID) == null) {return null;}
        var gameInfo = games.get(gameID);
        var game = new Game();
        game.setGameID(gameID);
        game.setGame((ChessGameImpl) gameInfo.get("game"));
        game.setGameName((String) gameInfo.get("gameName"));
        game.setBlackUsername((String) gameInfo.get("blackUsername"));
        game.setWhiteUsername((String) gameInfo.get("whiteUsername"));
        return game;
    }
    public ArrayList<Game> readAllGamesInDatabase() {
        var allGames = new ArrayList<Game>();
        for (Integer gameID : games.keySet()) {
            allGames.add(readGameInDatabase(gameID));
        }
        return allGames;
    }
    public User readUserInDatabase(String username) {
        if (noUsersInDatabase()) {return null;}
        else if (users.get(username) == null) {return null;}
        return new User(username, users.get(username).get("password"), users.get(username).get("email"));
    }
    public AuthToken readAuthTokenInDatabase(String authToken) {
        if (noAuthInDatabase()) {return null;}
        if (authTokens.get(authToken) == null) {return null;}
        return new AuthToken(authToken, authTokens.get(authToken));
    }

    //UPDATE
    public void updateGameInDatabase(Game game) {
        games.get(game.getGameID()).put("whiteUsername", game.getWhiteUsername());
        games.get(game.getGameID()).put("blackUsername", game.getBlackUsername());
        games.get(game.getGameID()).put("gameName", game.getGameName());
        games.get(game.getGameID()).put("game", game.getGame());
    }
    public void updateUserInDatabase(User user) {
        users.get(user.getUsername()).put("password", user.getPassword());
        users.get(user.getUsername()).put("email", user.getEmail());
    }

    //DELETE
    public void deleteGameFromDatabase(Integer gameID) {
        games.remove(gameID);
    }
    public void deleteAuthTokenFromDatabase(String authToken) {
        authTokens.remove(authToken);
    }
    public void deleteUserFromDatabase(String username) {users.remove(username);}

    //CLEAR
    public void clearGamesInDatabase() {
        if (games != null) {games.clear();}
    }
    public void clearUsersInDatabase() {
        if (users != null) {users.clear();}
    }
    public void clearAuthTokensInDatabase() {
        if (authTokens != null) {authTokens.clear();}
    }

    //EMPTY
    public boolean noGamesInDatabase() {
        return games.isEmpty();
    }
    public boolean noUsersInDatabase() {
        return users.isEmpty();
    }
    public boolean noAuthInDatabase() {
        return authTokens.isEmpty();
    }


}
