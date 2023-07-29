package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.executeUpdate("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
            System.out.println("NOT CREATE USERS TABLE");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("NOT DROP USERS TABLE");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.executeUpdate("INSERT users(name, lastName, age) VALUE(\""
                    + name + "\", \"" + lastName + "\", " + age + ")");
        } catch (SQLException e) {
            System.out.println("NOT SAVE USER");
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println("NOT REMOVE USER BY ID = " + id);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = new Util().getConn().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")
                );
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("NOT GET ALL USERS");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = new Util().getConn().createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id > 0");
        } catch (SQLException e) {
            System.out.println("NOT CLEAN USERS TABLE");
        }
    }
}
