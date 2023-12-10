package database;

import dataAccess.DataAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * DatabaseAccess is responsible for creating connections to the database. Connections are
 * managed with a simple pool in order to increase performance. To obtain and
 * use connections represented by this class use the following pattern.
 *
 * <pre>
 *  public boolean example(String selectStatement, DatabaseAccess db) throws DataAccessException{
 *    var conn = db.getConnection();
 *    try (var preparedStatement = conn.prepareStatement(selectStatement)) {
 *        return preparedStatement.execute();
 *    } catch (SQLException ex) {
 *        throw new DataAccessException(ex.toString());
 *    } finally {
 *        db.returnConnection(conn);
 *    }
 *  }
 * </pre>
 */
public class DatabaseAccess {
    /* HELPFUL RESOURCES
    Phase 4 pass off
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/chess/4-database/database.md
    Example code
    // database access
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/instruction/db-sql/example-code/DatabaseAccessExample.java
    // relational databases
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/instruction/db-jdbc/db-jdbc.md
    DatabaseAccess SQL
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/instruction/db-sql/db-sql.md
    Getting started
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/chess/4-database/getting-started.md
    ---
    All project requirements
    https://github.com/softwareconstruction240/softwareconstruction/blob/main/chess/chess.md
     */

    public static final String DB_NAME = "chess";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "@Dr3dg3hamm3r";

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306";

    private final LinkedList<Connection> connections = new LinkedList<>();

    public Connection getConnection() throws DataAccessException {
        try {
            Connection connection;
            if (connections.isEmpty()) {
                connection = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
                connection.setCatalog(DB_NAME);
            } else {
                connection = connections.removeFirst();
            }
            return connection;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    /*
    synchronized public void returnConnection(Connection connection) {
        connections.add(connection);
    }
*/
    public void closeConnection(Connection connection) throws DataAccessException {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }
        }
    }
}

