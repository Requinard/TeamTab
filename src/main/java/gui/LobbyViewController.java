package gui;

import Game.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class LobbyViewController implements Initializable {

    private static final Logger log = Logger.getLogger(LobbyViewController.class.getName());
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
    @FXML
    private Button buttonChat;
    @FXML
    private Label ipLabel;
    private LobbyView view;
    private Runnable runnable;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    private final double TICKRATE = 0.1;
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * <p>
     * Start a timer for initiateLobby
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(this::buttonBackOnClick);
        buttonReady.setOnMouseClicked(this::buttonReadyOnClick);
        buttonChat.setOnMouseClicked(this::buttonChatOnClick);

        timerRefresh = new java.util.Timer();
        try {
            Thread.sleep(1);
        } catch (Exception e) {

        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                initiateLobby();
            }
        };
        timerRefresh.schedule(timerTask, 0, (long) ((1/TICKRATE)*1000));
    }

    /**
     * Fills TextFields playersTeam1Name and playersTeam2Name
     * With a the list of all players in the team
     */
    private void initiateLobby() {

        Platform.runLater(() -> {

            log.log(Level.FINE, "Lobby is being initialized");

            for (Player currentPlayer : view.stageController.clientGame.getPlayers()) {

                if (view.stageController.clientGame.getTeams().size() > 1) {
                    team1Name.setText(view.stageController.clientGame.getTeams().get(0).getName());
                    team2Name.setText(view.stageController.clientGame.getTeams().get(1).getName());
                    if (!currentPlayer.getPlayerStatus()) {
                        //view.stageController.clientGame
                    }
                    if (currentPlayer.getTeam().getName().equals(team1Name.getText())) {

                        playersTeam1Name.setText(currentPlayer.getUsername() + "\n");
                    }
                    if (currentPlayer.getTeam().getName().equals(team2Name.getText())) {
                        playersTeam2Names.setText(currentPlayer.getUsername() + "\n");
                    }
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //ipLabel.setText(StageController.chatAppDefusalSquad.getIpAddress());
        });
    }

    /**
     * Sets the lobbyView
     *
     * @param lobbyView The view of the lobby
     */
    public void setView(LobbyView lobbyView) {
        view = lobbyView;
    }

    /**
     * When button ready is pressed startGame and change to GameView
     *
     * @param mouseEvent Passes the view to the gameView
     */
    private void buttonReadyOnClick(MouseEvent mouseEvent) {
        runnable = () -> {
            view.stageController.clientGame.reset(true);
            view.stageController.clientGame.startRound();
            Platform.runLater(new Runnable() {
                public void run() {
                    //StageController.chatAppDefusalSquad.closeChatApp();
                    GameView gameView = new GameView((view.stageController));
                    view.pass(gameView);
                    log.log(Level.INFO, "Going from LobbyView to GameView succeeded");
                }
            });
        };
        runnable.run();
    }

    /**
     * When button back pressed change to StartView
     *
     * @param mouseEvent Returns the player from lobbyview to startview
     */
    private void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = () -> Platform.runLater(() -> {
            StartView startView = new StartView((view.stageController));
            view.pass(startView);
            log.log(Level.INFO, "Going from LobbyView to StartView succeeded");
        });
        runnable.run();
    }

    /**
     * when the buttons is clicked it will start the chatApp GUI
     * Author Kamil Wasylkiewicz
     *
     * @param mouseEvent
     */
    private void buttonChatOnClick(MouseEvent mouseEvent) {
        log.log(Level.INFO, "Lobbyview: Chat app GUI is starting");
        runnable = () -> Platform.runLater(() -> {
            //StageController.chatAppDefusalSquad.startChatApp();
        });
        runnable.run();
    }
}