package gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import junit.framework.Assert;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;


/**
 * Created by frank on 04/01/2016.
 */
public class MainViewTest extends GuiTest {
    private Stage stage;
    private StageController stageController;

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {

            parent = FXMLLoader.load(this.getClass().getResource("/MainView.fxml"));
            return parent;
        } catch (IOException ex) {
            System.out.println("ScoreView.fxml could not be loaded");
        }
        return parent;
    }

    @Test
    public void StartGame() {

        TextField firstname = find("#userName");
        firstname.setText("bennet");

        Assert.assertEquals(firstname.getText(), "bennet");

        Button startGame = find("#buttonStart");

        click(startGame);
        click("#buttonJoin");

    }



}
