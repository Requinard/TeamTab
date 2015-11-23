package gui;

import Game.HostGame;
import Game.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javassist.bytecode.stackmap.TypeData;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    static String playerName;
    static Player currentPlayer;
    Stage stage;
    IView currentView;
    Thread gameThread;
    HostGame hostGame;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    private AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");

    public StageController(Stage primaryStage) throws UnsupportedEncodingException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        hostGame = new HostGame();
        timerRefresh = new java.util.Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    //playBackgroundMusic();
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
        log.log(Level.INFO, "The {0} is loaded", stage.getTitle());
        stage.setTitle("Defusal Squad startscreen");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    public void refreshView() {
    }

    /**
     * Author Qun
     * Hard resets the whole game
     */
    public void resetGame(){
        log.log(Level.INFO, "reset game for teams : {0}", hostGame.getTeams());
        this.hostGame = new HostGame();
    }


	/**
     * Author Qun
	 * This sets the game for the player
     * @param hostGame  The game
     */
    public void setExistingGame(HostGame hostGame) {
        this.hostGame = hostGame;
    }
}
