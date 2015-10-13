package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class LobbyViewController implements Initializable {

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonReady;

    private LobbyView view;

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

    }

    public void setView(LobbyView lobbyView)
    {
        view = lobbyView;
    }

    public void buttonReadyOnClick(MouseEvent mouseEvent)
    {
        GameView gameView = new GameView((view.stageController));
        view.pass(gameView);
    }

    public void buttonBackOnClick(MouseEvent mouseEvent)
    {
        JoinView joinView = new JoinView((view.stageController));
        view.pass(joinView);
    }


}
