package ba.unsa.etf.rpr.project.enums;

public enum ScientificPaperGenre {
    ART,
    BIOLOGY,
    BUSINESS,
    CHEMISTRY,
    ENGINEERING,
    ECONOMICS,
    GEOGRAPHY,
    MEDICINE,
    PHYSICS,
    PSYCHOLOGY;

    public static String convert(ScientificPaperGenre genre) {
        switch (genre){
            case ART:
                return "Arts and humanities";
            case BUSINESS:
                return "Business and management";
            case CHEMISTRY:
                return "Chemistry and Materials Science";
            case GEOGRAPHY:
                return "Eart Sciences and Geography";
            case ECONOMICS:
                return "Economics and Finance";
            case ENGINEERING:
                return "Engineering and Computer Science";
            case BIOLOGY:
                return "Life Sciences and Biology";
            case MEDICINE:
                return "Medical and Health Sciences";
            case PHYSICS:
                return "Physics and Mathematics";
            case PSYCHOLOGY:
                return "Social Sciences and Psychology";
        }
        return null;
    }
}
