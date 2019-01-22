package core;
import queries.Insert;
<<<<<<< HEAD
import queries.SQL_Marca;
=======
import queries.SQL_Cat_piesa;
>>>>>>> 84beb61caaaa18c12417dfc2f4b4b4dd8bbd97fc
import queries.SQL_Piesa;
import tools.DB_Connection;
import tools.DB_Statements;
import types.Config;
import types.Db_Fields;

import java.sql.*;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class test {
    public static void main(String[] args) throws SQLException {
<<<<<<< HEAD
//        new Insert().model_auto("TEST DE INSERT", 879);
//        new SQL_Piesa().insertPiesa(3, 5, "roata", 20, 120);
       // new SQL_Marca().insert("audi");
      System.out.println();
=======
//        new Insert().model_auto("TEST DE INSERT", 3);
//        new SQL_Piesa().insertPiesa(3, 5, "fuzetoi", 20, 120);

        // MODEL CAT PIESA
        new SQL_Cat_piesa().insertCatPiesa("coIutz");

        // MODEL PIESA
//        new SQL_Piesa().deletePiesa(6, "Capota motor", 8);
//        new SQL_Piesa().deleteUpdatePiesa(6, "Capota motor", 8, true);
//        new SQL_Piesa().stocUpdatePiesa(6, "Capota motor", 8, -5);
//        new SQL_Piesa().stocSet(6, "Capota motor", 8, 0);
//        new SQL_Piesa().pretUpdate(6, "Capota motor", 8, -356);
//        new SQL_Piesa().pretSet(6, "Capota motor", 8, 300);

>>>>>>> 84beb61caaaa18c12417dfc2f4b4b4dd8bbd97fc
    }
}
