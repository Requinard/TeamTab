package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class StartViewController implements Initializable {
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonBack;
    @FXML
    TextField TeamName;
    @FXML
    TextField TeamSize;

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
                //view.stageController.game.startGame();
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

    public void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                //view.stageController.game.reset();
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
