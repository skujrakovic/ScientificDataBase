package ba.unsa.etf.rpr.project.javabeans;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;

import java.util.ArrayList;

public class ScientificPaper {
    String title, link, summary;
    Integer yearOfPublication;
    ScientificPaperGenre genre;
    ScientificPaperType type;
    ArrayList<String> authors;

    public ScientificPaper(String title, String link, String summary, Integer yearOfPublication, ScientificPaperGenre genre, ScientificPaperType type) {
        this.title = title;
        this.link = link;
        this.summary = summary;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public ScientificPaperGenre getGenre() {
        return genre;
    }

    public void setGenre(ScientificPaperGenre genre) {
        this.genre = genre;
    }

    public ScientificPaperType getType() {
        return type;
    }

    public void setType(ScientificPaperType type) {
        this.type = type;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
}