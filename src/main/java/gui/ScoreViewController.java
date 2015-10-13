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
public class ScoreViewController implements Initializable{
    @FXML
    private Button buttonBackLobby;

    private ScoreView view;

    public void initialize(URL location, ResourceBundle resources) {

        buttonBackLobby.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackLobbyOnClick(event);
            }
        });
    }

    public void setView(ScoreView scoreView)
    {
        view = scoreView;
    }

    public void buttonBackLobbyOnClick(MouseEvent mouseEvent)
    {
        LobbyView lobbyView = new LobbyView((view.stageController));
        view.pass(lobbyView);
    }
}
