package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Piesa {
    public void insertPiesa(int id_categorie, int id_model, String piesa, int pret, int stoc ) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        PreparedStatement insertStatement = connection.prepareStatement("insert into `piesa` values (?, ?, ?, ?, ?, ?)");

        if (id_categorie == 0 ||
            id_model == 0 ||
            !this.checkIdCategorie(connection, id_categorie) ||
            !this.checkIdModel(connection, id_model) ||
            this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        if (piesa.length() == 0) {
            System.out.print(Errors.ERROR_DB_INSERT_ERROR_WRONG_CHARS);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        insertStatement.setObject(1, null);
        insertStatement.setString(2, piesa);
        insertStatement.setInt(3, pret);
        insertStatement.setInt(4, stoc);
        insertStatement.setInt(5, id_categorie);
        insertStatement.setInt(6, id_model);

        insertStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public boolean checkIdCategorie(Connection connection, int id_categorie) throws SQLException {
        if (id_categorie == 0) {
            return false;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select id from `categorie_piesa` where id = ?");

        searchStatement.setInt(1, id_categorie);

        return searchStatement.executeQuery().next();
    }

    public boolean checkIdModel(Connection connection, int id_model) throws SQLException {
        if (id_model == 0) {
            return false;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select id from `model_auto` where id = ?");

        searchStatement.setInt(1, id_model);

        return searchStatement.executeQuery().next();
    }

    public boolean checkDuplicateEntry(Connection connection, String piesa, int id_model) throws SQLException {
        if (piesa.length() == 0) {
            return false;
        }

        boolean existingPiesa = false;

        PreparedStatement searchStatement = connection.prepareStatement("select piesa, id_model from `piesa` where id_model = ?");

        searchStatement.setInt(1, id_model);

        ResultSet output = searchStatement.executeQuery();

        while (output.next()) {
            existingPiesa = piesa.toLowerCase().trim().equals(output.getString("piesa").toLowerCase().trim()) && (id_model == output.getInt("id_model"));

            if (existingPiesa) {
                break;
            }
        }

        return existingPiesa;
    }
}
