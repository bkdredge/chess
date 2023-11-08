package unitTests;

import database.MemoryDatabase;
import database.MySQLDatabase;
import request.*;
import result.*;
import service.*;
import chess.ChessGameImpl;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearDataTests extends BasicFunctions{
    @Test
    void clearApp() {
        var gameDao = new GameDAO();

        var game = new Game();
        game.setGameName("Barbenheimer");
        game.setWhiteUsername("Barbie");
        game.setBlackUsername("Oppenheimer");
        game.setGame(new ChessGameImpl());

        try {
            gameDao.insertGameIntoDatabase(game);
        }
        catch (DataAccessException e) {
            System.out.println("Server error.");
        }

        if(gameDao.getDatabase().getClass().equals(MemoryDatabase.class)) {
            assertTrue(gameDao.isGameInDatabase(0), "No game was written into the DB.");
        } else if (gameDao.getDatabase().getClass().equals(MySQLDatabase.class)) {
            assertTrue(gameDao.isGameInDatabase(1), "No game was written into the DB.");
        }

        var clearService = new ClearDataService();
        var clearResult = clearService.clear();

        if(gameDao.getDatabase().getClass().equals(MemoryDatabase.class)) {
            assertFalse(gameDao.isGameInDatabase(0), "The clear DB function didn't work properly.");
        } else if (gameDao.getDatabase().getClass().equals(MySQLDatabase.class)) {
            assertFalse(gameDao.isGameInDatabase(1), "The clear DB function didn't work properly.");        }
    }
}
