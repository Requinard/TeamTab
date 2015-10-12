package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by david on 6-10-15.
 */

public class MainController implements Initializable {
    @FXML
    private Button buttonJoin;

    private MainView view;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonJoin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonJoinOnClick(event);
            }
        });
    }

    public void setView(MainView mainView)
    {
        view = mainView;
    }

    public void buttonJoinOnClick(MouseEvent mouseEvent)
    {
        LobbyView lobbyView = new LobbyView(view.stageController);
        view.pass(lobbyView);
    }

}
