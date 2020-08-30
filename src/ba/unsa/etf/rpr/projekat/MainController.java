package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainController {
    public Button btnSearch;
    public ImageView imgArts, imgBusiness, imgChemistry, imgGeography, imgEconomics, imgEngineering, imgBiology, imgMedicine, imgPhysics, imgPsychology;
    private UserModel userModel = UserModel.getInstance();
    @FXML
    public void initialize(){
        Image imageDecline = new Image(getClass().getResourceAsStream("/images/search2.png"));
        ImageView img = new ImageView(imageDecline);
        img.setFitWidth(20);
        img.setFitHeight(20);
        btnSearch.setGraphic(img);
    }
    public void Test (ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully logged in!");

        alert.showAndWait();
    }
}
