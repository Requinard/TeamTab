package gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Game.Game;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    Stage stage;
    IView currentView;
    Thread gameThread;
    Game game;

    public StageController(Stage primaryStage) {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        game = new Game();

    }

    public void loadScene(@NotNull IView nextView) {
        currentView.deload();

        nextView.load();

        refreshView();
    }

    public void loadScene(Scene scene) {

        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    public void refreshView() {
        return;
    }

}
