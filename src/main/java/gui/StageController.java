package gui;

import Game.ClientGame;
import Game.HostGame;
import Game.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.RMI.ChatAppDefusalSquad;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.TimerTask;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    static Player currentPlayer;
    static ChatAppDefusalSquad chatAppDefusalSquad;
    Stage stage;
    IView currentView;
    Thread gameThread;
    ClientGame game;
    ClientGame clientGame;
    HostGame hostGame;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    private AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");

    public StageController(Stage primaryStage) throws UnsupportedEncodingException, UnknownHostException, java.rmi.UnknownHostException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        hostGame = new HostGame();
        chatAppDefusalSquad = new ChatAppDefusalSquad();
        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    audioPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timerRefresh.schedule(timerTask, 0, 20000);
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
        /*
        this.game = new Game(this);
           Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: er moet de methode aanageroepen worden van de clientgame die de game reset
        */
    }

    /*
    public void setExistingGame(Game game){
        this.game = game;
    }
        Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: de clientgame moet geset worden
*/

    /**
     * @param clientGame
     */
    public void setExistingGame(ClientGame clientGame) {

    }


}
