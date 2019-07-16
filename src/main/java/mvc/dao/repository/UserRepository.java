package mvc.dao.repository;

import mvc.dao.entity.User;

import java.sql.*;

public class UserRepository {



    public User getUserByID(long chat_id) {

        DataSource dataSource = new DataSource();

        String query = "SELECT id,chat_id,firstname,category,feedURL,interval,numberOfNews" +
                " FROM users " +
                " WHERE users.chat_id='" + chat_id + "'";

        try (
                // get connection with our database
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        )
        {
            if (resultSet.next())  {
                User user =  new User(
                        resultSet.getLong("id"),
                        resultSet.getLong("chat_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("category"),
                        resultSet.getString("feedURL"),
                        resultSet.getLong("interval"),
                        resultSet.getLong("numberOfNews")
                );

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean saveUser(User user) {
        DataSource dataSource = new DataSource();

        try(
                Connection con = dataSource.getConnection();
                PreparedStatement stmt = user.getId() == 0L ?
                        con.prepareStatement("INSERT INTO user (chat_id,firstname,category,feedURL,interval,numberOfNews) VALUES (?,?,?,?,?,?)") :
                        con.prepareStatement("UPDATE user SET chat_id=?,firstname=?, category=?, feedURL=?, interval=?,numberOfNews=? WHERE chat_id=" + user.getId())
        ){

            stmt.setLong(1, user.getChat_id());
            stmt.setString(2, user.getFirstname());
            stmt.setString(3, user.getCategory());
            stmt.setString(4, user.getFeedURL());
            stmt.setLong(5, user.getInterval());
            stmt.setLong(6, user.getNumberOfNews());

            stmt.executeLargeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
