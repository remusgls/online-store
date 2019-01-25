package core;

import model.user.User;
import queries.SQL_User;

import java.sql.SQLException;

public class UserJourneyNewUser {
    public static void main(String args[]) {
        User remus = null;

        // Log in user and prepare user cart
        try {
            remus = new SQL_User().insertUser("gosman", "claudiu", "strada noua, alea rozelor", "123456", "gosman32@gmail.com");

            System.out.println(remus);
            if (remus == null) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
