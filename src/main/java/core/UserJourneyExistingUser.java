package core;

import model.cart.Cart;
import model.cart.CartItem;
import model.product.Product;
import model.user.User;
import queries.SQL_Cart;
import queries.SQL_Piesa;
import queries.SQL_User;
import tools.PDF_generator;
import types.Errors;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserJourneyExistingUser {
    public static void main(String[] args) {
        User user = null;

        // Log in user and prepare user cart
        try {
            user = new SQL_User().loadUserData("myhawesa@gmail.com", "newPassword");

            if (user == null) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if (user.getId() == 0) {
            return;
        }

        // Display current user data
        System.out.println(user);


        // Update user data
//        try {
//            user.setPrenume(user.getId(), "Renum");
//            user.setNume(user.getId(), "George");
//            user.setEmail(user.getId(), "myhawesa@gmail.com");
//
//            // credentials
//            user.setPassword(user.getId(), "newPassword");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        // Show some products
        List<Product> products = new ArrayList<>(10);

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

        List<CartItem> cartItems = new ArrayList<>(10);

        Product newProductToAdd = products.stream().filter(product -> product.getId() == 30).findAny().orElse(null);

        CartItem newCartItem = null;

        try {
            newCartItem = new SQL_Cart().insertCartItem(user.getId(), newProductToAdd, 10);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cartItems.add(newCartItem);

        // update cart item if already existing
//        if (newCartItem != null) {
//            for (CartItem cartItem: cartItems) {
//                if (cartItem.getId() == newCartItem.getId()) {
//                    cartItem.setBuc(newCartItem.getBuc());
//                    cartItem.setPretCuDiscount(newCartItem.getPretCuDiscount());
//                    cartItem.setPretFaraDiscount(newCartItem.getPretFaraDiscount());
//                }
//            }
//        }

        // TO checkout

        new PDF_generator().generatePDFtable(cartItems);

        // Prepare cart item
//        List<CartItem> userCart = new ArrayList<>(1);

//        userCart.add()
    }
}
