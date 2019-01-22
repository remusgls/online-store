package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Piesa {
    public void deletePiesa(int id, String piesa, int id_model) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            return;
        }

        PreparedStatement deleteStatement = connection.prepareStatement("delete from `piesa` where id = ? and piesa = ? ");

        deleteStatement.setInt(1, id);
        deleteStatement.setString(2, piesa);

        deleteStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void deleteUpdatePiesa(int id, String piesa, int id_model, Boolean deleted) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            return;
        }

        PreparedStatement deleteUpdateStatement = connection.prepareStatement("update `piesa` set deleted = ? where id = ? and piesa = ?");

        deleteUpdateStatement.setInt(2, id);
        deleteUpdateStatement.setString(3, piesa);
        deleteUpdateStatement.setBoolean(1, deleted);

        deleteUpdateStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void pretUpdate(int id, String piesa, int id_model, int pretUpdateValue) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            return;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select pret from `piesa` where id = ? and id_model = ? and piesa = ?");

        searchStatement.setInt(1, id);
        searchStatement.setInt(2, id_model);
        searchStatement.setString(3, piesa);

        ResultSet results = searchStatement.executeQuery();

        int currentPret = 0;

        while (results.next()) {
            currentPret = results.getInt("pret");
        }

        currentPret = currentPret + pretUpdateValue;

        PreparedStatement updatePretStatement = connection.prepareStatement("update piesa set pret = ? where id = ? and piesa = ?");

        updatePretStatement.setInt(2, id);
        updatePretStatement.setString(3, piesa);
        updatePretStatement.setInt(1, currentPret);

        updatePretStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void pretSet(int id, String piesa, int id_model, int pretValue) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
                pretValue < 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            new DB_Connection().closeDBConnection(connection);
            return;
        }

        PreparedStatement setPretStatement = connection.prepareStatement("update `piesa` set pret = ? where id = ? and id_model = ? and piesa = ?");

        setPretStatement.setInt(1, pretValue);
        setPretStatement.setInt(2, id);
        setPretStatement.setInt(3, id_model);
        setPretStatement.setString(4, piesa);

        setPretStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void stocUpdate(int id, String piesa, int id_model, int stocUpdateValue) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            return;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select stoc from `piesa` where id = ? and id_model = ? and piesa = ?");

        searchStatement.setInt(1, id);
        searchStatement.setInt(2, id_model);
        searchStatement.setString(3, piesa);

        ResultSet results = searchStatement.executeQuery();

        int currentStoc = 0;

        while (results.next()) {
            currentStoc = results.getInt("stoc");
        }

        currentStoc = currentStoc + stocUpdateValue;

        PreparedStatement updateStocStatement = connection.prepareStatement("update piesa set stoc = ? where id = ? and piesa = ?");

        updateStocStatement.setInt(2, id);
        updateStocStatement.setString(3, piesa);
        updateStocStatement.setInt(1, currentStoc);

        updateStocStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void stocSet(int id, String piesa, int id_model, int stocValue) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        // multiple checks
        if (id == 0 ||
            stocValue < 0 ||
            piesa.length() <= 0 ||
            id_model == 0 ||
            !this.checkDuplicateEntry(connection, piesa, id_model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            new DB_Connection().closeDBConnection(connection);
            return;
        }

        PreparedStatement setStocStatement = connection.prepareStatement("update `piesa` set stoc = ? where id = ? and id_model = ? and piesa = ?");

        setStocStatement.setInt(1, stocValue);
        setStocStatement.setInt(2, id);
        setStocStatement.setInt(3, id_model);
        setStocStatement.setString(4, piesa);

        setStocStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

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
