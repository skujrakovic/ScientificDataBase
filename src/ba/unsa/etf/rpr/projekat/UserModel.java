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
    private PreparedStatement addUserQuery, findUserQuery;


    private SimpleObjectProperty<User> currentUser = new SimpleObjectProperty<>();

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
            addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username=?");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
                findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
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


    public User getCurrentUser() {
        return currentUser.get();
    }

    public SimpleObjectProperty<User> currentUserProperty() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser.set(currentUser);
    }

    public void addUser(User user) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");

            try {
                addUserQuery.setString(1, user.getName());
                addUserQuery.setString(2, user.getSurname());
                addUserQuery.setString(3, user.getEmail());
                addUserQuery.setString(4, user.getUsername());
                addUserQuery.setString(5, user.getPassword());
                addUserQuery.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean usernameExists(String username){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username=?");

            try {
                findUserQuery.setString(1, username);
                ResultSet rs = findUserQuery.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void logInUser(String username){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username=?");

            try {
                findUserQuery.setString(1, username);
                ResultSet rs = findUserQuery.executeQuery();
                if (rs.next()){
                    User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    currentUser.set(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
