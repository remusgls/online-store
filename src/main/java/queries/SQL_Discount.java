package queries;

import tools.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Discount {
    public int getDiscount(Connection connection, int idCategorie) throws SQLException {
        if (idCategorie == 0) {
            return 0;
        }

        int discountValue = 0;

        PreparedStatement searchStatement = connection.prepareStatement("select valoare from `discount` where id_categorie = ?");

        searchStatement.setInt(1, idCategorie);

        ResultSet searchValue = searchStatement.executeQuery();

        while (searchValue.next()) {
            discountValue = searchValue.getInt("valoare");
        }

        return discountValue;
    }
}
