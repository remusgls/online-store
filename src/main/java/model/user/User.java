package model.user;

import model.user.UserInterface;
import queries.SQL_User;
import types.Db_Fields;

import java.sql.SQLException;

public class User  implements UserInterface {
    private int id;
    private String nume;
    private String prenume;
    private String email;

    @Override
    public String toString() {
        return "User: {" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(int id, String nume, String prenume, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public void setNume(int userId, String newNume) throws SQLException {
        if (newNume.isEmpty() ||
            newNume.toLowerCase().trim().equals(this.nume.toLowerCase().trim())) {
            System.out.println("Invalid nume!");
            return;
        }

        new SQL_User().updateUserNume(userId, newNume);
        this.nume = newNume;
    }

    @Override
    public String getPrenume() {
        return prenume;
    }

    @Override
    public void setPrenume(int userId, String newPrenume) throws SQLException {
        if (newPrenume.isEmpty() ||
            newPrenume.toLowerCase().trim().equals(this.prenume.toLowerCase().trim())) {
            System.out.println("Invalid prenume!");
            return;
        }

        new SQL_User().updateUserPrenume(userId, newPrenume);

        this.prenume = newPrenume;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(int userId, String newEmail) throws SQLException {
        if (newEmail.isEmpty() ||
            newEmail.toLowerCase().trim().equals(this.email) ||
            !new Db_Fields().EMAIL_PATTERN.matcher(newEmail).matches()) {
            System.out.println("invalid email address!");
            return;
        }

        new SQL_User().updateUserMail(userId, newEmail);

        this.email = newEmail;
    }

    @Override
    public void setPassword(int userId, String newPassword) throws SQLException {
        if (userId == 0) {
            System.out.println("invalid user id!");
            return;
        }

        new SQL_User().updateUserPassword(userId, newPassword);
    }
}
