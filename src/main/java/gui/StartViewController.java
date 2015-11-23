package gui;

import Game.Player;
import Game.Team;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javassist.bytecode.stackmap.TypeData;

import javax.swing.*;
import java.net.URL;
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

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
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
    }

    /**
     * Sets the startView
     * @param startView     The startView for the player, the view where he can fill in username
     */
    public void setView(StartView startView) {
        view = startView;
    }

    /**
     * When button start is pressed checks if teanname is filled in and change to LobbyView
     * @param mouseEvent    Sends the player to the lobbyView
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
                Team currentTeam = view.stageController.hostGame.createTeam(teamName);
                log.log(Level.INFO, "Team {0} is created", currentTeam.getName());

                //Player is created
                StageController.currentPlayer = view.stageController.hostGame.createPlayer(StageController.playerName, teamName);
                log.log(Level.INFO, "Player {0} is created", StageController.currentPlayer.getUsername());

                //Player gets assigned to the team
                view.stageController.hostGame.assignTeam(StageController.currentPlayer, currentTeam);
                log.log(Level.INFO, "Player {0} is assigned to {1}", new Object[]{StageController.currentPlayer.getUsername(), StageController.currentPlayer.getTeam()});

                //Testdata for second team is added
                testData();

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
     * @param mouseEvent    Brings back player from startview to mainview
     */
    public void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = () -> {
            // view.stageController.game.reset();
            Platform.runLater(() -> {
                MainView mainView = new MainView((view.stageController));
                view.pass(mainView);
                log.log(Level.INFO, "Going from TeamView to StartView succeeded");
            });
        };
        runnable.run();
    }

    public void testData() {
        log.log(Level.INFO, "Second team is being initialized");
        //Team is created
        Team currentTeam = view.stageController.hostGame.createTeam("Private");
        log.log(Level.INFO, "Team {0} is created", currentTeam.getName());

        //Player is created
        Player player = view.stageController.hostGame.createPlayer("Ryan", "Private");
        log.log(Level.INFO, "Player {0} is created", player.getUsername());
        //Player gets assigned to the team
        view.stageController.hostGame.assignTeam(player, currentTeam);
        log.log(Level.INFO, "Player {0} is assigned to {1}", new Object[]{StageController.currentPlayer.getUsername(), StageController.currentPlayer.getTeam()});
    }
}
