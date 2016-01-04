package gui;

import Game.Player;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ListView;
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
    private javafx.scene.control.ListView playersTeam1Names;
    @FXML
    private javafx.scene.control.ListView playersTeam2Names;
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
    private ObservableList playersTeam1;
    private ObservableList playersTeam2;
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
        buttonChat.setOnMouseClicked(this::buttonChatOnClick);

         playersTeam1 = FXCollections.observableArrayList();
         playersTeam2 = FXCollections.observableArrayList();

        playersTeam1Names.setItems(playersTeam1);
        playersTeam2Names.setItems(playersTeam2);

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
        timerRefresh.schedule(timerTask, 0, 30);
    }

    /**
     * Fills TextFields playersTeam1Name and playersTeam2Name
     * With a the list of all players in the team
     */
    private void initiateLobby() {

        Platform.runLater(() -> {

            log.log(Level.FINE, "Lobby is being initialized");

            if(view.stageController.clientGame.getTeams().size() > 1) {
                if(team1Name.getText().isEmpty()) {
                    team1Name.setText(view.stageController.clientGame.getTeams().get(0).getName());
                    log.log(Level.INFO, "Team1name has been set");
                    team2Name.setText(view.stageController.clientGame.getTeams().get(1).getName());
                    log.log(Level.INFO, "Team2Name has been set");
                }
                playersTeam1.clear();
                playersTeam2.clear();

                if(view.stageController.clientGame.getPlayers().size() > 0) {
                    for (Player player : view.stageController.clientGame.getPlayers()) {
                        if (player.getTeam().getName() == team1Name.getText())
                            playersTeam1.add(player.getUsername());
                        else
                            playersTeam2.add(player.getUsername());
                    }
                }

            }


            boolean allActive = false;
            if(view.stageController.clientGame.getPlayers().size() > 0)
                 allActive = true;

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
        });
    }

    public void StartGame() {
        Platform.runLater(() -> {
            //StageController.chatAppDefusalSquad.closeChatApp();
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
            if (!view.stageController.clientGame.localPlayer.getPlayerStatus()) {
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