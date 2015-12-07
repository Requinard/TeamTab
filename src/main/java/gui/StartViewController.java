package gui;

import Game.ClientGame;
import Game.HostGame;
import Game.Team;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javassist.bytecode.stackmap.TypeData;

import javax.swing.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class StartViewController implements Initializable {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    private final String pattern = "[\n\r \t]+";
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField teamNameTextField;
    private StartView view;
    private Runnable runnable;
    private HostGame hostGame;
    private ClientGame clientGame;

    private String ipAddress = "";

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * <p>
     * start audio
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {

        buttonStart.setOnMouseClicked(this::buttonStartOnClick);

        buttonBack.setOnMouseClicked(this::buttonBackOnClick);

        AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");
        audioPlayer.play();

        clientGame = new ClientGame();
        hostGame = new HostGame();
    }

    /**
     * Sets the startView
     *
     * @param startView The startView for the player, the view where he can fill in username
     */
    public void setView(StartView startView) {
        view = startView;
        view.stageController.setClientGame(clientGame);
        view.stageController.setHostGame(hostGame);
        view.stageController.resetGame();
    }

    /**
     * When button start is pressed checks if teanname is filled in and change to LobbyView
     *
     * @param mouseEvent Sends the player to the lobbyView
     */
    public void buttonStartOnClick(MouseEvent mouseEvent) {
        runnable = () -> {
            if (teamNameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kies een teamname");
            } else if (teamNameTextField.getText().matches(pattern)) {
                JOptionPane.showMessageDialog(null, "Je string bevat karakters die niet toegestaan zijn!");
            } else {
                String teamName = teamNameTextField.getText();
                System.out.println("StartView - Teamname is set to: " + teamName);

                //Team is created
                Team currentTeam = view.stageController.clientGame.createTeam(teamName);
                Team otherTeam = view.stageController.clientGame.createTeam("Bots");

                // Add current player
                try {
                    view.stageController.clientGame.setHostIp(InetAddress.getLocalHost().getHostAddress());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                StageController.currentPlayer = view.stageController.clientGame.createPlayer(StageController.playerName, "127.0.0.1");
                //view.stageController.clientGame.assignTeam(player, currentTeam);

                log.log(Level.INFO, "Team {0} is created", currentTeam.getName());

                // get real ipaddress of player
                InetAddress localhost = null;
                try {
                    localhost = InetAddress.getLocalHost();
                    ipAddress = localhost.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                //Player is created
                //StageController.currentPlayer = view.stageController.clientGame.createPlayer(StageController.playerName, ipAddress);
                log.log(Level.INFO, "Player {0} is created", StageController.currentPlayer.getUsername());

                //Player gets assigned to the team
                //view.stageController.clientGame.assignTeam(StageController.currentPlayer, currentTeam);
                log.log(Level.INFO, "Player {0} is assigned to {1}", new Object[]{StageController.currentPlayer.getUsername(), StageController.currentPlayer.getTeam()});

                //Testdata for second team is added
                //testData();

                // create and start the RMI registry with hostgame IP
                StageController.chatAppDefusalSquad.setIpAddress(StageController.currentPlayer.getIp());
                StageController.chatAppDefusalSquad.createAndBindRegistry();
                log.log(Level.INFO, "RMI chat loaded");

                Platform.runLater(() -> {
                    LobbyView lobbyView = new LobbyView((view.stageController));
                    view.pass(lobbyView);
                    log.log(Level.INFO, "Going from TeamView to LobbyView succeeded");
                });
            }
        };
        runnable.run();
    }

    /**
     * When button back is pressed change to MainView
     *
     * @param mouseEvent Brings back player from startview to mainview
     */
    public void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = () -> {
            // view.stageController.game.reset();
            hostGame = null;
            clientGame = null;
            view.stageController.setClientGame(null);
            Platform.runLater(() -> {
                MainView mainView = new MainView((view.stageController));
                view.pass(mainView);
                log.log(Level.INFO, "Going from TeamView to StartView succeeded");
            });
        };
        runnable.run();
    }

    /*
    public void testData() {
        log.log(Level.INFO, "Second team is being initialized");
        //Team is created
        Team currentTeam = view.stageController.clientGame.createTeam("Private");
        log.log(Level.INFO, "Team {0} is created", currentTeam.getName());

        //Player is created
        Player player = view.stageController.clientGame.createPlayer("Ryan", "Private");
        log.log(Level.INFO, "Player {0} is created", player.getUsername());
        //Player gets assigned to the team
        view.stageController.clientGame.assignTeam(player, currentTeam);
        log.log(Level.INFO, "Player {0} is assigned to {1}", new Object[]{StageController.currentPlayer.getUsername(), StageController.currentPlayer.getTeam()});
    }
    */
}
