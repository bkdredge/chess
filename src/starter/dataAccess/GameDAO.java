package dataAccess;

import model.Game;
import chess.ChessGame;
import chess.ChessGameImpl;
import dataAccess.DataAccessException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Data Access Object for games in the database.
 */
public class GameDAO {
    private Collection<Game>games = new HashSet<Game>();

    /**
     * A method for inserting a name game into the database.
     * @param game
     * @throws DataAccessException
     */
    public void insertGameIntoDatabase(Game game) throws DataAccessException {
        games.add(game);
    }

    /**
     * A method for retrieving a single game from the database,
     * from a gameID (String)
     * @param gameID
     * @throws DataAccessException
     */
    public Game retrieveGameFromDatabase(int gameID) throws DataAccessException{
        for(var game:games) {
            if(game.getGameID()==gameID) {
                return game;
            }
        }
        return null;
    }

    /**
     * A method for retrieving all games from the database.
     * This is in the form of a collection of games.
     * @throws DataAccessException
     */
    public Collection<Game> retrieveAllGamesFromDatabase() throws DataAccessException{
        return games;
    }

    /**
     * A method for claiming a spot in the game.
     * The player's name is provided,
     * and the player is saved as either the
     * whitePlayer or blackPlayer in the database.
     * @param username
     * @throws DataAccessException
     */
    public void assignTeamInGame(String username, int gameID, String color) throws DataAccessException{
        for(var game:games) {
            if (game.getGameID()==gameID) {
                if(color=="BLACK") {
                    game.setBlackUsername(username);
                } else if (color=="WHITE") {
                    game.setWhiteUsername(username);
                }
            }
        }
    } // claimSpot

    /**
     * Updates a game,
     * replacing chessGame string corresponding to a given gameID
     * with a new chessGame string.
     * @param gameToUpdate
     * @throws DataAccessException
     */
    public void updateGameInDatabase(Game gameToUpdate) throws DataAccessException{
        for(var game:games) {
            if (game.getGameID()==gameToUpdate.getGameID()) {
                game=gameToUpdate;
            }
        }
    }

    /**
     * Removes a single game from the database.
     * @param gameToRemove
     * @throws DataAccessException
     */
    public void removeGameFromDatabase(Game gameToRemove) throws DataAccessException{
        for(var game:games) {
            if (game.getGameID()==gameToRemove.getGameID()) {
                games.remove(game);
            }
        }
    }

    /**
     * Remove all games from the database.
     * @throws DataAccessException
     */
    public void clearGamesInDatabase() throws DataAccessException{games.clear();}
}
