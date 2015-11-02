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

    private void initiateLobby() {
        Platform.runLater(new Runnable() {
            public void run() {
                final Player currentPlayer = view.stageController.game.getPlayerByName(StageController.playerName);
                for (Team a : view.stageController.game.allTeams()) {
                    if (a.isPlayerInTeam(currentPlayer)) {
                        team1Name.setText(a.getName());
                        for (Player p : a.getPlayers()) {
                            playersTeam1Name.setText(p.getName() + "\n");
                        }
                    } else {
                        team2Name.setText(a.getName());
                        {
                            for (Player b : a.getPlayers()) {
                                playersTeam2Names.setText(b.getName() + "\n");
                            }
                        }
                    }
                }

            }
        });
    }

    public void setView(LobbyView lobbyView) {
        view = lobbyView;
    }

    private void buttonReadyOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                view.stageController.game.startGame();
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