package ba.unsa.etf.rpr.project.interfaces;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.javabeans.User;

import java.util.ArrayList;

public interface IScienceChest {
    void addUser(User user);
    boolean usernameExists(String username);
    void logInUser(String username);
    ArrayList<String> getAuthorsForScientificPaper(Integer id);
    ArrayList<ScientificPaper> getScientificPaperByGenre(ScientificPaperGenre genre);
    ArrayList<ScientificPaper> getScientificPaperByTitle(String title);
    void addScientificPaper(ScientificPaper scientificPaper);
    void writeToFile(ScientificPaper scientificPaper);
}
