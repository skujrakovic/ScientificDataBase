package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;


public class AddController {
    public TextField fldTitle, fldAuthors, fldYear, fldLink;
    public TextArea areaSummary;
    public ChoiceBox<ScientificPaperGenre> choiceGenre;
    public ChoiceBox<ScientificPaperType> choiceType;
    public Button btnAdd;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();


    @FXML
    public void initialize (){
        choiceGenre.setItems(FXCollections.observableArrayList(ScientificPaperGenre.values()));
        choiceType.setItems(FXCollections.observableArrayList(ScientificPaperType.values()));
    }

    public void Add(ActionEvent actionEvent){
        ScientificPaper paper = new ScientificPaper(fldTitle.getText(), fldLink.getText(), areaSummary.getText(), Integer.valueOf(fldYear.getText()), choiceGenre.getValue(), choiceType.getValue());
        paper.setAuthors(new ArrayList<String>(Arrays.asList(fldAuthors.getText().toString().split(","))));
        scienceChestDAO.addScientificPaper(paper);
    }
}
