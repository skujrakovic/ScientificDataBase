package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddControllerTest {
    Stage theStage;
    AddController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"), bundle);
        ctrl = new AddController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Add new scientific paper");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    @Order(1)
    public void testAddEmpty(FxRobot robot){
        robot.clickOn("#btnAdd");
        TextField field = robot.lookup("#fldTitle").queryAs(TextField.class);
        assertTrue(SignUpControllerTest.containsStyle(field, "incorrectField"));
        field = robot.lookup("#fldAuthors").queryAs(TextField.class);
        assertTrue(SignUpControllerTest.containsStyle(field, "incorrectField"));
        field = robot.lookup("#fldLink").queryAs(TextField.class);
        assertTrue(SignUpControllerTest.containsStyle(field, "incorrectField"));
    }

    @Test
    @Order(2)
    public void testAddScientificPaper(FxRobot robot){
        robot.clickOn("#fldTitle").write("Title");
        robot.clickOn("#fldAuthors").write("Author1,Author2");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                robot.lookup("#choiceGenre").queryAs(ChoiceBox.class).getSelectionModel().select(0);
                robot.lookup("#choiceType").queryAs(ChoiceBox.class).getSelectionModel().select(0);
            }
        });
        robot.clickOn("#fldLink").write("http://www.google.com");
        robot.clickOn("#areaSummary").write("This is a summary");
        robot.clickOn("#btnAdd");
        assertFalse(ScienceChestDAO.getInstance().getScientificPaperByTitle("Title").isEmpty());
    }
}