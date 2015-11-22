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

        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartOnClick(event);
            }
        });

        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackOnClick(event);
            }
        });

        AudioPlayer audioPlayer = new AudioPlayer("src/main/resources/audio/ThemeMusic.mp3");
        audioPlayer.play();
    }

    /**
     * Sets the startView
     * @param startView
     */
    public void setView(StartView startView) {
        view = startView;
    }

    /**
     * When button start is pressed checks if teanname is filled in and change to LobbyView
     * @param mouseEvent
     */
    public void buttonStartOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                if (teamNameTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kies een teamname");
                } else if (teamNameTextField.getText().matches(pattern)) {
                    JOptionPane.showMessageDialog(null, "Je string bevat karakters die niet toegestaan zijn!");
                } else {
                    String teamName = teamNameTextField.getText();
                    System.out.println("StartView - Teamname is set to: " + teamName);

                    //Team is created
                    Team currentTeam = view.stageController.clientGame.createTeam(teamName);
                    log.log(Level.INFO, "Team {0} is created", currentTeam.getName());

                    //Player is created
                    view.stageController.currentPlayer = view.stageController.clientGame.createPlayer(StageController.playerName, teamName);
                    log.log(Level.INFO, "Player {0} is created", view.stageController.currentPlayer.getUsername());

                    //Player gets assigned to the team
                    view.stageController.clientGame.assignTeam(view.stageController.currentPlayer,currentTeam);
                    log.log(Level.INFO, "Player {0} is assigned to {1}",new Object[] {view.stageController.currentPlayer.getUsername(),view.stageController.currentPlayer.getTeam()});
                    Platform.runLater(new Runnable() {
                        public void run() {
                            LobbyView lobbyView = new LobbyView((view.stageController));
                            view.pass(lobbyView);
                            log.log(Level.INFO, "Going from TeamView to LobbyView succeeded");
                        }
                    });
                }
            }
        };
        runnable.run();
    }

    /**
     * When button back is pressed change to MainView
     * @param mouseEvent
     */
    public void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
               // view.stageController.game.reset();
                Platform.runLater(new Runnable() {
                    public void run() {
                        MainView mainView = new MainView((view.stageController));
                        view.pass(mainView);

                    }
                });
            }
        };
        runnable.run();
    }
}
