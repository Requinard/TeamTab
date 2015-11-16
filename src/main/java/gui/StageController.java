package gui;

import game.Game;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    Stage stage;
    IView currentView;
    Thread gameThread;
    Game game;

    public StageController(Stage primaryStage) throws UnsupportedEncodingException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        game = new Game(this);

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
    }

    public void resetGame(){
        this.game = new Game(this);
    }

    public void setExistingGame(Game game){
        this.game = game;
    }



}
