package gui;

import Game.Player;
import Game.Team;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class LobbyViewController implements Initializable {

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonReady;
    @FXML
    private TextField team1Name;
    @FXML
    private TextField team2Name;
    @FXML
    private TextField playersTeam1Name;
    @FXML
    private TextField playersTeam2Names;
    @FXML
    private Button buttonHaalTeamsOp;

    private LobbyView view;
    private Runnable runnable;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * Start a timer for initiateLobby
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackOnClick(event);
            }
        });
        buttonReady.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonReadyOnClick(event);
            }
        });

        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                initiateLobby();
            }
        };
        timerRefresh.schedule(timerTask, 0, 30);
    }

    /**
     * Fills TextFields playersTeam1Name and playersTeam2Name
     * With a the list of all players in the team
     */
    private void initiateLobby() {
        Platform.runLater(new Runnable() {
            public void run() {

                for (Player currentPlayer : view.stageController.clientGame.getPlayers()) {
                    if (currentPlayer.getUsername() == StageController.playerName) {
                        team1Name.setText(currentPlayer.getTeam().getName());
                        for (Player playersInTeam1 : currentPlayer.getTeam().getPlayers()) {
                            playersTeam1Name.setText(playersInTeam1.getUsername() + "\n");
                        }
                    } else {
                        team2Name.setText(currentPlayer.getTeam().getName());
                        {
                            for (Player PlayersInTeam2 : currentPlayer.getTeam().getPlayers()) {
                                playersTeam2Names.setText(PlayersInTeam2.getUsername() + "\n");
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Sets the lobbyView
     * @param lobbyView
     */
    public void setView(LobbyView lobbyView) {
        view = lobbyView;
    }

    /**
     * When button ready is pressed startGame and change to GameView
     * @param mouseEvent
     */
    private void buttonReadyOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                view.stageController.clientGame.startRound();
                Platform.runLater(new Runnable() {
                    public void run() {
                        GameView gameView = new GameView((view.stageController));
                        view.pass(gameView);
                    }
                });
            }
        };
        runnable.run();
    }

    /**
     * When button back pressed change to StartView
     * @param mouseEvent
     */
    private void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                Platform.runLater(new Runnable() {
                    public void run() {
                        StartView startView = new StartView((view.stageController));
                        view.pass(startView);
                    }
                });
            }
        };
        runnable.run();
    }
}