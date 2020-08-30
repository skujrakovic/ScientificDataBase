package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
    public Button btnSearch;
    @FXML
    public void initialize(){
        Image imageDecline = new Image(String.valueOf(getClass().getResource("/images/logo.png")));
        btnSearch.setGraphic(new ImageView(imageDecline));
    }
}
