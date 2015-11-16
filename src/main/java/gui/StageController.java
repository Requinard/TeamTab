package gui;

import Game.ClientGame;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import Game.*;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    static String playerName;
    Stage stage;
    IView currentView;
    Thread gameThread;
    ClientGame game;
	ClientGame clientGame;

    public StageController(Stage primaryStage) throws UnsupportedEncodingException {

        stage = primaryStage;
        currentView = new MainView(this);
        currentView.load();
        /*
        game = new Game(this);
           Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: newGame moet clientgame worden
        */

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
	 * @param clientGame
	 */
	public void setExistingGame(ClientGame clientGame) {

	}



}
