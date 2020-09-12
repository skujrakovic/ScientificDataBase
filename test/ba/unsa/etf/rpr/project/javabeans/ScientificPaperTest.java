package ba.unsa.etf.rpr.project.javabeans;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.exceptions.InvalidUrlException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScientificPaperTest {
    @Test
    public void testContructorException(){
        assertThrows(InvalidUrlException.class,() -> new ScientificPaper("Title","link","summary",2020, ScientificPaperGenre.ART, ScientificPaperType.BOOK));
    }
    @Test
    public void testConstructor(){
        try {
            ScientificPaper paper = new ScientificPaper("Title", "http://www.google.com","Summary",2020,ScientificPaperGenre.ART, ScientificPaperType.BOOK);
            assertEquals("Title", paper.getTitle());
            assertEquals("http://www.google.com", paper.getLink());
            assertEquals("Summary", paper.getSummary());
            assertEquals(2020, paper.getYearOfPublication());
            assertEquals(ScientificPaperGenre.ART, paper.getGenre());
            assertEquals(ScientificPaperType.BOOK, paper.getType());
        } catch (InvalidUrlException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSetters(){
        try {
            ScientificPaper paper = new ScientificPaper("a", "http://www.etf.unsa.ba","b",2000,ScientificPaperGenre.BIOLOGY, ScientificPaperType.JOURNAL);
            paper.setTitle("Title");
            paper.setSummary("Summary");
            paper.setAuthors(new ArrayList<>(Arrays.asList(new String("Author1,Author2").split(","))));
            paper.setLink("http://www.google.com");
            paper.setGenre(ScientificPaperGenre.ART);
            paper.setType(ScientificPaperType.BOOK);
            paper.setYearOfPublication(2020);
            assertEquals("Title", paper.getTitle());
            assertEquals("http://www.google.com", paper.getLink());
            assertEquals("Summary", paper.getSummary());
            assertEquals(2020, paper.getYearOfPublication());
            assertEquals(ScientificPaperGenre.ART, paper.getGenre());
            assertEquals(ScientificPaperType.BOOK, paper.getType());
            assertEquals("Author1,Author2", String.join(",", paper.getAuthors()));
        } catch (InvalidUrlException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testToString(){
        ScientificPaper paper = null;
        try {
            paper = new ScientificPaper("Title", "http://www.google.com","Summary",2020, ScientificPaperGenre.ART, ScientificPaperType.BOOK);
            assertEquals("Title, 2020", paper+"");
        } catch (InvalidUrlException e) {
            e.printStackTrace();
        }
    }
}