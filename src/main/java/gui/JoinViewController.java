package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextArea;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
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

    public void buttonSearchOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
//                ArrayList<String> games = view.stageController.game.joinGameViewTeams();
//                for(String s : games){
//                    //listGames.set(s + "\n");
//                }
            }
        };
        runnable.run();
    }

    public void setView(JoinView joinView) {
        view = joinView;
    }

    public void buttonBackOnClick(MouseEvent mouseEvent) {
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

    public void buttonJoinOnClick(MouseEvent mouseEvent) {
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
}
