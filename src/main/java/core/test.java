package core;
import queries.*;
import tools.DB_Connection;
import tools.DB_Statements;
import types.Config;
import types.Db_Fields;

import java.sql.*;

public class test {
    public static void main(String[] args) throws SQLException {
//        new Insert().model_auto("TEST DE INSERT", 3);
//        new SQL_Piesa().insertPiesa(3, 5, "fuzetoi", 20, 120);

        // MODEL CAT PIESA
        // new SQL_Cat_piesa().insertCatPiesa("coIutz");

        // MODEL PIESA
//        new SQL_Piesa().deletePiesa(6, "Capota motor", 8);
//        new SQL_Piesa().deleteUpdatePiesa(6, "Capota motor", 8, true);
//        new SQL_Piesa().stocUpdatePiesa(6, "Capota motor", 8, -5);
//        new SQL_Piesa().stocSet(6, "Capota motor", 8, 0);
//        new SQL_Piesa().pretUpdate(6, "Capota motor", 8, -356);
//        new SQL_Piesa().pretSet(6, "Capota motor", 8, 300);

        // MODEL USER
      //  new SQL_User().insertUser("coman", "adrian", "strada cosminului numarul 7", "Spanac", "ett@gmail.com");
       // new SQL_User().updateUserData("septe@gmail.com",null, "adrian", "HAHAHA", null);
        new SQL_Model().insertModel("Samsung", 3);
    }
}
