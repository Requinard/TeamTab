package gui;

import Game.ClientGame;
import Game.HostGame;
import Game.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import tracker.JanitorSingleton;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.TimerTask;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    static Player currentPlayer;
    Stage stage;
    IView currentView;
    Thread gameThread;
    ClientGame clientGame;
    HostGame hostGame;


    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    //private AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");

    public StageController(Stage primaryStage) throws UnsupportedEncodingException, UnknownHostException, java.rmi.UnknownHostException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    //audioPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        JanitorSingleton.getInstance().trackTimer(timerRefresh);
        timerRefresh.schedule(timerTask, 0, 20000);
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

        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    /**
     * @param clientGame
     */
    public void setExistingGame(ClientGame clientGame) {

    }


}
