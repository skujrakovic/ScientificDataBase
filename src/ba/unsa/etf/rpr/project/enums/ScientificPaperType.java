package ba.unsa.etf.rpr.project.enums;

public enum ScientificPaperType {
    BOOK("Book"),
    JOURNAL("Journal");

    private String label;

    ScientificPaperType(String label) {
        this.label = label;
    }

    public String ScientificPaperType() { return label; }

    @Override public String toString() { return label; }
}
