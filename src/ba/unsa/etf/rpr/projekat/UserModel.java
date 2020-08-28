package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class UserModel {
    private static UserModel instance;
    private static Connection conn;
    private PreparedStatement addUserQuery;

    public static UserModel getInstance() {
        if (instance == null) instance = new UserModel();
        return instance;
    }

    public static Connection getConn() {
        return conn;
    }

    private UserModel() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            addUserQuery = conn.prepareStatement("UPDATE user SET name=?, surname=?, email=?, password=? WHERE username=?");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                addUserQuery = conn.prepareStatement("UPDATE user SET name=?, surname=?, email=?, password=? WHERE username=?");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        if (instance != null) instance.close();
        instance = null;
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerateDatabase() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("users.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.length() >= 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    Statement stat;
                    try {
                        stat = conn.createStatement();
                        stat.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addUser(User user) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
            try {
                addUserQuery.setString(1, user.getName());
                addUserQuery.setString(2, user.getSurname());
                addUserQuery.setString(3, user.getEmail());
                addUserQuery.setString(4, user.getPassword());
                addUserQuery.setString(5, user.getUsername());
                addUserQuery.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
