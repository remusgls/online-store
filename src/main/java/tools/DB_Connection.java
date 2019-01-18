package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import types.Config;
import types.Errors;

public class DB_Connection {
    public Connection openDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println(Errors.ERROR_DB_DRIVER_CLASS);
            System.exit(1);
        }

        Connection con = null;
        String connectionUrl = Config.getDbUrl() + Config.getDbName() + "?autoReconnect=true&useSSL=false";
        try {
            con = DriverManager.getConnection(
                    connectionUrl,
                    Config.getDbUser(),
                    Config.getDbPass());

            // disable auto save stuff
            con.setAutoCommit(false);

        } catch (SQLException e) {
            System.out.println(Errors.ERROR_DB_OPEN_CONNECTION);
            e.printStackTrace();
        }

        return con;
    }

    public void closeDBConnection(Connection dbConnection) {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(Errors.ERROR_DB_CLOSE);
            e.printStackTrace();
        }
    }
}
