package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.javabeans.User;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import com.sun.javafx.scene.control.ContextMenuContent;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.robot.Motion;

import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainControllerTest {
    Stage theStage;
    MainController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"), bundle);
        ctrl = new MainController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("ScienceChest");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();

        theStage = stage;
    }

    @Test
    @Order(1)
    public void testOpenResultsForGenre(FxRobot robot){
        //testing if when choosing one genre the results show only papers which are acceptable
        robot.lookup("#imgArts").tryQuery().isPresent();
        robot.clickOn("#imgArts");
        robot.lookup("#tableViewResults").tryQuery().isPresent();
        TableView table =robot.lookup("#tableViewResults").queryAs(TableView.class);
        ObservableList<ScientificPaper> papers = table.getItems();
        for(ScientificPaper paper: papers){
            assertEquals(paper.getGenre(), ScientificPaperGenre.ART);
        }
        //robot.clickOn("#file").moveTo("#close").clickOn("#close");
    }

    @Test
    @Order(2)
    public void testSearch(FxRobot robot){
        robot.clickOn("#fldSearch").write("Advance");
        robot.clickOn("#btnSearch");
        robot.lookup("#tableViewResults").tryQuery().isPresent();
        TableView table =robot.lookup("#tableViewResults").queryAs(TableView.class);
        ObservableList<ScientificPaper> papers = table.getItems();
        for(ScientificPaper paper: papers){
            assertTrue(paper.getTitle().contains("Advance"));
        }
        Platform.runLater(()->{
            theStage.close();
        });
    }
}