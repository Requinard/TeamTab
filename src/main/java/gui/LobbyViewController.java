package gui;

import Game.GameStateEnum;
import Game.Player;
import Game.Team;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
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
    private Label ipLabel;
    @FXML
    private Button buttonSwapTeam;
    private LobbyView view;
    private Runnable runnable;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * <p/>
     * Start a timer for initiateLobby
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(this::buttonBackOnClick);
        buttonReady.setOnMouseClicked(this::buttonReadyOnClick);
        buttonSwapTeam.setOnMouseClicked(this::buttonSwapTeamClick);

        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                initiateLobby();
            }
        };
        timerRefresh.schedule(timerTask, 0, 2000);
    }

    /**
     * Fills TextFields playersTeam1Name and playersTeam2Name
     * With a the list of all players in the team
     */
    private void initiateLobby() {
        while (view == null)
            Thread.yield();

        while (view.stageController.clientGame.getPlayers().isEmpty() || view.stageController.clientGame.localGame.getTeam() == null)
            Thread.yield();

        while (view.stageController.clientGame.getPlayers().get(0).getTeam() == null)
            Thread.yield();

        Platform.runLater(() -> {

            playersTeam1Name.clear();
            playersTeam2Names.clear();

            for (Player currentPlayer : view.stageController.clientGame.getPlayers()) {

                if (view.stageController.clientGame.getTeams().size() > 1) {
                    while(currentPlayer.getTeam() == null)
                        Thread.yield();
                    team1Name.setText(view.stageController.clientGame.getTeams().get(0).getName());
                    team2Name.setText(view.stageController.clientGame.getTeams().get(1).getName());
                    if (currentPlayer.getTeam().getName().equals(team1Name.getText())) {
                        playersTeam1Name.setText(playersTeam1Name.getText() + "\n" + currentPlayer.getUsername());
                    } else if (currentPlayer.getTeam().getName().equals(team2Name.getText())) {

                        playersTeam2Names.setText(playersTeam2Names.getText() + "\n" + currentPlayer.getUsername());
                    }
                }


            }
            if (view.stageController.clientGame.getPlayers().size() > 0) {
                boolean allActive = true;
                for (Player player : view.stageController.clientGame.getPlayers()) {
                    if (!player.getPlayerStatus()) {
                        allActive = false;
                    }
                }
                if (allActive) {
                    StartGame();
                    timerRefresh.cancel();
                    timerRefresh.purge();
                }
            }
            log.log(Level.FINE, "Lobby is being initialized");

        });
    }

    public void StartGame() {
        Platform.runLater(() -> {

            view.stageController.clientGame.setGameState(GameStateEnum.GameView);
            GameView gameView = new GameView((view.stageController));
            view.pass(gameView);
            log.log(Level.INFO, "Going from LobbyView to GameView succeeded");
        });
    }

    /**
     * Sets the lobbyView
     *
     * @param lobbyView The view of the lobby
     */
    public void setView(LobbyView lobbyView) {
        view = lobbyView;

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
            String ipAddress = localhost.getHostAddress();
            view.stageController.clientGame.setLocalIP(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    /**
     * When button ready is pressed startGame and change to GameView
     *
     * @param mouseEvent Passes the view to the gameView
     */
    private void buttonReadyOnClick(MouseEvent mouseEvent) {

        view.stageController.clientGame.changePlayerStatus(true);

        for (Player player : view.stageController.clientGame.getPlayers()) {
            if (!view.stageController.clientGame.localGame.player.getPlayerStatus()) {
                return;
            }
        }
    }

    /**
     * When button back pressed change to StartView
     *
     * @param mouseEvent Returns the player from lobbyview to startview
     */
    private void buttonBackOnClick(MouseEvent mouseEvent) {
        view.stageController.hostGame.resetTeamName();
        runnable = () -> Platform.runLater(() -> {
            StartView startView = new StartView((view.stageController));
            view.pass(startView);
            log.log(Level.INFO, "Going from LobbyView to StartView succeeded");
        });
        runnable.run();
    }


    /**
     * When this button is clicked the player will be moved to a different team
     * This methods works only with two teams
     * Author Kamil Wasylkiewicz
     *
     * @param mouseEvent
     */
    private void buttonSwapTeamClick(MouseEvent mouseEvent) {
        log.log(Level.INFO, "Lobbyview: Player will be swapped to another team");
        Player currentPlayer = view.stageController.clientGame.getPlayer(view.stageController.clientGame.localGame.getPlayer().getUsername());
        Team playersTeam = null;
        Team oppositeTeam = null;

        // get the playersTeam
        for (Team team : view.stageController.hostGame.getTeams()) {
            for (Player player : team.getPlayers()) {
                if (player.getIp().equals(currentPlayer.getIp())) {
                    playersTeam = team;
                    break;
                }
            }
        }

        // get the opposite team
        for (Team team : view.stageController.hostGame.getTeams()) {
            if (!team.equals(playersTeam)) {
                oppositeTeam = team;
                if (oppositeTeam != null) {
                    view.stageController.clientGame.assignTeam(currentPlayer, oppositeTeam);
                    playersTeam1Name.clear();
                    playersTeam2Names.clear();
                    break;
                }
            }
        }
    }
}