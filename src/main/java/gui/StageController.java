package gui;

import Game.HostGame;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.TimerTask;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    Stage stage;
    IView currentView;
    Thread gameThread;
    HostGame game;
    HostGame hostGame;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    private AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");

    public StageController(Stage primaryStage) throws UnsupportedEncodingException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        /*
        game = new Game(this);
           Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: newGame moet clientgame worden
        */

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
	 *
     * @param hostGame
     */
    public void setExistingGame(HostGame hostGame) {

	}



}
