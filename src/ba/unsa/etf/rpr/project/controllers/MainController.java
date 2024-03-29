package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.reports.PrintReport;
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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class MainController {
    public Button btnSearch, btnLogOut;
    public ImageView imgArts, imgBusiness, imgChemistry, imgGeography, imgEconomics, imgEngineering, imgBiology, imgMedicine, imgPhysics, imgPsychology;
    public TextField fldSearch;
    public MenuItem close, add, about, print;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();
    private ResourceBundle bundle = ResourceBundle.getBundle("Translation");

    @FXML
    public void initialize() {
        if (scienceChestDAO.getCurrentUser() == null) {
            add.setDisable(true);
            btnLogOut.setText(bundle.getString("homepage"));
        } else {
            add.setDisable(false);
            btnLogOut.setText(bundle.getString("logout"));
        }
        Image imageDecline = new Image(getClass().getResourceAsStream("/images/search2.png"));
        ImageView img = new ImageView(imageDecline);
        img.setFitWidth(20);
        img.setFitHeight(20);
        btnSearch.setGraphic(img);
        fldSearch.setTooltip(new Tooltip(bundle.getString("searchTooltip")));
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
                    Stage myStage = new Stage();
                    FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/add.fxml"), bundle);
                    AddController lctrl = new AddController();
                    loaderr.setController(lctrl);
                    root = loaderr.load();
                    myStage.setTitle(bundle.getString("add"));
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
                alert.setTitle(bundle.getString("info"));
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText(bundle.getString("credits1") + "\n" + bundle.getString("credits2") + "\n" + bundle.getString("credits3"));
                alert.showAndWait();
            }
        });
        print.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrintReport report = new PrintReport();
                try {
                    report.showReport(ScienceChestDAO.getConn());
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void openResultsFor(ScientificPaperGenre genre) {
        scienceChestDAO.getScientificPaperByGenre(genre);
        openResults();
    }

    public void search(ActionEvent actionEvent) {
        if (fldSearch.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("info"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("typeSomething"));
            alert.showAndWait();
        } else {
            scienceChestDAO.getScientificPaperByTitle(fldSearch.getText());
            if (scienceChestDAO.getResults() == null || scienceChestDAO.getResults().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(bundle.getString("info"));
                alert.setHeaderText(null);
                alert.setContentText(bundle.getString("noResults"));
                alert.showAndWait();
                return;
            }
            openResults();
        }
    }

    public void openResults() {
        Parent root = null;
        try {
            Stage myStage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/results.fxml"), bundle);
            ResultsController lctrl = new ResultsController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle(bundle.getString("results"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) {
        Parent root = null;
        try {
            scienceChestDAO.setCurrentUser(null);
            Stage myStage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"), bundle);
            HomepageController lctrl = new HomepageController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle(bundle.getString("homepage"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
