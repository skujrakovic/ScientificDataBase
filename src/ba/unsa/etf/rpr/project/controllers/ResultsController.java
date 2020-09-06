package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.*;


public class ResultsController {
    public TableView<ScientificPaper> tableViewResults;
    public TableColumn<ScientificPaper,ArrayList<String>> colAuthors;
    public TableColumn<ScientificPaper,String> colTitle;
    public TableColumn<ScientificPaper, ScientificPaperGenre> colGenre;
    public TableColumn<ScientificPaper, ScientificPaperType> colType;
    private ObservableList<ScientificPaper> scientificPapersList;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();

    public ResultsController(){
        scientificPapersList= FXCollections.observableList(scienceChestDAO.getResults());
    }

    @FXML
    public void initialize(){
        tableViewResults.setItems(scientificPapersList);
        colType.setCellValueFactory(new PropertyValueFactory<ScientificPaper,ScientificPaperType>("type"));
        colTitle.setCellValueFactory(new PropertyValueFactory<ScientificPaper, String>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<ScientificPaper, ScientificPaperGenre>("genre"));
        colGenre.setCellFactory(column-> new TableCell<>() {
            @Override
            protected void updateItem(ScientificPaperGenre genre, boolean empty) {
                super.updateItem(genre, empty);
                if (genre == null || empty) {
                    setGraphic(null);
                } else {
                    Label label = new Label (genre.toString());
                    label.setStyle("-fx-text-fill: white;");
                    setGraphic(label);
                }
            }
        });
        colAuthors.setCellValueFactory(new PropertyValueFactory<ScientificPaper,ArrayList<String>>("authors"));
        colAuthors.setCellFactory(column-> new TableCell<>() {
            @Override
            protected void updateItem(ArrayList<String> items, boolean empty) {
                super.updateItem(items, empty);
                if (items == null || empty) {
                    setGraphic(null);
                } else {
                    VBox vbox = new VBox();
                    for (int i = 0; i < items.size(); i++) {
                        Label lbl = new Label(items.get(i));
                        lbl.setStyle("-fx-text-fill: white;");
                        vbox.getChildren().add(lbl);
                    }
                    setGraphic(vbox);

                }
            }
        });
    }
}