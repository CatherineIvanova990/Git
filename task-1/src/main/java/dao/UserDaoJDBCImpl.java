package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createDB = """
                create table if not exists Users
                  (
                      id       serial primary key,
                      name     varchar(255),
                      last_name varchar(255),
                      age      smallint
                  );
                """;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createDB);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropDB = """
                drop table if exists Users;
                """;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dropDB);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = """
                insert into Users (name, last_name, age)
                values (?,?,?);
                """;
        try (Connection connection = Util.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(saveUser)) {
            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);
            pStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUser = """
                delete from Users where id = ?;
                """;
        try (Connection connection = Util.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(removeUser)) {
            pStatement.setLong(1, id);
            pStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getUsers = """
                select * from Users;
                """;
        try (Connection connection = Util.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getUsers)) {
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String removeAllUsers = """
                delete from Users;
                """;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(removeAllUsers);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
