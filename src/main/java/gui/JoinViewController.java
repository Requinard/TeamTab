package gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import networking.finder.GameFinder;

import javax.swing.*;
import java.net.URL;
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
    @FXML
    private TextField tfCustomIP;
    @FXML
    private Button btnJoinCustom;

    private JoinView view;
    private Runnable runnable;
    GameFinder gameFinder;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        gameFinder = new GameFinder();
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
        btnJoinCustom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            btnJoinCustom(event);
            }
        });
    }
    private void btnJoinCustom(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                searchOneGame();
            }
        };
        runnable.run();
    }
    /**
     * When button search is pressed look for available lobby's
     * @param mouseEvent
     */
    private void buttonSearchOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
            searchAllGames();
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
    private void searchAllGames()
    {
        ObservableList openServers = FXCollections.observableArrayList();
        gameFinder.findGames(openServers);
        listGames.setItems(openServers);
    }
    private void searchOneGame()
    {
        if(gameFinder.findSingleGame(tfCustomIP.getText()))
        {
            JOptionPane.showMessageDialog(null,"Game Found");
            //naar de lobby van die game connecten
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Game Not Found");
        }
    }
}
