package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LogInController {
    public TextField fldUsername;
    public PasswordField fldPassword;
    public Button btnLogIn;
    public ImageView imgLogo;
    public Hyperlink linkSignUp;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();
    @FXML
    public void initialize(){
        imgLogo.setPickOnBounds(true);
        imgLogo.setOnMouseClicked((MouseEvent e) -> {
            Parent root = null;
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                Stage myStage = new Stage();
                FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"), bundle);
                HomepageController lctrl = new HomepageController();
                loaderr.setController(lctrl);
                root = loaderr.load();
                myStage.setTitle("Homepage");
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    public void SignUp(ActionEvent actionEvent) {
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"), bundle);
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
    public void LogIn(ActionEvent actionEvent){
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            scienceChestDAO.logInUser(fldUsername.getText());
            Stage myStage=new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/main.fxml"), bundle);
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
