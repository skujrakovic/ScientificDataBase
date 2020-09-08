package ba.unsa.etf.rpr.project.enums;

import java.util.ResourceBundle;

public enum ScientificPaperGenre {
    ART("Arts and Humanities"),
    BIOLOGY("Life Sciences and Biology"),
    BUSINESS("Business and Management"),
    CHEMISTRY("Chemistry and Materials Science"),
    ENGINEERING("Engineering and Computer Science"),
    ECONOMICS("Economics and Finance"),
    GEOGRAPHY("Earth Sciences and Geography"),
    MEDICINE("Medical and Health Sciences"),
    PHYSICS("Physics and Mathematics"),
    PSYCHOLOGY("Social Sciences and Psychology");
    private String label;

    ScientificPaperGenre(String label) {
        this.label = label;
    }

    public String ScientificPaperGenre() { return label; }

    @Override public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        return bundle.getString((label.split(" "))[0]); }

}
