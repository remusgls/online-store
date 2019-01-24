package queries;

import tools.DB_Connection;
import types.Config;
import types.Db_Fields;
import types.Errors;

import java.sql.*;

public class SQL_Cart {
    public void createUserCart(int userId) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        //check if user is invalid or doesnt exist
        if (userId == 0 ||
            !new SQL_User().checkExistingUserById(userId)) {
            new DB_Connection().closeDBConnection(connection);
            System.out.println(Errors.ERROR_DB_INVALID_MODEL_ID);

            return;
        }

        // check if cart id already exists
        if (this.checkUserCart(connection, userId)) {
            new DB_Connection().closeDBConnection(connection);
            System.out.println("User " + userId + " has cart already prepared!");

            return;
        }

        String DBFields = new Db_Fields().userCartDBFields;

        String createStatement = "create table " + new Db_Fields().cartName + userId + " " + DBFields;
        Statement statement = connection.createStatement();
        //This line has the issue
        statement.executeUpdate(createStatement);

        new DB_Connection().closeDBConnection(connection);


    }

    public boolean checkUserCart(Connection connection, int userId) throws SQLException {
        if (userId == 0) {
            return false;
        }

        String tableName = new Db_Fields().cartName + userId;

        PreparedStatement searchQuery = connection.prepareStatement("select * from information_schema.tables where table_schema = ? and table_name = ? limit 1");

        searchQuery.setString(1, Config.getDbName());
        searchQuery.setString(2, tableName);

        return searchQuery.executeQuery().next();
    }
}
