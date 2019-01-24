package core;

import model.user.User;
import queries.SQL_User;

import java.sql.SQLException;

public class UserJourneyExistingUser {
    public static void main(String[] args) {
        User remus = null;

        // Log in user and prepare user cart
        try {
            remus = new SQL_User().loadUserData("myhawesa@gmail.com", "newPassword");

            if (remus == null) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if (remus.getId() == 0) {
            return;
        }

        // Display current user data
        System.out.println(remus);


        // Update user data
        try {
            remus.setPrenume(remus.getId(), "Renum");
            remus.setNume(remus.getId(), "George");
            remus.setEmail(remus.getId(), "myhawesa@gmail.com");

            // credentials
            remus.setPassword(remus.getId(), "newPassword");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Display updated user data


    }
}
