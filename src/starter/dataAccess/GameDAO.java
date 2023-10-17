package dataAccess;

import model.Game;
import model.User;

import java.util.Collection;

/**
 * Data Access Object for games in the database.
 */
public class GameDAO {

    /**
     * A method for inserting a name game into the database.
     * @param game
     * @throws DataAccessException
     */
    void insertGameIntoDatabase(Game game) throws DataAccessException {}

    /**
     * A method for retrieving a single game from the database,
     * from a gameID (String)
     * @param gameID
     * @throws DataAccessException
     */
    Game retrieveGameFromDatabase(String gameID) throws DataAccessException{
        return null;
    }

    /**
     * A method for retrieving all games from the database.
     * This is in the form of a collection of games.
     * @throws DataAccessException
     */
    Collection<Game> retrieveAllGamesFromDatabase() throws DataAccessException{
        return null;
    }

    /**
     * A method for claiming a spot in the game.
     * The player's name is provided,
     * and the player is saved as either the
     * whitePlayer or blackPlayer in the database.
     * @param username
     * @throws DataAccessException
     */
    void assignTeamInGame(String username) throws DataAccessException{} // claimSpot

    /**
     * Updates a game,
     * replacing chessGame string corresponding to a given gameID
     * with a new chessGame string.
     * @param gameToUpdate
     * @throws DataAccessException
     */
    void updateGameInDatabase(Game gameToUpdate) throws DataAccessException{}

    /**
     * Removes a single game from the database.
     * @param gameToRemove
     * @throws DataAccessException
     */
    void removeGameFromDatabase(Game gameToRemove) throws DataAccessException{}

    /**
     * Remove all games from the database.
     * @throws DataAccessException
     */
    void clearGamesInDatabase() throws DataAccessException{}
}
