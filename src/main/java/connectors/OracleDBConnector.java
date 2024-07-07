package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.driver.OracleDriver;

/**
 * Connector class to establish a connection with Oracle Database.
 */
public class OracleDBConnector {
    private static final Logger logger = LogManager.getLogger(OracleDBConnector.class);
    
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    public OracleDBConnector(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;

        connect();
    }

    /**
     * Establishes a connection to the database.
     *
     * @throws SQLException if a database access error occurs
     */
    private void connect() throws SQLException {
        try {
            DriverManager.registerDriver(new OracleDriver());
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                logger.info("Database connection established.");
            } else {
                logger.error("Database connection failed.");
            }
        } catch (SQLException e) {
            logger.error("Database connection error: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the database connection.
     *
     * @return the database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed.");
            } catch (SQLException e) {
                logger.error("Error closing database connection: {}", e.getMessage());
            }
        }
    }
}
