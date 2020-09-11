package ba.unsa.etf.rpr.project.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SignUpControllerTest {
    Stage theStage;
    SignUpController ctrl;

    public static boolean containsStyle(TextField field, String style) {
        return field.getStyleClass().toString().contains(style);
    }

    @Start
    public void start (Stage stage) throws Exception {
        File dbfile = new File("database.db");
        dbfile.delete();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"),bundle);
        ctrl = new SignUpController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    private static void signUp(FxRobot robot){
        TextField polje = robot.lookup("#fldName").queryAs(TextField.class);
        assertTrue(containsStyle(polje, "incorrectField"));
        robot.clickOn("#fldName").write("Name");
        assertTrue(containsStyle(polje, ""));

        polje = robot.lookup("#fldSurname").queryAs(TextField.class);
        assertTrue(containsStyle(polje, "incorrectField"));
        robot.clickOn("#fldSurname").write("Surname");
        assertTrue(containsStyle(polje, ""));

        polje = robot.lookup("#fldUsername").queryAs(TextField.class);
        assertTrue(containsStyle(polje, "incorrectField"));
        robot.clickOn("#fldUsername").write("Username");
        assertTrue(containsStyle(polje, ""));

        polje = robot.lookup("#fldEmail").queryAs(TextField.class);
        assertTrue(containsStyle(polje, "incorrectField"));
        robot.clickOn("#fldEmail").write("email@email.com");
        assertTrue(containsStyle(polje, ""));


        polje = robot.lookup("#fldPassword").queryAs(TextField.class);
        assertTrue(containsStyle(polje, "incorrectField"));
        robot.clickOn("#fldPassword").write("password123");
        assertTrue(containsStyle(polje, ""));
    }

    @Test
    @Order(1)
    public void testSignUp(FxRobot robot){
        robot.clickOn("#btnSignUp");

        signUp(robot);

        robot.clickOn("#btnSignUp");
        //checking if the main window opened after a successful signup
        robot.lookup("#btnLogOut").tryQuery().isPresent();
        robot.clickOn("#btnLogOut");
    }

    @Test
    @Order(2)
    public void testSignUpWithExistingUsername(FxRobot robot){
        robot.clickOn("#btnSignUp");
        //testing signing up with an existing username (from the test before)
        signUp(robot);

        robot.clickOn("#btnSignUp");
        //checking for the alert dialog
        robot.lookup("OK").tryQuery().isPresent();
        robot.lookup("The username already exists, please choose another one.").tryQuery().isPresent();

        robot.clickOn("OK");
    }

    @Test
    @Order(3)
    public void testGoToLogIn(FxRobot robot){
        //testing the LogIn hyperlink
        robot.clickOn("#linkLogIn");
        robot.lookup("#btnLogIn").tryQuery().isPresent();
    }
}