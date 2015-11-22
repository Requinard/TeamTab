package gui;

import Game.ClientGame;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javassist.bytecode.stackmap.TypeData;
import javazoom.jl.decoder.JavaLayerException;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import Game.*;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    static String playerName;
    static Player currentPlayer;
    Stage stage;
    IView currentView;
    Thread gameThread;
    ClientGame game;
	ClientGame clientGame;
    private AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");

    public StageController(Stage primaryStage) throws UnsupportedEncodingException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        clientGame = new ClientGame();
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
        log.log(Level.INFO, "reset game for teams : {0}", clientGame.getTeams());
        this.clientGame = new ClientGame();
    }


	/**
     * Author Qun
	 * This sets the game for the player
	 * @param clientGame
	 */
	public void setExistingGame(ClientGame clientGame) {
        this.clientGame = clientGame;
	}



}
