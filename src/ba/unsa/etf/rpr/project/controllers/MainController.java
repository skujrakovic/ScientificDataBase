package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class MainController {
    public Button btnSearch;
    public ImageView imgArts, imgBusiness, imgChemistry, imgGeography, imgEconomics, imgEngineering, imgBiology, imgMedicine, imgPhysics, imgPsychology;
    public TextField fldSearch;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();

    @FXML
    public void initialize(){
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
    }

    public void openResultsFor (ScientificPaperGenre genre){
        Parent root = null;
        try {
            scienceChestDAO.getScientificPaperByGenre(genre);
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/results.fxml"));
            ResultsController lctrl=new ResultsController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Results");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Search (ActionEvent actionEvent){
        Parent root = null;
        try {
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/results.fxml"));
            ResultsController lctrl=new ResultsController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Results");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
