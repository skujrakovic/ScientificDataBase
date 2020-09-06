package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AddController {
    public TextField fldTitle, fldAuthors, fldYear, fldLink;
    public TextArea areaSummary;
    public ChoiceBox<ScientificPaperGenre> choiceGenre;
    public ChoiceBox<ScientificPaperType> choiceType;


    @FXML
    public void initialize (){
        choiceGenre.setItems(FXCollections.observableArrayList(ScientificPaperGenre.values()));
        choiceType.setItems(FXCollections.observableArrayList(ScientificPaperType.values()));
    }
}
