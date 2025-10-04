package com.saifutdinov.dao.impl;

import com.saifutdinov.dao.UserDao;
import com.saifutdinov.entity.User;
import com.saifutdinov.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection dbConnection = DatabaseConnectionUtil.getConnection();

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> resultList = new ArrayList<>();

        try (Statement st = dbConnection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                resultList.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("name"),
                                rs.getString("lastname")
                        )
                );
            }
            return resultList;

        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all users", e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name, lastname, login, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User getByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement ps = dbConnection.prepareStatement(sql)) {
            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("lastname")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching user by login", e);
        }
        return null;
    }
}
