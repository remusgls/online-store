package queries;

import model.cart.CartItem;
import tools.DB_Connection;
import types.Config;
import types.Db_Fields;
import types.Errors;

import java.sql.*;

public class SQL_Cart {
    public CartItem insertCartItem(int userId, int piesaId, int buc) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        //check if user is invalid or doesn't exist or not enough stoc
        if (userId == 0 ||
            this.checkUserCart(connection, userId) ||
            new SQL_Piesa().checkStoc(connection, piesaId, buc)) {
            new DB_Connection().closeDBConnection(connection);
            System.out.println(Errors.ERROR_DB_INVALID_MODEL_ID);

            return null;
        }

        if (this.searchForCartItem(connection, piesaId, userId)) {

        }

        return null;
    }

    public void createUserCart(int userId) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        //check if user is invalid or doesn't exist
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

    public boolean searchForCartItem(Connection connection, int piesaId, int userId) throws SQLException {
        if (piesaId == 0 ||
            userId == 0) {
            return false;
        }

        String tableName = new Db_Fields().cartName + userId;

        PreparedStatement searchQuery = connection.prepareStatement("select id_piesa from table_name = ? and id_piesa = ? limit 1");

        searchQuery.setString(1, Config.getDbName());
        searchQuery.setInt(1, piesaId);

        return searchQuery.executeQuery().next();
    }

    public void updateCartItem(Connection connection, int piesaId, int userId, int newCantitate) throws SQLException {
        if (piesaId == 0 ||
            userId == 0 ||
            newCantitate == 0) {
            return;
        }

        String tableName = new Db_Fields().cartName + userId;

        PreparedStatement searchQuery = connection.prepareStatement("select id_piesa from table_name = ? and id_piesa = ? limit 1");

        searchQuery.setString(1, Config.getDbName());
        searchQuery.setInt(1, piesaId);

        ResultSet searchResult = searchQuery.executeQuery();

        int initialCantitate = 0;

        while (searchResult.next()) {
            initialCantitate = searchResult.getInt("cantitate");
        }

        PreparedStatement updateQuery = connection.prepareStatement("update ? set cantitate = ? where id_piesa = ?");

        updateQuery.setString(1, tableName);
        updateQuery.setInt(2, initialCantitate + newCantitate);
        updateQuery.setInt(3, piesaId);

        updateQuery.executeUpdate();

        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }
}
