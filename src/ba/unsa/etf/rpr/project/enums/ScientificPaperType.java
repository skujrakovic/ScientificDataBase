package ba.unsa.etf.rpr.project.enums;

import java.util.ResourceBundle;

public enum ScientificPaperType {
    BOOK("Book"),
    JOURNAL("Journal");

    private String label;

    ScientificPaperType(String label) {
        this.label = label;
    }

    public String ScientificPaperType() {
        return label;
    }

    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        return bundle.getString(label);
    }
}
