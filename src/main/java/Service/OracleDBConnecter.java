package Service;

import common.BrowserSetup;
import oracle.jdbc.driver.OracleDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class OracleDBConnecter {
    Logger logger = LogManager.getLogger(OracleDBConnecter.class);
    private String URL;
    private String Username;
    private String Password;

    public Connection connection;

    public OracleDBConnecter(String URL, String Username, String Password) throws SQLException {
        this.URL = URL;
        this.Username = Username;
        this.Password = Password;

        DriverManager.registerDriver(new OracleDriver());
        connection = DriverManager.getConnection(this.URL, this.Username, this.Password);

        if (connection != null) {
            logger.info("Database Connection established " + connection);

        } else {
           logger.error("Database Connection Failed" + connection);
        }


    }


}
