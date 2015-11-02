package gui;

import Game.Player;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class StartViewController implements Initializable {
    private final String pattern = "[\n\r \t]+";
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField teamNameTextField;
    private StartView view;
    private Runnable runnable;

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
    }

    public void setView(StartView startView) {
        view = startView;
    }

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

                    //DIT WERKT ALLEEN VOOR DE EERSTE ITERATIE
                    view.stageController.game.createTeam(teamName);
                    Player newPlayer = view.stageController.game.createAndGetThisPlayer(StageController.playerName, teamName);


                    //Team teamByTeamName = view.stageController.game.getTeamByName(teamName);
                    view.stageController.game.addPlayerToTeam(newPlayer);

                    Platform.runLater(new Runnable() {
                        public void run() {
                            LobbyView lobbyView = new LobbyView((view.stageController));
                            view.pass(lobbyView);
                        }
                    });
                }
            }
        };
        runnable.run();
    }

    public void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                view.stageController.game.reset();
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
