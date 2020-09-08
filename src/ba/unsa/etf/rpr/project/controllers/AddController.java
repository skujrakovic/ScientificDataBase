package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.exceptions.InvalidUrlException;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class AddController {
    public TextField fldTitle, fldAuthors, fldLink;
    public Spinner<Integer> spinnerYear;
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
        ScientificPaper paper = null;
        try {
            paper = new ScientificPaper(fldTitle.getText(), fldLink.getText(), areaSummary.getText(), spinnerYear.getValue(), choiceGenre.getValue(), choiceType.getValue());
            paper.setAuthors(new ArrayList<String>(Arrays.asList(fldAuthors.getText().toString().split(","))));
            scienceChestDAO.addScientificPaper(paper);
        } catch (InvalidUrlException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(bundle.getString("warning"));
            alert.setContentText(bundle.getString("url"));
            alert.setHeaderText(null);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }
}
