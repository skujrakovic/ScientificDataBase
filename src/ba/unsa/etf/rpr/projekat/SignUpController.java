package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    public Button btnSignUp;
    public Hyperlink linkLogIn;
    public TextField fldName, fldSurname, fldUsername, fldEmail, fldPassword;
    private UserModel userModel = UserModel.getInstance();
    @FXML
    public void initialize() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SignUp(ActionEvent actionEvent){
        User newUser = new User(fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldUsername.getText(), fldPassword.getText());
        userModel.addUser(newUser);
    }
}
