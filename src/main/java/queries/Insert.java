package queries;

import tools.DB_Connection;
import tools.DB_Statements;
import types.Config;
import types.Db_Fields;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert {
    public void model_auto(String model, int id_marca) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        PreparedStatement insertStatement = connection.prepareStatement("insert into `model_auto` values (?, ?, ?)");

        if (!this.checkIdMarca(connection, id_marca)) {
            System.out.print(Errors.ERROR_DB_INVALID_MARCA_ID);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        if (model.length() == 0 || id_marca == 0) {
            new DB_Connection().closeDBConnection(connection);

            System.out.print(Errors.ERROR_DB_INSERT_ERROR_WRONG_CHARS);
            return;
        }

        insertStatement.setObject(1, null);
        insertStatement.setString(2, model);
        insertStatement.setInt(3, id_marca);

        insertStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public boolean checkIdMarca(Connection connection, int id_marca) throws SQLException {
        if (id_marca == 0) {
            return false;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select id from `marca_auto` where id = ?");

        searchStatement.setInt(1, id_marca);

        return searchStatement.executeQuery().next();
    }
}
