package ba.unsa.etf.rpr.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomepageController {
    public Button btnSignUp, btnLogIn, btnContinue, btnEnglish, btnBosnian;
    @FXML
    public void initialize() {
        Image imageEnglish = new Image(getClass().getResourceAsStream("/images/britain.png"));
        ImageView img = new ImageView(imageEnglish);
        img.setFitWidth(25);
        img.setFitHeight(25);
        btnEnglish.setGraphic(img);
        Image imageBosnian = new Image(getClass().getResourceAsStream("/images/bosniann.png"));
        ImageView img1 = new ImageView(imageBosnian);
        img1.setFitWidth(65);
        img1.setFitHeight(38);
        btnBosnian.setGraphic(img1);
    }
    public void SignUp(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"));
            SignUpController sctrl=new SignUpController();
            loaderr.setController(sctrl);
            root = loaderr.load();
            myStage.setTitle("Sign Up");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void LogIn(ActionEvent actionEvent) {
        Parent root = null;
        try {
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            LogInController lctrl=new LogInController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Log In");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Continue (ActionEvent actionEvent){
        Parent root = null;
        try {
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            MainController lctrl=new MainController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("ScienceChest");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
