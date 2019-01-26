package queries;

import model.cart.CartItem;
import model.product.Product;
import tools.DB_Connection;
import types.Config;
import types.Db_Fields;
import types.Errors;

import java.sql.*;

public class SQL_Cart {
    public CartItem insertCartItem(int userId, Product product, int buc) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        //check if user is invalid or doesn't exist or not enough stoc
        if (userId == 0 ||
                product.getStoc() < buc) {
            new DB_Connection().closeDBConnection(connection);
            System.out.println(Errors.ERROR_INSUFICIENT_STOC);

            return null;
        }

        //decrement stoc
        new SQL_Piesa().stocUpdate(product.getId(), buc);

        String tableName = new Db_Fields().cartName + userId;

        //check current stoc if already in cart
        if (this.searchForCartItem(connection, product.getId(), userId)) {
            buc += this.getUserCartItem(connection, product.getId(), userId);

            PreparedStatement searchQuery = connection.prepareStatement("select id from `" + tableName + "`  where id_piesa = ?");

            searchQuery.setInt(1, product.getId());

            ResultSet resultSet = searchQuery.executeQuery();

            int id = 0;

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            PreparedStatement updateStocStatement = connection.prepareStatement("update `" + tableName + "` set cantitate = ? where id = ?");

            updateStocStatement.setInt(1, buc);
            updateStocStatement.setInt(2, id);

            updateStocStatement.executeUpdate();
            connection.commit();

            new DB_Connection().closeDBConnection(connection);

            return null;
        } else {

            String sqlPrepare = "insert into $tableName values (?, ?, ?, ?, ?, ?, ?, ?)";

            sqlPrepare = sqlPrepare.replace("$tableName", tableName);

            PreparedStatement insertStatement = connection.prepareStatement(sqlPrepare);

            //insertStatement.setString(1, tableName);
            insertStatement.setObject(1, null);
            insertStatement.setObject(2, userId);
            insertStatement.setInt(3, product.getId());
            insertStatement.setObject(4, null);
            insertStatement.setString(5, product.getPiesa() + " " + product.getModel());
            insertStatement.setInt(6, buc);
            insertStatement.setInt(7, product.getPret());
            insertStatement.setInt(8, product.getDiscount());

            insertStatement.executeUpdate();

            connection.commit();

            PreparedStatement searchQuery = connection.prepareStatement("select id from `" + tableName + "`  where id_piesa = ?");

            searchQuery.setInt(1, product.getId());

            ResultSet resultSet = searchQuery.executeQuery();

            int idCartItem = 0;

            while (resultSet.next()) {
                idCartItem = resultSet.getInt("id");
            }

            new DB_Connection().closeDBConnection(connection);

            return new CartItem(idCartItem,
                    product.getPiesa() + " " + product.getModel(),
                    buc,
                    product.getDiscount(),
                    product.getPret(),
                    product.getPret() * buc,
                    (product.getPret() * buc) - (product.getPret() * buc * product.getDiscount() / 100)
            );
        }
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

        PreparedStatement searchQuery = connection.prepareStatement("select * from `" + tableName + "` where id_piesa = ?");

        searchQuery.setInt(1, piesaId);

        return searchQuery.executeQuery().next();
    }

    public int getUserCartItem(Connection connection, int piesaId, int userId) throws SQLException {
        if (piesaId == 0 ||
            userId == 0) {
            return 0;
        }

        String tableName = new Db_Fields().cartName + userId;

        PreparedStatement searchQuery = connection.prepareStatement("select cantitate from `" +  tableName + "` where id_piesa = ? limit 1");

        searchQuery.setInt(1, piesaId);

        int cantitate = 0;

        ResultSet searchResults = searchQuery.executeQuery();

        while (searchResults.next()) {
            cantitate = searchResults.getInt("cantitate");
        }

        return cantitate;
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
