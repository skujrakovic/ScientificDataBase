package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.Main;
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
    ResourceBundle bundle = ResourceBundle.getBundle("Translation");

    @FXML
    public void initialize() {
        fldUsername.setPromptText(bundle.getString("usernamePrompt"));
        fldPassword.setPromptText(bundle.getString("passwordPrompt"));
        fldName.textProperty().addListener((obs, oldName, newName) -> {
                    if (!newName.isEmpty()) {
                        fldName.getStyleClass().removeAll("incorrectField");
                    }else{
                        fldName.getStyleClass().add("incorrectField");
                    }
                }
        );
        fldSurname.textProperty().addListener((obs, oldName, newName) -> {
                    if (!newName.isEmpty()) {
                        fldSurname.getStyleClass().removeAll("incorrectField");
                    }else{
                        fldSurname.getStyleClass().add("incorrectField");
                    }
                }
        );
        fldEmail.textProperty().addListener((obs, oldName, newName) -> {
                    if (!newName.isEmpty() && newName.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        fldEmail.getStyleClass().removeAll("incorrectField");
                    }else{
                        fldEmail.getStyleClass().add("incorrectField");
                    }
                }
        );
        fldUsername.textProperty().addListener((obs, oldName, newName) -> {
                    if (!newName.isEmpty() && newName.length()>=6) {
                        fldUsername.getStyleClass().removeAll("incorrectField");
                    }else{
                        fldUsername.getStyleClass().add("incorrectField");
                    }
                }
        );
        fldPassword.textProperty().addListener((obs, oldName, newName) -> {
                    if (!newName.isEmpty() && newName.length()>=8 && newName.matches("^[a-zA-Z0-9]+$")) {
                        fldPassword.getStyleClass().removeAll("incorrectField");
                    }else{
                        fldPassword.getStyleClass().add("incorrectField");
                    }
                }
        );
        imgLogo.setPickOnBounds(true);
        imgLogo.setOnMouseClicked((MouseEvent e) -> {
            Parent root = null;
            try {
                Stage myStage = new Stage();
                FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"), bundle);
                HomepageController lctrl = new HomepageController();
                loaderr.setController(lctrl);
                root = loaderr.load();
                myStage.setTitle(bundle.getString("homepage"));
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
            Stage myStage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), bundle);
            LogInController lctrl = new LogInController();
            loaderr.setController(lctrl);
            root = loaderr.load();
            myStage.setTitle(bundle.getString("login"));
            myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            myStage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SignUp(ActionEvent actionEvent) {
        if (fldName.getText().equals("")) fldName.getStyleClass().add("incorrectField");
        if (fldSurname.getText().equals("")) fldSurname.getStyleClass().add("incorrectField");
        if (fldUsername.getText().equals("") || fldUsername.getText().length() < 6)
            fldUsername.getStyleClass().add("incorrectField");
        if (fldEmail.getText().equals("") || !fldEmail.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) fldEmail.getStyleClass().add("incorrectField");
        if (fldPassword.getText().equals("") || fldPassword.getText().length() < 8 || !fldPassword.getText().matches("^[a-zA-Z0-9]+$"))
            fldPassword.getStyleClass().add("incorrectField");
        if(scienceChestDAO.usernameExists(fldUsername.getText())) {
            System.out.println("postoji");
            fldUsername.getStyleClass().add("incorrectField");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("info"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("existingUsername"));
            alert.showAndWait();
            return;
        }
        String check = fldUsername.getStyleClass().toString()+fldName.getStyleClass().toString()+fldSurname.getStyleClass().toString()+fldEmail.getStyleClass().toString()+fldPassword.getStyleClass().toString();
        if(!check.contains("incorrectField")) {
            User newUser = new User(fldName.getText(), fldSurname.getText(), fldEmail.getText(), fldUsername.getText(), fldPassword.getText());
            scienceChestDAO.addUser(newUser);
            scienceChestDAO.logInUser(newUser.getUsername());
            Parent root = null;
            try {
                Stage myStage = new Stage();
                FXMLLoader loaderr = new FXMLLoader(getClass().getResource("/fxml/main.fxml"), bundle);
                MainController lctrl = new MainController();
                loaderr.setController(lctrl);
                root = loaderr.load();
                myStage.setTitle("ScienceChest");
                myStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                myStage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
