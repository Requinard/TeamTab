package gui;

import Game.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javassist.bytecode.stackmap.TypeData;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class LobbyViewController implements Initializable {

    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
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
        Platform.runLater(() -> {
            log.log(Level.FINE, "Lobby is being initialized");
            for (Player currentPlayer : view.stageController.clientGame.getPlayers()) {
                if (currentPlayer.getUsername().equals(StageController.currentPlayer.getUsername())) {
                    team1Name.setText(currentPlayer.getTeam().getName());
                    log.log(Level.FINER, "Team {0} is set in the lobby", currentPlayer.getTeam().getName());
                    for (Player playerInTeam1 : currentPlayer.getTeam().getPlayers()) {
                        playersTeam1Name.setText(playerInTeam1.getUsername() + "\n");
                        log.log(Level.FINER, "Player {0} in team {1} is added to the lobby", new Object[]{playerInTeam1.getUsername(), playerInTeam1.getTeam().getName()});
                    }
                } else {

                    team2Name.setText(currentPlayer.getTeam().getName());
                    log.log(Level.FINE, "Team {0} is set in the lobby", currentPlayer.getTeam().getName());
                    {
                        for (Player playerInTeam2 : currentPlayer.getTeam().getPlayers()) {
                            playersTeam2Names.setText(playerInTeam2.getUsername() + "\n");
                            log.log(Level.FINER, "Player {0} in team {1} is added to the lobby", new Object[]{playerInTeam2.getUsername(), playerInTeam2.getTeam().getName()});

                        }
                    }
                }
            }
            ipLabel.setText(StageController.chatAppDefusalSquad.getIpAddress());
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
                    StageController.chatAppDefusalSquad.closeChatApp();
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
            StageController.chatAppDefusalSquad.startChatApp();
        });
        runnable.run();
    }
}