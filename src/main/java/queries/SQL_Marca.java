package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public
class SQL_Marca {

    public int getMarcaId(String marca) throws SQLException {
        int foundId = 0;

        if (marca.isEmpty() ||
            marca.length() < 2) {
            return foundId;
        }

        Connection connection = new DB_Connection().openDBConnection();

        PreparedStatement searchStatement = connection.prepareStatement("select id from `marca_auto` where marca like '%?%' limit 1");

        searchStatement.setString(1, marca);

        ResultSet resultSet = searchStatement.executeQuery();


        while (resultSet.next()) {
            foundId = resultSet.getInt("id");
        }

        return foundId;
    }

    /**
     * insert new marca_auto to marca
     * @param marca
     * @throws SQLException
     */
    public void insertMarca(String marca) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();
        PreparedStatement insertStatement = connection.prepareStatement("insert into `marca_auto` values (?, ?)");

        if (
                marca.length() == 0 ||
                        this.checkDuplicateMarca(connection, marca)) {
            System.out.print(Errors.ERROR_DB_INVALID_MARCA);

            new DB_Connection().closeDBConnection(connection);

            return;
        }


        insertStatement.setObject(1, null);
        insertStatement.setString(2, marca);


        insertStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    /**
     * check for already existing marca_auto on marca
     * @param connection
     * @param marca
     * @return
     * @throws SQLException
     */
    public boolean checkDuplicateMarca(Connection connection, String marca) throws SQLException {
        if (marca.length() == 0) {
            return false;
        }

        boolean existingMarca = false;

        PreparedStatement searchStatement = connection.prepareStatement("select marca, id from `marca_auto` where marca = ?");

        searchStatement.setString(1, marca);

        ResultSet output = searchStatement.executeQuery();

        while (output.next()) {
            existingMarca = marca.toLowerCase().trim().equals(output.getString("marca").toLowerCase().trim());

            if (existingMarca) {
                break;
            }
        }

        return existingMarca;


    }



}
