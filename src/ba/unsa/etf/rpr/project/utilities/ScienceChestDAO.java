package ba.unsa.etf.rpr.project.utilities;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.javabeans.User;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScienceChestDAO {
    private static ScienceChestDAO instance;
    private static Connection conn;
    private PreparedStatement addUserQuery, findUserQuery, getScientificPapersByGenre, getScientificPapersByTitle, getAuthorsForScientificPaper;


    private SimpleObjectProperty<User> currentUser = new SimpleObjectProperty<>();

    public List<ScientificPaper> getResults() {
        return results;
    }

    private List<ScientificPaper> results = new ArrayList<>();

    public static ScienceChestDAO getInstance() {
        if (instance == null) instance = new ScienceChestDAO();
        return instance;
    }

    public static Connection getConn() {
        return conn;
    }

    private ScienceChestDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username=?");
            getScientificPapersByGenre = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");
            getScientificPapersByTitle = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");
            getAuthorsForScientificPaper = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
                findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
                getScientificPapersByGenre = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");
                getScientificPapersByTitle = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");;
                getAuthorsForScientificPaper = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");
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
            ulaz = new Scanner(new FileInputStream("database.db.sql"));
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
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
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
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
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
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
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

    public ScientificPaper getScientificPaperFromResultSet(ResultSet rs) throws SQLException {
        return new ScientificPaper(rs.getString("title"), rs.getString("link"), rs.getString("summary"), rs.getInt("year"), ScientificPaperGenre.valueOf(rs.getString("genre")), ScientificPaperType.valueOf(rs.getString("type")) );
    }

    public ArrayList<String> getAuthorsForScientificPaper (Integer id) {
        ArrayList<String> authors = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getAuthorsForScientificPaper = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");

            try {
                getAuthorsForScientificPaper.setString(1, String.valueOf(id));
                ResultSet rs = getAuthorsForScientificPaper.executeQuery();
                while (rs.next()) {
                    authors.add(rs.getString("full_name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void getScientificPaperByGenre (ScientificPaperGenre genre){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getScientificPapersByGenre = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");

            try {
                getScientificPapersByGenre.setString(1, genre.name());
                ResultSet rs = getScientificPapersByGenre.executeQuery();
                while (rs.next()) {
                    ScientificPaper paper = getScientificPaperFromResultSet(rs);
                    paper.setAuthors(getAuthorsForScientificPaper(rs.getInt("sid")));
                    results.add(paper);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getScientificPaperByTitle(String title){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getScientificPapersByTitle = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");

            try {
                String searchTitle = "%" + title + "%";
                getScientificPapersByTitle.setString(1, searchTitle);
                ResultSet rs = getScientificPapersByTitle.executeQuery();
                while (rs.next()) {
                    ScientificPaper paper = getScientificPaperFromResultSet(rs);
                    paper.setAuthors(getAuthorsForScientificPaper(rs.getInt("sid")));
                    results.add(paper);
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
