package dataAccess;

import database.Database;
import model.Game;
import chess.ChessGame;

import java.util.ArrayList;

/**
 * Data Access Object for games in the database.
 */
public class GameDAO {
    /**
     * A temporary database object.
     */
    private Database databaseTemp = new Database();

    /**
     * A method for inserting a name game into the database.
     * @param game
     * @throws DataAccessException
     */
    public void insertGameIntoDatabase(Game game) throws DataAccessException {
        if (!isGameInDatabase(game.getGameID())) {
            databaseTemp.createGameInDatabase(game);
        }
        else {
            throw new DataAccessException("A game with this ID already exists.");
        }
    }

    /**
     * A method for retrieving a single game from the database,
     * from a gameID (int)
     * @param gameID
     * @throws DataAccessException
     */
    public Game retrieveGameFromDatabase(int gameID) throws DataAccessException {
        if (databaseTemp.readGameInDatabase(gameID) == null) {
            throw new DataAccessException("No game exists with this ID.");
        }
        else {
            return databaseTemp.readGameInDatabase(gameID);
        }
    }

    /**
     * A method for retrieving all games from the database.
     * This is in the form of a collection of games.
     * @throws DataAccessException
     */
    public ArrayList<Game> retrieveAllGamesFromDatabase() throws DataAccessException {
        return databaseTemp.readAllGamesInDatabase();
    }

    /**
     * A method for claiming a spot in the game.
     * The player's name is provided,
     * and the player is saved as either the
     * whitePlayer or blackPlayer in the database.
     * @param username
     * @param gameID
     * @param color
     * @throws DataAccessException
     */
    public void assignTeamInGame(String username, Integer gameID, ChessGame.TeamColor color) throws DataAccessException {
        var gameInfo = databaseTemp.readGameInDatabase(gameID);
        if (gameInfo == null) {
            throw new DataAccessException("Trying to join a game that doesn't exist");
        }
        else if (color == ChessGame.TeamColor.WHITE) {
            if (gameInfo.getWhiteUsername() == null) {
                // make user white
                gameInfo.setWhiteUsername(username);
                databaseTemp.updateGameInDatabase(gameInfo);
            }
            else {
                // throw DAE
                throw new DataAccessException("White's taken.");
            }
        }
        else {
            if (gameInfo.getBlackUsername() == null) {
                // make user black
                gameInfo.setBlackUsername(username);
                databaseTemp.updateGameInDatabase(gameInfo);
            }
            else {
                // throw DAE
                throw new DataAccessException("Black's taken.");
            }
        }
    }

    /**
     * Updates a game,
     * replacing chessGame string corresponding to a given gameID
     * with a new chessGame string.
     * @param gameToUpdate
     * @throws DataAccessException
     */
    public void updateGameInDatabase(Game gameToUpdate) throws DataAccessException {
        databaseTemp.updateGameInDatabase(gameToUpdate);
    }

    /**
     * Removes a single game from the database.
     * @param gameID
     * @throws DataAccessException
     */
    public void removeGameFromDatabase(Integer gameID) throws DataAccessException {
        if (!isGameInDatabase(gameID)) {
            throw new DataAccessException("No game corresponds to this gameID");
        }
        else {
            databaseTemp.deleteGameFromDatabase(gameID);
        }
    }

    /**
     * Remove all games from the database.
     * @throws DataAccessException
     */
    public void clearGamesInDatabase() throws DataAccessException {
        databaseTemp.clearGamesInDatabase();
    }

    public boolean isGameInDatabase(Integer gameID) {
        if (noGamesInDatabase()) {return false;}
        else { return databaseTemp.readGameInDatabase(gameID) != null; }
    }

    public boolean noGamesInDatabase() {
        return databaseTemp.noGamesInDatabase();
    }
}
