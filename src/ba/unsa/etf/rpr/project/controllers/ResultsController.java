package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ResultsController {
    public TableView<ScientificPaper> tableViewResults;
    public TableColumn<ScientificPaper, ArrayList<String>> colAuthors;
    public TableColumn<ScientificPaper, String> colTitle;
    public TableColumn<ScientificPaper, ScientificPaperGenre> colGenre;
    public TableColumn<ScientificPaper, ScientificPaperType> colType;
    public Hyperlink link;
    public ChoiceBox<ScientificPaperGenre> choiceGenre;
    public ChoiceBox<ScientificPaperType> choiceType;
    public Button btnDownload;
    private ObservableList<ScientificPaper> scientificPapersList;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();

    public ResultsController() {
        scientificPapersList = FXCollections.observableList(scienceChestDAO.getResults());
    }

    @FXML
    public void initialize() {
        link.setDisable(true);
        btnDownload.setDisable(true);
        tableViewResults.setItems(scientificPapersList);
        colType.setCellValueFactory(new PropertyValueFactory<ScientificPaper, ScientificPaperType>("type"));
        colTitle.setCellValueFactory(new PropertyValueFactory<ScientificPaper, String>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<ScientificPaper, ScientificPaperGenre>("genre"));
        colGenre.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ScientificPaperGenre genre, boolean empty) {
                super.updateItem(genre, empty);
                if (genre == null || empty) {
                    setGraphic(null);
                } else {
                    Label label = new Label(genre.toString());
                    label.setStyle("-fx-text-fill: white;");
                    setGraphic(label);
                }
            }
        });
        colAuthors.setCellValueFactory(new PropertyValueFactory<ScientificPaper, ArrayList<String>>("authors"));
        colAuthors.setCellFactory(column -> new TableCell<>() {
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
        tableViewResults.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                link.setDisable(false);
                btnDownload.setDisable(false);
            } else {
                link.setDisable(true);
                btnDownload.setDisable(true);
            }
        });
        choiceGenre.setItems(FXCollections.observableArrayList(ScientificPaperGenre.values()));
        choiceType.setItems(FXCollections.observableArrayList(ScientificPaperType.values()));

    }

    public void openWebsite(ActionEvent actionEvent) {
        try {
            URL url = new URL(tableViewResults.getSelectionModel().getSelectedItem().getLink());
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(tableViewResults.getSelectionModel().getSelectedItem().getLink()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("String " + tableViewResults.getSelectionModel().getSelectedItem().getLink() + " does not represent a valid URL");
        }

    }

    public void filter(ActionEvent actionEvent) {
        if (!choiceGenre.getSelectionModel().isEmpty()) {
            scientificPapersList = scientificPapersList.stream().filter((scientificPaper -> scientificPaper.getGenre().equals(choiceGenre.getSelectionModel().getSelectedItem()))).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
        if (!choiceType.getSelectionModel().isEmpty()) {
            scientificPapersList = scientificPapersList.stream().filter((scientificPaper -> scientificPaper.getType().equals(choiceType.getSelectionModel().getSelectedItem()))).collect(Collectors.toCollection(FXCollections::observableArrayList));
            ;
        }
        tableViewResults.setItems(scientificPapersList);
    }

    public void saveFile(ActionEvent actionEvent) {
        Thread thread = new Thread(() -> {
            scienceChestDAO.writeToFile(tableViewResults.getSelectionModel().getSelectedItem());
        });
        thread.start();
    }
}