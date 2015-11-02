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

    private void fillScoreBoard(){
        System.out.println("OK");
        System.out.println(StageController.playerName);
        //System.out.println(view.stageController.game.getTeamOfPlayer().getName());
        final ArrayList<String> scoreBoard = view.stageController.game.endGame(view.stageController.game.getTeamOfPlayer());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (String score : scoreBoard) {
                    scoreArea.appendText(score + "\n");
                }
            }
        });
    }

    public void setView(ScoreView scoreView) {
        view = scoreView;
    }

    private void buttonBackLobbyOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        LobbyView lobbyView = new LobbyView((view.stageController));
                        view.pass(lobbyView);
                    }
                });
            }
        };
        runnable.run();
    }
}
