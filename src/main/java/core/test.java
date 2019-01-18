package core;
import queries.Insert;
import queries.SQL_Piesa;
import tools.DB_Connection;
import tools.DB_Statements;
import types.Config;
import types.Db_Fields;

import java.sql.*;

public class test {
    public static void main(String[] args) throws SQLException {
//        new Insert().model_auto("TEST DE INSERT", 879);
        new SQL_Piesa().insertPiesa(3, 5, "fuzetoi", 20, 120);
    }
}
