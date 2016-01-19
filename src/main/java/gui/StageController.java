package gui;

import Game.ClientGame;
import Game.HostGame;
import Game.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    static Player currentPlayer;
    Stage stage;
    IView currentView;
    ClientGame clientGame;
    HostGame hostGame;

    public StageController(Stage primaryStage) throws UnsupportedEncodingException, UnknownHostException, java.rmi.UnknownHostException {
        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
    }

    public void setClientGame(ClientGame clientGame) {
        this.clientGame = clientGame;
    }

    public void setHostGame(HostGame hostGame) {
        this.hostGame = hostGame;
    }

    public void loadScene(@NotNull IView nextView) {
        currentView.deload();
        nextView.load();
    }

    public void loadScene(Scene scene) {
        stage.setTitle("Defusal Squad");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
}
