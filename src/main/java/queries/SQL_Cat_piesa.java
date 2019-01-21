package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Cat_piesa {

    public void insertCatPiesa(String newCategorie) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        if (this.existingCategorie(connection, newCategorie)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL_ID);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        PreparedStatement insertStatement = connection.prepareStatement("insert into `categorie_piesa` values (null, ?)");

        insertStatement.setString(1, newCategorie);

        insertStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    private boolean existingCategorie(Connection connection, String newCategorie) throws SQLException {
        if (newCategorie.length() == 0) {
            return false;
        }

        PreparedStatement searchStatement = connection.prepareStatement("select * from `categorie_piesa` where categorie = ?");

        searchStatement.setString(1, newCategorie);

        ResultSet searchValue = searchStatement.executeQuery();
        boolean foundDuplicate = false;

        while (searchValue.next()) {
            foundDuplicate = searchValue.getString("categorie").toLowerCase().trim().equals(newCategorie.toLowerCase().trim());

            if (foundDuplicate) {
                break;
            }
        }

        return foundDuplicate;


    }

}
