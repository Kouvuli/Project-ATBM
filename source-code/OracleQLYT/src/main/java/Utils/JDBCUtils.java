package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connection;

    public  JDBCUtils(String username,String password) throws ClassNotFoundException, SQLException {


            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);

            // Create a connection to the database
            String serverName = "127.0.0.1";
            String portNumber = "1521";
            String sid = "xe";
//            String service_name="pdb";
            String url  = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;


            connection = DriverManager.getConnection(url, username, password);



    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();

    }
}
