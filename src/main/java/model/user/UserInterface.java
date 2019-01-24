package model.user;

import java.sql.SQLException;

public interface UserInterface {
    public int getId();

    public String getNume();

    public String getPrenume();

    public String getEmail();

    public void setNume(int userId, String newNume) throws SQLException;

    public void setPrenume(int userId, String newPrenume) throws SQLException;

    public void setEmail(int userId, String newEmail) throws SQLException;

    public void setPassword(int userId, String newPassword) throws SQLException;
}
