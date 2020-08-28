package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomepageController {
    Button btnSignUp, btnLogIn, btnContinue;
    @FXML
    public void initialize() {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
