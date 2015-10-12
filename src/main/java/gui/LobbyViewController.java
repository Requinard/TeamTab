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

    private LobbyView view;

    public void initialize(URL location, ResourceBundle resources) {
        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackOnClick(event);
            }
        });
    }

    public void setView(LobbyView lobbyView)
    {
        view = lobbyView;
    }

    public void buttonBackOnClick(MouseEvent mouseEvent)
    {
        MainView mainView = new MainView((view.stageController));
        view.pass(mainView);
    }
}
