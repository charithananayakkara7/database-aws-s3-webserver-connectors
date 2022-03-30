package Service;

import oracle.jdbc.driver.OracleDriver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class OracleDBConnecter {

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
            System.out.println("Database Connection established " + connection);

        } else {
            System.out.println("Database Connection Failed " + connection);
        }


    }


}
