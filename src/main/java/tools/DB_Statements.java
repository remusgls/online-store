package tools;

import types.Db_Fields;

import java.sql.*;

public class DB_Statements {
    public Statement createStatement(Connection dbConnection) {
        Statement statement = null;

        try {
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    public PreparedStatement prepareStatement(Connection dbConnection, String sqlQuery) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = dbConnection.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public ResultSet runSqlQuery(Connection dbConnection, String sqlQuery, Boolean update) {

        PreparedStatement preparedStatement = new DB_Statements().prepareStatement(dbConnection, sqlQuery);

        if (update) {
            int resultSet = 0;

            try {
                resultSet = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ResultSet resultSet = null;

        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public int runUpdateQuery(Connection dbConnection, String sqlQuery) {
        PreparedStatement preparedStatement = new DB_Statements().prepareStatement(dbConnection, "INSERT INTO `model_auto` VALUES ('112', 'AAAA', '1')");
        int result = 0;
        try {

            result = preparedStatement.executeUpdate();
            dbConnection.commit();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
