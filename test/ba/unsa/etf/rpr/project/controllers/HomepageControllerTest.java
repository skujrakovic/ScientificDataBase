package ba.unsa.etf.rpr.project.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class HomepageControllerTest {
    Stage theStage;
    HomepageController ctrl;

    @Start
    public void start (Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"),bundle);
        ctrl = new HomepageController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Homepage");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();

        stage.toFront();

        theStage = stage;
    }

    @Test
    public void testChangeLanguage(FxRobot robot){
        robot.clickOn("#btnBosnian");
        Button btnSignUp = robot.lookup("#btnSignUp").queryAs(Button.class);
        assertEquals("Registriraj se", btnSignUp.getText());
        robot.clickOn("#btnEnglish");
        Button btnLogIn = robot.lookup("#btnLogIn").queryAs(Button.class);
        assertEquals("Log in", btnLogIn.getText());
    }
}