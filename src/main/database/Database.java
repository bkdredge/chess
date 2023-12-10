package database;

import model.AuthToken;
import model.Game;
import model.User;

import java.util.ArrayList;

public interface Database {

    //CREATE
    public void createGameInDatabase(Game game);
    public void createAuthTokenInDatabase(AuthToken authToken);
    public void createUserInDatabase(User user);

    //READ
    public Game readGameInDatabase(Integer gameID);
    public ArrayList<Game> readAllGamesInDatabase();
    public User readUserInDatabase(String username);
    public AuthToken readAuthTokenInDatabase(String authToken);

    //UPDATE
    public void updateGameInDatabase(Game game);
    public void updateUserInDatabase(User user);

    //DELETE
    public void deleteGameFromDatabase(Integer gameID);
    public void deleteAuthTokenFromDatabase(String authToken);
    public void deleteUserFromDatabase(String username);

    //CLEAR
    public void clearGamesInDatabase();
    public void clearUsersInDatabase();
    public void clearAuthTokensInDatabase();

    //EMPTY
    public boolean noGamesInDatabase();
    public boolean noUsersInDatabase();
    public boolean noAuthInDatabase();
}
