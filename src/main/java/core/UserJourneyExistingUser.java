package core;

import model.cart.CartItem;
import model.product.Product;
import model.user.User;
import queries.SQL_Piesa;
import queries.SQL_User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
//        try {
//            remus.setPrenume(remus.getId(), "Renum");
//            remus.setNume(remus.getId(), "George");
//            remus.setEmail(remus.getId(), "myhawesa@gmail.com");
//
//            // credentials
//            remus.setPassword(remus.getId(), "newPassword");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        // Show some products
        List<Product> products;

        try {
            products = new SQL_Piesa().selectProduct("", "a2", "");

            if (products.size() == 0) {
                System.out.println("No results found.");

                return;
            }

            System.out.println("Am gasit " + products.size() + " rezultate");

            for (Product product: products) {
                System.out.println(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ADD TO CART
        List<CartItem> userCart = new ArrayList<>(1);

//        userCart.add()
    }
}
