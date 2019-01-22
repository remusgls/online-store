package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public
class SQL_Marca {

    public void insert(String marca) throws SQLException {
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
