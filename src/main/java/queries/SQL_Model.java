package queries;

import tools.DB_Connection;
import types.Errors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_Model {
    public int getModelId(String model) throws SQLException {
        int foundId = 0;

        if (model.isEmpty()) {
            return foundId;
        }

        Connection connection = new DB_Connection().openDBConnection();

        PreparedStatement searchStatement = connection.prepareStatement("select id from `model_auto` where marca like '%?%' limit 1");

        searchStatement.setString(1, model);

        ResultSet resultSet = searchStatement.executeQuery();


        while (resultSet.next()) {
            foundId = resultSet.getInt("id");
        }

        return foundId;
    }

    public void insertModel(String model,int id_marca) throws SQLException {
       Connection connection = new DB_Connection().openDBConnection();
        PreparedStatement insertModel_auto = connection.prepareStatement("insert into `model_auto` values (?, ?, ?, ?)");

       if (model.length() == 0 ||
           this.checkDuplicateModel(connection, model)) {
            System.out.print(Errors.ERROR_DB_INVALID_MODEL);

           new DB_Connection().closeDBConnection(connection);

          return;
       }
        insertModel_auto.setObject(1,null);
        insertModel_auto.setString(2, model);
        insertModel_auto.setInt(3, id_marca);
        insertModel_auto.setBoolean(4, false);

        insertModel_auto.executeUpdate();
        connection.commit();

        new DB_Connection().closeDBConnection(connection);
      }

     public boolean checkDuplicateModel(Connection connection, String model) throws SQLException {
          if (model.length() == 0) {
              return false;
           }

           boolean existingModel = false;

        PreparedStatement searchStatementModel = connection.prepareStatement("select model, id from `model_auto` where model = ?");

           searchStatementModel.setString(1, model);

          ResultSet output = searchStatementModel.executeQuery();

        while (output.next()) {

        existingModel = model.toLowerCase().trim().equals(output.getString("model").toLowerCase().trim());

               if (existingModel) {
                   break;
              }
          }

           return existingModel;


        }

}