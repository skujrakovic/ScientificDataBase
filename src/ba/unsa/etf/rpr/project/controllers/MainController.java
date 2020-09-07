package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class MainController {
    public Button btnSearch, btnLogOut;
    public ImageView imgArts, imgBusiness, imgChemistry, imgGeography, imgEconomics, imgEngineering, imgBiology, imgMedicine, imgPhysics, imgPsychology;
    public TextField fldSearch;
    public MenuItem close, add, about;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();

    @FXML
    public void initialize(){
        if(scienceChestDAO.getCurrentUser()==null) add.setDisable(true);
        else add.setDisable(false);
        Image imageDecline = new Image(getClass().getResourceAsStream("/images/search2.png"));
        ImageView img = new ImageView(imageDecline);
        img.setFitWidth(20);
        img.setFitHeight(20);
        btnSearch.setGraphic(img);
        fldSearch.setTooltip(new Tooltip("Search by title"));
        imgArts.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("ART"));
        });
        imgBiology.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("BIOLOGY"));
        });
        imgBusiness.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("BUSINESS"));
        });
        imgChemistry.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("CHEMISTRY"));
        });
        imgEconomics.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("ECONOMICS"));
        });
        imgEngineering.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("ENGINEERING"));
        });
        imgGeography.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("GEOGRAPHY"));
        });
        imgMedicine.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("MEDICINE"));
        });
        imgPsychology.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("PSYCHOLOGY"));
        });
        imgPhysics.setOnMouseClicked((MouseEvent e) -> {
            openResultsFor(ScientificPaperGenre.valueOf("PHYSICS"));
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Parent root = null;
                try {
                    ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                    Stage myStage=new Stage();
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/add.fxml"), bundle);
                    AddController lctrl=new AddController();
                    loaderr.setController(lctrl);
                    root = loaderr.load();
                    myStage.setTitle("Add new scientific paper");
                    myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    myStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Credits for icons: freepik, monkik, Nhor Phai, Smashicons, phatplus.\nAll of the icons were downloaded from www.flaticon.com\nCredits for ScienceChest logo: www.freelogodesign.com");
                alert.showAndWait();
            }
        });
    }

    public void openResultsFor (ScientificPaperGenre genre){
            scienceChestDAO.getScientificPaperByGenre(genre);
            openResults();
    }

    public void Search (ActionEvent actionEvent){
        if(fldSearch.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You need to type something into the search field.");
            alert.showAndWait();
        }else {
            scienceChestDAO.getScientificPaperByTitle(fldSearch.getText());
            if (scienceChestDAO.getResults() == null || scienceChestDAO.getResults().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("No results found.");
                alert.showAndWait();
                return;
            }
           openResults();
        }
    }

    public void openResults(){
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            Stage myStage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/results.fxml"), bundle);
            ResultsController lctrl = new ResultsController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Results");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOut(ActionEvent actionEvent){
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            scienceChestDAO.setCurrentUser(null);
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"), bundle);
            HomepageController lctrl=new HomepageController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Homepage");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
