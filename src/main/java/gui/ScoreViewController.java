package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class ScoreViewController implements Initializable {
    @FXML
    private Button buttonBackLobby;
    @FXML
    private TextField scoreField;
    @FXML
    private TextArea scoreArea;

    private ScoreView view;
    private Runnable runnable;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * Starts a timer for fillScoreBoard
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonBackLobby.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackLobbyOnClick(event);
            }
        });

        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                fillScoreBoard();
            }
        };
        timerRefresh.schedule(timerTask, 500);
    }

    /**
     * Shows if the team has won or lost
     * Prints the score of the players in TextArea
     */
    private void fillScoreBoard(){
        System.out.println("OK");
        System.out.println(StageController.playerName);
        /*
        final ArrayList<String> scoreBoard = view.stageController.game.endGame(view.stageController.game.getTeamOfPlayer());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (String score : scoreBoard) {
                    scoreArea.appendText(score + "\n");
                }
            }
        });
         Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: de scoreboard moet geset worden door .endGame() en .getTeamOfPlayer() te vervangen, de rest van de code is wel valide
        */
    }

    /**
     * Sets the scoreView
     * @param scoreView
     */
    public void setView(ScoreView scoreView) {
        view = scoreView;
    }

    /**
     * When button back to lobby is pressed, game hardReset and change to LobbyView
     * @param mouseEvent
     */
    private void buttonBackLobbyOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                /*
                view.stageController.game.hardReset();
                Platform.runLater(new Runnable() {
                    public void run() {
                        LobbyView lobbyView = new LobbyView((view.stageController));
                        view.pass(lobbyView);
                    }
                });
                 Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: .hardReset() moet vervangen worden
                */
            }
        };
        runnable.run();
    }
}
