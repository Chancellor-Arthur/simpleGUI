package ru.dubna.simplejavagui.db;

import java.sql.*;

public final class DBManager {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/simple";
    static final String DB_USERNAME = "postgres";
    static final String DB_PASSWORD = "postgres";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static ResultSet getUser(String login) throws SQLException {
        String query = "SELECT * FROM users WHERE login = ?";

        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);

        return statement.executeQuery();
    }

    public static ResultSet getUser(String login, int id) throws SQLException {
        String query = "SELECT * FROM users WHERE login = ? AND id <> ?";

        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setInt(2, id);

        return statement.executeQuery();
    }

    public static ResultSet getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";

        PreparedStatement statement = getConnection().prepareStatement(query);

        return statement.executeQuery();
    }

    public static void createUser(String login, String password, Boolean isAdmin) throws SQLException {
        String query = "INSERT INTO users (login, password, is_admin) VALUES (?, ?, ?)";

        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setBoolean(3, isAdmin);

        statement.executeUpdate();
    }

    public static void updateUser(String login, String password, Boolean isAdmin, int id) throws SQLException {
        String query = "UPDATE users SET login = ?, password = ?, is_admin = ? WHERE id = ?";

        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setBoolean(3, isAdmin);
        statement.setInt(4, id);

        statement.executeUpdate();
    }

    public static void removeUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";

        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1, id);

        statement.executeUpdate();
    }
}
