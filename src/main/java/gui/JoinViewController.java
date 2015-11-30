package gui;

import com.sun.deploy.net.protocol.ProtocolType;
import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import networking.finder.GameFinder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class JoinViewController implements Initializable {
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonJoin;
    @FXML
    private Button buttonSearch;
    @FXML
    private TextField availableGames;
    @FXML
    private ListView listGames;

    private JoinView view;
    private Runnable runnable;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackOnClick(event);
            }
        });

        buttonJoin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonJoinOnClick(event);
            }
        });

        buttonSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonSearchOnClick(event);
            }
        });

    }

    /**
     * When button search is pressed look for available lobby's
     * @param mouseEvent
     */
    private void buttonSearchOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
            searchGames();
            }
        };
        runnable.run();
    }

    /**
     * Sets the joinView
     * @param joinView
     */
    public void setView(JoinView joinView) {
        view = joinView;
    }

    /**
     * When button back is pressed change to MainView
     * @param mouseEvent
     */
    private void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                Platform.runLater(new Runnable() {
                    public void run() {
                        MainView mainView = new MainView(view.stageController);
                        view.pass(mainView);
                    }
                });
            }
        };
        runnable.run();
    }

    /**
     * When button Join is pressed join selected lobby
     * @param mouseEvent
     */
    private void buttonJoinOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                Platform.runLater(new Runnable() {
                    public void run() {
                        LobbyView lobbyView = new LobbyView(view.stageController);
                        view.pass(lobbyView);
                    }
                });
            }
        };
        runnable.run();
    }
    private void searchGames()
    {
        GameFinder gameFinder = new GameFinder();
        ObservableList openServers = FXCollections.observableArrayList();
        gameFinder.findGames(openServers);
        listGames.setItems(openServers);
    }
}
