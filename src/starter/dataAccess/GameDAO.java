package dataAccess;

import model.Game;
import model.User;

public class GameDAO {
    /**
     *
     */
    Game game = null;

    /**
     *
     * @param newGame
     * @throws DataAccessException
     */
    void createGame(Game newGame) throws DataAccessException {
        game=newGame;
    }
    /**
     *
     */
    void insert() {}

    /**
     *
     */
    void find(){}

    /**
     *
     */
    void findAll(){}

    /**
     *
     */
    void claimSpot(){}

    /**
     *
     */
    void updateGame(){}

    /**
     *
     */
    void remove(){}

    /**
     *
     */
    void clear(){}
}
