package unitTests;

import chess.*;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for data access
 */
public class DataAccessTests {

    private ChessBoard board;

    @BeforeEach
    public void setup() {
        board = new ChessBoardImpl();
    }

    @Test
    @DisplayName("Log In A User")
    public void loginUser() {
        //FIXME Add code here
    }

    @Test
    @DisplayName("Find A User by Username")
    public void findUserByUsername() {
        //FIXME Add code here
    }

    @Test
    @DisplayName("Find A User by AuthToken")
    public void findUserByAuthToken() {
        //FIXME Add code here
    }

    @Test
    @DisplayName("Find A Game by name of Game")
    public void findGameByName() {
        //FIXME Add code here
    }

    @Test
    @DisplayName("Find A Game by game ID")
    public void findGameByID() {
        //FIXME Add code here
    }


}
