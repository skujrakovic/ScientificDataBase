package ba.unsa.etf.rpr.project.controllers;

import ba.unsa.etf.rpr.project.enums.ScientificPaperGenre;
import ba.unsa.etf.rpr.project.enums.ScientificPaperType;
import ba.unsa.etf.rpr.project.javabeans.ScientificPaper;
import ba.unsa.etf.rpr.project.utilities.ScienceChestDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javax.swing.text.TabableView;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResultsControllerTest {
    Stage theStage;
    ResultsController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/results.fxml"), bundle);
        ctrl = new ResultsController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Results");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    @Order(1)
    public void testFilterByGenre(FxRobot robot){
        //setting the items in tableview
        robot.lookup("#tableViewResults").queryAs(TableView.class).setItems(FXCollections.observableArrayList(ScienceChestDAO.getInstance().getScientificPaperByTitle("Advance")));
        //choosing a genre to filter by
        robot.lookup("#choiceGenre").tryQuery().isPresent();
        robot.clickOn("#choiceGenre").moveTo(1,1);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        //pressing the button which leads to filtering
        robot.clickOn("#btnConfirm");
        WaitForAsyncUtils.waitForFxEvents();
        TableView table =robot.lookup("#tableViewResults").queryAs(TableView.class);
        ObservableList<ScientificPaper> papers = table.getItems();
        for(ScientificPaper paper: papers){
            assertEquals(robot.lookup("#choiceGenre").queryAs(ChoiceBox.class).getSelectionModel().getSelectedItem(), paper.getGenre());
        }
    }

    @Test
    @Order(2)
    public void testFilterByType (FxRobot robot){
        //setting the items in tableview
        robot.lookup("#tableViewResults").queryAs(TableView.class).setItems(FXCollections.observableArrayList(ScienceChestDAO.getInstance().getScientificPaperByTitle("Advance")));
        //choosing a type to filter by
        robot.lookup("#choiceType").tryQuery().isPresent();
        robot.clickOn("#choiceType").moveTo(1,1);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        //pressing the button which leads to filtering
        robot.clickOn("#btnConfirm");
        WaitForAsyncUtils.waitForFxEvents();
        TableView table =robot.lookup("#tableViewResults").queryAs(TableView.class);
        ObservableList<ScientificPaper> papers = table.getItems();
        for(ScientificPaper paper: papers){
            assertEquals(robot.lookup("#choiceType").queryAs(ChoiceBox.class).getSelectionModel().getSelectedItem(), paper.getType());
        }
    }

    @Test
    @Order(3)
    public void testOptionsEnabled(FxRobot robot){
        //setting the items in tableview
        robot.lookup("#tableViewResults").queryAs(TableView.class).setItems(FXCollections.observableArrayList(ScienceChestDAO.getInstance().getScientificPaperByTitle("Advance")));
        //checking if the link is disabled
        assertTrue(robot.lookup("#link").queryAs(Hyperlink.class).isDisabled());
        //checking if the button is disabled
        assertTrue(robot.lookup("#btnDownload").queryAs(Button.class).isDisabled());
        robot.lookup("#tableViewResults").queryAs(TableView.class).getSelectionModel().select(0);
        //checking if the link is disabled
        assertFalse(robot.lookup("#link").queryAs(Hyperlink.class).isDisabled());
        //checking if the button is disabled
        assertFalse(robot.lookup("#btnDownload").queryAs(Button.class).isDisabled());
        Platform.runLater(()->{
            theStage.close();
        });
    }

}