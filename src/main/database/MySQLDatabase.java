package database;

import chess.ChessGameImpl;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.AuthToken;
import model.Game;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MySQLDatabase implements Database {
    private DatabaseAccess dbAccess = new DatabaseAccess();
    public MySQLDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");

            var createAuthTable = """
            CREATE TABLE IF NOT EXISTS auth (
                authToken VARCHAR(255) NOT NULL,
                userName VARCHAR(255) NOT NULL 
            )""";

            var createGameTable = """
            CREATE TABLE IF NOT EXISTS game (
                gameID INT NOT NULL AUTO_INCREMENT,
                whiteUsername VARCHAR(255) NULL,
                blackUsername VARCHAR(255) NULL,
                gameName VARCHAR(255) NOT NULL,
                game TEXT NOT NULL,
                PRIMARY KEY (gameID)
            )""";

            var createUserTable = """
            CREATE TABLE IF NOT EXISTS user (
                username VARCHAR(255) NOT NULL,
                password VARCHAR(255) NOT NULL,
                email VARCHAR(255) NOT NULL,
                PRIMARY KEY (username)
            )""";

            try (var createTableStatement = conn.prepareStatement(createAuthTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createGameTable)) {
                createTableStatement.executeUpdate();
            }
            try (var createTableStatement = conn.prepareStatement(createUserTable)) {
                createTableStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING
    @Override public void createGameInDatabase(Game game) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("INSERT INTO game " +
                    "(whiteUsername, blackUsername, gameName, game) VALUES(?, ?, ?, ?)",
                    RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, game.getWhiteUsername()); //2 ...
                preparedStatement.setString(2, game.getBlackUsername());
                preparedStatement.setString(3, game.getGameName());
                preparedStatement.setString(4, new Gson().toJson(game));
                preparedStatement.executeUpdate();
                dbAccess.closeConnection(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING
    @Override public void createAuthTokenInDatabase(AuthToken authToken) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("INSERT INTO auth (authToken, userName) VALUES(?, ?)", RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, authToken.getAuthToken());
                preparedStatement.setString(2, authToken.getUsername());
                preparedStatement.executeUpdate();
                dbAccess.closeConnection(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING
    @Override public void createUserInDatabase(User user) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("INSERT INTO user (username, password, email) VALUES (?, ?, ?)")) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.executeUpdate();
                dbAccess.closeConnection(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING
    @Override public Game readGameInDatabase(Integer gameID) {
        if (noGamesInDatabase()) {return null;}
        Game game = null;
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM game WHERE gameID = ?")) {
                preparedStatement.setInt(1, gameID);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        //int foundGameID = resultSet.getInt("gameID");
                        String whiteUsername = resultSet.getString("whiteUsername");
                        String blackUsername = resultSet.getString("blackUsername");
                        String gameName = resultSet.getString("gameName");
                        var gson = new Gson().fromJson(resultSet.getString("game"), ChessGameImpl.class);
                        game= new Game(gameID, whiteUsername, blackUsername, gameName, gson);
                        dbAccess.closeConnection(conn);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return game;
    } // WORKING I THINK?
    @Override public ArrayList<Game> readAllGamesInDatabase() {
        var listOfAllGames = new ArrayList<Game>();
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM game")) {
                try (var resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int foundGameID = resultSet.getInt("gameID");
                        String whiteUsername = resultSet.getString("whiteUsername");
                        String blackUsername = resultSet.getString("blackUsername");
                        String gameName = resultSet.getString("gameName");
                        var gson = new Gson().fromJson(resultSet.getString("game"), ChessGameImpl.class);
                        listOfAllGames.add(new Game(foundGameID, whiteUsername, blackUsername, gameName, gson));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return listOfAllGames;
    } // WORKING?
    @Override public User readUserInDatabase(String username) {
        User user = null;
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM user WHERE username = ?")) {
                preparedStatement.setString(1, username);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String foundUsername = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String email = resultSet.getString("email");
                        user = new User(foundUsername, password, email);
                    }
                }
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return user;
    } // WORKING
    @Override public AuthToken readAuthTokenInDatabase(String authToken) {
        AuthToken auth = null;
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM auth WHERE authToken = ?")) {
                preparedStatement.setString(1, authToken);
                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String foundAuthToken = resultSet.getString("authToken");
                        String username = resultSet.getString("username");
                        auth = new AuthToken(foundAuthToken, username);
                    }
                }
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return auth;
    } // WORKING?
    @Override
    public void updateGameInDatabase(Game game) {
        int foundGameID = game.getGameID();
        Game existingGame = readGameInDatabase(foundGameID);
        existingGame.setWhiteUsername(game.getWhiteUsername());
        existingGame.setBlackUsername(game.getBlackUsername());
        existingGame.setGameName(game.getGameName());
        existingGame.setGame(game.getGame());

        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("UPDATE game " +
                    "SET whiteUsername = ?, blackUsername = ?, gameName = ?, game = ? " +
                    "WHERE gameID = ?")) {
                preparedStatement.setString(1, existingGame.getWhiteUsername());
                preparedStatement.setString(2, existingGame.getBlackUsername());
                preparedStatement.setString(3, existingGame.getGameName());
                preparedStatement.setString(4, new Gson().toJson(existingGame));
                preparedStatement.setInt(5, existingGame.getGameID());
                preparedStatement.executeUpdate();
                dbAccess.closeConnection(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void updateUserInDatabase(User user) {
        String username = user.getUsername();
        User existingUser = readUserInDatabase(username);
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("UPDATE users " +
                    "SET password = ?, email = ? " +
                    "WHERE username = ?")) {
                preparedStatement.setString(1, existingUser.getPassword());
                preparedStatement.setString(2, existingUser.getEmail());
                preparedStatement.setString(3, existingUser.getUsername());
                preparedStatement.executeUpdate();
                dbAccess.closeConnection(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Override public void deleteGameFromDatabase(Integer gameID) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("DELETE FROM game WHERE gameID = ?")) {
                preparedStatement.setInt(1, gameID);
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // NOT SURE
    @Override public void deleteAuthTokenFromDatabase(String authToken) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("DELETE FROM auth WHERE authToken = ?")) {
                preparedStatement.setString(1, authToken);
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // NOT SURE
    @Override public void deleteUserFromDatabase(String username) {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("DELETE FROM user WHERE username = ?")) {
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // NOT SURE
    @Override public void clearGamesInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("TRUNCATE TABLE game")) {
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
    @Override public void clearUsersInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("TRUNCATE TABLE user")) {
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
    @Override public void clearAuthTokensInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("TRUNCATE TABLE auth")) {
                preparedStatement.executeUpdate();
            }
            dbAccess.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
    @Override public boolean noGamesInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var statement = conn.createStatement()) {
                try (var resultSet = statement.executeQuery("SELECT COUNT(*) FROM game")) {
                    resultSet.next();
                    return resultSet.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
    @Override public boolean noUsersInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var statement = conn.createStatement()) {
                try (var resultSet = statement.executeQuery("SELECT COUNT(*) FROM user")) {
                    resultSet.next();
                    return resultSet.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
    @Override public boolean noAuthInDatabase() {
        try (var conn = dbAccess.getConnection()) {
            conn.setCatalog("chess");
            try (var statement = conn.createStatement()) {
                try (var resultSet = statement.executeQuery("SELECT COUNT(*) FROM auth")) {
                    resultSet.next();
                    return resultSet.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    } // WORKING SO FAR
}
