package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.exceptions.InvalidUrlException;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        fldTitle.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldTitle.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty()) {
                        System.out.println("tu");
                        fldTitle.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldAuthors.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldAuthors.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty()) {
                        System.out.println("tu");
                        fldAuthors.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldLink.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldLink.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty()) {
                        System.out.println("tu");
                        fldLink.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        choiceGenre.valueProperty().addListener((observableValue, scientificPaperGenreSingleSelectionModel, t1) -> {
            if(t1!=null) choiceGenre.getStyleClass().removeAll("incorrectField");
        });
        choiceType.valueProperty().addListener( (observableValue, scientificPaperTypeSingleSelectionModel, t1) -> {
            if(t1!=null) choiceType.getStyleClass().removeAll("incorrectField");
        });
    }

    public void Add(ActionEvent actionEvent){
        ScientificPaper paper = null;
        try {
            if(fldTitle.getText().equals("")) fldTitle.getStyleClass().add("incorrectField");
            if(fldAuthors.getText().equals("")) fldAuthors.getStyleClass().add("incorrectField");
            if(fldLink.getText().equals("")) fldLink.getStyleClass().add("incorrectField");
            if(choiceGenre.getSelectionModel().getSelectedItem()==null) choiceGenre.getStyleClass().add("incorrectField");
            if(choiceType.getSelectionModel().getSelectedItem()==null) choiceType.getStyleClass().add("incorrectField");
            String check=fldAuthors.getStyleClass().toString()+fldLink.getStyleClass().toString()+fldTitle.getStyleClass().toString()+choiceType.getStyleClass().toString()+choiceType.getStyleClass().toString();
            if(!check.contains("incorrectField")) {
                paper = new ScientificPaper(fldTitle.getText(), fldLink.getText(), areaSummary.getText(), spinnerYear.getValue(), choiceGenre.getValue(), choiceType.getValue());
                paper.setAuthors(new ArrayList<String>(Arrays.asList(fldAuthors.getText().toString().split(","))));
                scienceChestDAO.addScientificPaper(paper);
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
        } catch (InvalidUrlException e) {
            System.out.println(e.getMessage());
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
