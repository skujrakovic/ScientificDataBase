package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.javabeans.User;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUpController {
    public Button btnSignUp;
    public Hyperlink linkLogIn;
    public TextField fldName, fldSurname, fldUsername, fldEmail;
    public PasswordField fldPassword;
    public ImageView imgLogo;
    private ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();

    @FXML
    public void initialize() {
        fldUsername.setPromptText("Use 6 or more characters");
        fldPassword.setPromptText("Use 8 or more characters with a mix of letters & numbers");
        fldName.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldName.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty()) {
                        System.out.println("tu");
                        fldName.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldSurname.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldSurname.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty()) {
                        System.out.println("tu");
                        fldSurname.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldEmail.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldEmail.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty() && newName.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        System.out.println("tu");
                        fldEmail.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldUsername.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldUsername.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty() && newName.length()>=6) {
                        System.out.println("tu");
                        fldUsername.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
        fldPassword.textProperty().addListener((obs, oldName, newName) -> {
                    if (fldPassword.getStyleClass().toString().contains("incorrectField") && !newName.isEmpty() && newName.length()>=8 && newName.matches("^[a-zA-Z0-9]+$")) {
                        System.out.println("tu");
                        fldPassword.getStyleClass().removeAll("incorrectField");
                    }
                }
        );
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

    public void LogIn(ActionEvent actionEvent) {
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            Stage myStage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), bundle);
            LogInController lctrl = new LogInController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle("Log In");
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SignUp(ActionEvent actionEvent) {
        if (fldUsername.getText().equals("") || fldUsername.getText().length() < 6)
            fldUsername.getStyleClass().add("incorrectField");
        if (fldName.getText().equals("")) fldName.getStyleClass().add("incorrectField");
        if (fldSurname.getText().equals("")) fldSurname.getStyleClass().add("incorrectField");
        if (fldEmail.getText().equals("") || !fldEmail.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) fldEmail.getStyleClass().add("incorrectField");
        if (fldPassword.getText().equals("") || fldPassword.getText().length() < 8 || !fldPassword.getText().matches("^[a-zA-Z0-9]+$"))
            fldPassword.getStyleClass().add("incorrectField");
        if(scienceChestDAO.usernameExists(fldUsername.getText())) {
            fldUsername.getStyleClass().add("incorrectField");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("The username already exists, please choose another one.");
            alert.showAndWait();

        }
        String check = fldUsername.getStyleClass().toString()+fldName.getStyleClass().toString()+fldSurname.getStyleClass().toString()+fldEmail.getStyleClass().toString()+fldPassword.getStyleClass().toString();
        if(!check.contains("incorrectField")) {
            User newUser = new User(fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldUsername.getText(), fldPassword.getText());
            scienceChestDAO.addUser(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully created your account!");

            alert.showAndWait();
            linkLogIn.fire();
        }
    }
}
