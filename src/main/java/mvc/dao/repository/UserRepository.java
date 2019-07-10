package mvc.dao.repository;

import mvc.dao.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    /**
     * Get User By Email and Password from User Table
     * @return class User or null
     */
    public User getUserByEmailByPassword(String email, String password) {

        DataSource dataSource = new DataSource();

        String query = "SELECT id, email, password, firstname, telegram" +
                " FROM users " +
                " WHERE users.email='" + email + "' AND password='" + password + "'";

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        )
        {
            if (resultSet.next())  {
                User user =  new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("telegram")
                );

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void setUser(String email, String password, String firstname, String lastname, String telegram) {

        DataSource dataSource = new DataSource();

        String query = "INSERT INTO users (email, password,firstname, telegram) VALUES ( \'"+email+
                "', '"+password+"','"+firstname+"','"+lastname+"','"+telegram+"') ;";

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){}catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
