package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.javabeans.User;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;


import java.io.File;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LogInControllerTest {
    Stage theStage;
    LogInController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        ScienceChestDAO.disconnect();
        File dbfile = new File("database.db");
        dbfile.delete();
        ScienceChestDAO dao = ScienceChestDAO.getInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"), bundle);
        ctrl = new LogInController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Log in");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    @Order(1)
    public void testLogIn(FxRobot robot){
        //adding a user
        ScienceChestDAO scienceChestDAO = ScienceChestDAO.getInstance();
        User user = new User("test","test","test@test.com","test","test12345");
        scienceChestDAO.addUser(user);
        //signing up as added user
        robot.clickOn("#fldUsername").write("test");
        robot.clickOn("#fldPassword").write("test12345");
        robot.clickOn("#btnLogIn");
        //checking if the new window (main.fxml) opened and exiting it
        robot.lookup("#btnLogOut").tryQuery().isPresent();
        robot.clickOn("#btnLogOut");
    }

    @Test
    @Order(2)
    public void testNonExistingAccountLogIn(FxRobot robot){
        robot.clickOn("#fldUsername").write("DoesntExist");
        robot.clickOn("#fldPassword").write("invisible123");
        robot.clickOn("#btnLogIn");
        //checking if the alert dialog shows
        robot.lookup(".dialog-pane").tryQuery().isPresent();
        DialogPane dialogPane = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        robot.clickOn(okButton);
        Platform.runLater(()->{
            theStage.close();
        });
    }

}