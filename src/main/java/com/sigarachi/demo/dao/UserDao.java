package com.sigarachi.demo.dao;

import com.sigarachi.demo.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    String url = "jdbc:postgresql://localhost/servlets?user=postgres&password=root&ssl=false";

    private static final String SELECT_ALL_USERS = "select id, name, email, country from users;";
    private static final String ADD_USER = "INSERT INTO users (id,name,email,country) VALUES (?,?,?,?);";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    public UserDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void insertUser(User user) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(this.ADD_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getCountry());
            System.out.println(user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> selectUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(this.SELECT_ALL_USERS);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(users);
        return users;
    }
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}
