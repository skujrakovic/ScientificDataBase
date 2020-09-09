package ba.unsa.etf.rpr.project.utilities;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.exceptions.InvalidUrlException;
import ba.unsa.etf.rpr.project.interfaces.IScienceChest;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.javabeans.User;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScienceChestDAO implements IScienceChest {
    private static ScienceChestDAO instance;
    private static Connection conn;
    private PreparedStatement addUserQuery, findUserQuery, getScientificPapersByGenreQuery, getScientificPapersByTitleQuery,
            getAuthorsForScientificPaperQuery, addScientificPaperQuery, addAuthorQuery, getMaxSidQuery, getMaxAidQuery;


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
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            getScientificPapersByGenreQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");
            getScientificPapersByTitleQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");
            getAuthorsForScientificPaperQuery = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");
            addScientificPaperQuery = conn.prepareStatement("INSERT INTO scientific_paper VALUES (?,?,?,?,?,?,?)");
            addAuthorQuery = conn.prepareStatement("INSERT INTO author VALUES (?,?,?)");
            getMaxSidQuery = conn.prepareStatement("SELECT max(sid)+1 FROM scientific_paper");
            getMaxAidQuery = conn.prepareStatement("SELECT max(aid)+1 FROM author");
        } catch (SQLException e) {
            regenerateDatabase();
            try {
                addUserQuery = conn.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
                findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
                getScientificPapersByGenreQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");
                getScientificPapersByTitleQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");
                ;
                getAuthorsForScientificPaperQuery = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");
                addScientificPaperQuery = conn.prepareStatement("INSERT INTO scientific_paper VALUES (?,?,?,?,?,?,?)");
                addAuthorQuery = conn.prepareStatement("INSERT INTO author VALUES (?,?,?)");
                getMaxSidQuery = conn.prepareStatement("SELECT max(sid)+1 FROM scientific_paper");
                getMaxAidQuery = conn.prepareStatement("SELECT max(aid)+1 FROM author");
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

    public boolean usernameExists(String username) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
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

    public void logInUser(String username) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            findUserQuery = conn.prepareStatement("SELECT * FROM user WHERE username=?");

            try {
                findUserQuery.setString(1, username);
                ResultSet rs = findUserQuery.executeQuery();
                if (rs.next()) {
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
        ScientificPaper paper;
        try {
            paper = new ScientificPaper(rs.getString("title"), rs.getString("link"), rs.getString("summary"), rs.getInt("year"), ScientificPaperGenre.valueOf(rs.getString("genre")), ScientificPaperType.valueOf(rs.getString("type")));
        } catch (InvalidUrlException e) {
            e.printStackTrace();
            return null;
        }
        return paper;
    }

    public ArrayList<String> getAuthorsForScientificPaper(Integer id) {
        ArrayList<String> authors = new ArrayList<>();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getAuthorsForScientificPaperQuery = conn.prepareStatement("SELECT full_name from author WHERE fk_sid=?");

            try {
                getAuthorsForScientificPaperQuery.setString(1, String.valueOf(id));
                ResultSet rs = getAuthorsForScientificPaperQuery.executeQuery();
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

    public void getScientificPaperByGenre(ScientificPaperGenre genre) {
        results.clear();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getScientificPapersByGenreQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE genre=?");
            try {
                getScientificPapersByGenreQuery.setString(1, genre.name());
                ResultSet rs = getScientificPapersByGenreQuery.executeQuery();
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

    public void getScientificPaperByTitle(String title) {
        results.clear();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getScientificPapersByTitleQuery = conn.prepareStatement("SELECT * FROM scientific_paper WHERE title LIKE ?");

            try {
                String searchTitle = "%" + title + "%";
                getScientificPapersByTitleQuery.setString(1, searchTitle);
                ResultSet rs = getScientificPapersByTitleQuery.executeQuery();
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

    public void addScientificPaper(ScientificPaper scientificPaper) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            getMaxSidQuery = conn.prepareStatement("SELECT max(sid)+1 from scientific_paper");
            getMaxAidQuery = conn.prepareStatement("SELECT max(aid)+1 from author");
            addScientificPaperQuery = conn.prepareStatement("INSERT INTO scientific_paper VALUES (?,?,?,?,?,?,?)");
            addAuthorQuery = conn.prepareStatement("INSERT INTO author VALUES (?,?,?)");
            try {
                ResultSet rs = getMaxSidQuery.executeQuery();
                Integer sid = 0, aid = 0;
                if (rs.next()) {
                    sid = rs.getInt(1);
                }
                rs = getMaxAidQuery.executeQuery();
                if (rs.next()) {
                    aid = rs.getInt(1);
                }
                addScientificPaperQuery.setInt(1, sid);
                addScientificPaperQuery.setString(2, scientificPaper.getTitle());
                addScientificPaperQuery.setInt(3, scientificPaper.getYearOfPublication());
                addScientificPaperQuery.setString(4, scientificPaper.getGenre().name());
                addScientificPaperQuery.setString(5, scientificPaper.getType().name());
                addScientificPaperQuery.setString(6, scientificPaper.getLink());
                addScientificPaperQuery.setString(7, scientificPaper.getSummary());
                addScientificPaperQuery.executeUpdate();
                for (int i = 0; i < scientificPaper.getAuthors().size(); i++) {
                    addAuthorQuery.setInt(1, aid++);
                    addAuthorQuery.setString(2, scientificPaper.getAuthors().get(i));
                    addAuthorQuery.setInt(3, sid);
                    addAuthorQuery.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String wrapString(String s, String deliminator, int length) {
        String result = "";
        int lastdelimPos = 0;
        for (String token : s.split(" ", -1)) {
            if (result.length() - lastdelimPos + token.length() > length) {
                result = result + deliminator + token;
                lastdelimPos = result.length() + 1;
            } else {
                result += (result.isEmpty() ? "" : " ") + token;
            }
        }
        return result;
    }

    public void writeToFile(ScientificPaper paper) {
        File file = new File(System.getProperty("user.home"), paper.getTitle() + ", " + paper.getType().toString() + ".txt");
        String text = new String("Title: " + paper.getTitle() + "\n\nSummary:\n");
        text += wrapString(paper.getSummary(), "\n", 70);
        text += "\n\nAuthors:\n";
        for (String author :
                paper.getAuthors()) {
            text += author;
            text += "\n";
        }
        Writer writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException ignore) {
            }
        }

    }
}
