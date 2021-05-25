package repository;

import config.DatabaseConfiguration;
import model.User;

import java.sql.*;

public class UserRepository {
    // CallableStatement

    public static void insertUser(User user) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "INSERT INTO users (firstName, lastName, age, noOfOrders, spentMoney)"
                + " VALUES('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getAge() +"', '" + user.getNoOfOrders()+"', '" + user.getSpentMoney()+"')";
        Statement statement = databaseConnection.createStatement();

        int rows = statement.executeUpdate(sql);
        DatabaseConfiguration.closeDatabaseConnection();
    }

    // PreparedStatement - use when we have parameters
    public static User getUserById(int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM users WHERE UserId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            DatabaseConfiguration.closeDatabaseConnection();
            return mapToUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return null;
    }

    // PreparedStatement
    public void updateUserName(String firstName, String lastName, int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String updateNameSql = "UPDATE users SET (firstName, lastName)=(?,?) WHERE UserId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    private static User mapToUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
           User user =  new User(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            user.setNoOfOrders(resultSet.getInt(5));
            user.setSpentMoney(resultSet.getDouble(6));
            return user;
        }
        return null;
    }

    public void delete(int id) throws  SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "DELETE FROM locations WHERE location_id=" + id;
        Statement statement = databaseConnection.createStatement();

        int rows = statement.executeUpdate(sql);
        DatabaseConfiguration.closeDatabaseConnection();
    }

}
