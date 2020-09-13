package ba.unsa.etf.rpr.project.utilities;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.exceptions.InvalidUrlException;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.javabeans.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScienceChestDAOTest {
    private ScienceChestDAO scienceChestDAO;

    @BeforeEach
    public void reconnect(){
        ScienceChestDAO.disconnect();
        File dbfile = new File("database.db");
        dbfile.delete();
        scienceChestDAO = ScienceChestDAO.getInstance();
    }
    @Test
    void testRegenerateDatabase() {
        ArrayList<ScientificPaper> papers = scienceChestDAO.getScientificPaperByTitle("Advance");
        assertEquals(4, papers.size());
        papers = scienceChestDAO.getScientificPaperByGenre(ScientificPaperGenre.ART);
        assertEquals(3,papers.size());
    }
    @Test
    void testAddUser(){
        scienceChestDAO.addUser(new User("Name", "Surname", "email@email.com", "Username","password123"));
        assertTrue(scienceChestDAO.usernameExists("Username"));
    }
    @Test
    void testLogInUser(){
        scienceChestDAO.addUser(new User("Name", "Surname", "email@email.com", "Username","password123"));
        scienceChestDAO.logInUser("Username");
        User currentUser = scienceChestDAO.getCurrentUser();
        assertEquals("Username", currentUser.getUsername());
    }
    @Test
    public void testGetAuthorsForScientificPaper(){
        ArrayList<ScientificPaper> papers = scienceChestDAO.getScientificPaperByTitle("Advanced Engineering Informatics");
        assertEquals("C. H. Chen", papers.get(0).getAuthors().get(0));
        assertEquals("T. Hartmann", papers.get(0).getAuthors().get(1));
    }
    @Test
    public void testGetScientificPaperByGenre(){
        ArrayList<ScientificPaper> papers = scienceChestDAO.getScientificPaperByGenre(ScientificPaperGenre.ECONOMICS);
        for (ScientificPaper paper:
             papers) {
            assertEquals(ScientificPaperGenre.ECONOMICS, paper.getGenre());
        }
    }
    @Test
    public void testGetScientificPaperByTitle(){
        ArrayList<ScientificPaper> papers = scienceChestDAO.getScientificPaperByTitle("Active");
        for (ScientificPaper paper:
                papers) {
            assertTrue(paper.getTitle().contains("Active"));
        }
    }
    @Test
    public void testAddScientificPaper(){
        try {
            scienceChestDAO.addScientificPaper(new ScientificPaper("Title","http://www.google.com","Summary",1999, ScientificPaperGenre.ART, ScientificPaperType.JOURNAL));
            ArrayList<ScientificPaper> papers = scienceChestDAO.getScientificPaperByTitle("Title");
            assertEquals(1,papers.size());
            for (ScientificPaper paper:
                    papers) {
                assertTrue(paper.getTitle().contains("Title"));
            }
        } catch (InvalidUrlException e) {
            e.printStackTrace();
        }
    }
}