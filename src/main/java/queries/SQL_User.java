package queries;

import tools.DB_Connection;
import types.Db_Fields;
import types.Errors;

import java.sql.*;
import java.util.Base64;

public class SQL_User {
    public void updateUserData(String email, String newNume, String newPrenume, String newParola, String newEmail) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        if (email.length() == 0 ||
            !(new Db_Fields().EMAIL_PATTERN.matcher(email).matches())) {
            System.out.print(Errors.ERROR_DB_INVALID_EMAIL);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        PreparedStatement searchUser = connection.prepareStatement("select mail, nume, prenume, parola from user where mail = ?");

        searchUser.setString(1, email);

        ResultSet searchResults = searchUser.executeQuery();

        // no user found!
        if (!searchResults.next()) {
            System.out.print(Errors.ERROR_DB_INVALID_EMAIL);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        PreparedStatement updateUser = connection.prepareStatement("update user set nume = ?, prenume = ?, parola = ?, mail = ? where mail = ?");

        if (searchResults.next()) {
            System.out.print(Errors.ERROR_DB_DUPLICATE_EMAIL);
            new DB_Connection().closeDBConnection(connection);
            return;
        }

        if (newNume == null ||
            searchResults.getString("nume").toLowerCase().trim().equals(newNume.trim().toLowerCase())) {
            newNume = searchResults.getString("nume");
        }

        if (newPrenume == null ||
            searchResults.getString("prenume").toLowerCase().trim().equals(newPrenume.trim().toLowerCase())) {
            newPrenume = searchResults.getString("prenume");
        }

        if (newParola == null ||
            new String(Base64.getDecoder().decode(searchResults.getString("parola"))).equals(new String(Base64.getEncoder().encode(newParola.getBytes())))) {
            newParola = searchResults.getString("parola");
        }

        if (newEmail == null ||
            searchResults.getString("mail").equals(newEmail)) {
            newEmail = searchResults.getString("mail");
        }

        updateUser.setString(1, newNume);
        updateUser.setString(2, newPrenume);
        updateUser.setString(3, new String(Base64.getEncoder().encode(newParola.getBytes())));
        updateUser.setString(4, newEmail);
        updateUser.setString(5, email);

        updateUser.executeUpdate();

        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    public void insertUser(String nume, String prenume, String adresa, String parola, String email) throws SQLException {
        Connection connection = new DB_Connection().openDBConnection();

        if (nume.length() <= 3 ||
            prenume.length() <= 3 ||
            adresa.length() <= 3 ||
            parola.length() <= 5 ||
            email.length() <= 3 ||
            this.checkExistingUserByEmail(email) ||
            !(new Db_Fields().EMAIL_PATTERN.matcher(email).matches())) {
            System.out.println(Errors.ERROR_DB_INVALID_USER_DETAILS);

            new DB_Connection().closeDBConnection(connection);

            return;
        }

        PreparedStatement insertStatement = connection.prepareStatement("insert into user values(?, ?, ?, ?, ?, ?, ?)");

        insertStatement.setObject(1, null);
        insertStatement.setString(2, nume);
        insertStatement.setString(3, prenume);
        insertStatement.setString(4, adresa);
        insertStatement.setString(5, new String(Base64.getEncoder().encode(parola.getBytes())));
        insertStatement.setString(6, email);
        insertStatement.setBoolean(7, false);

        insertStatement.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
    }

    private boolean checkExistingUserByEmail(String emailAddress) throws SQLException {
        if (emailAddress.length() == 0) {
            return true;
        }

        boolean foundUserEmailAddress = false;

        Connection connection = new DB_Connection().openDBConnection();

        PreparedStatement searchStatement = connection.prepareStatement("select mail from `user` where mail = ?");

        searchStatement.setString(1, emailAddress);

        ResultSet resultSet = searchStatement.executeQuery();

        while (resultSet.next()) {
            foundUserEmailAddress = resultSet.getString("mail").toLowerCase().trim().equals(emailAddress.trim().toLowerCase());

            if (foundUserEmailAddress) {
                break;
            }
        }

        return foundUserEmailAddress;
    }
}
