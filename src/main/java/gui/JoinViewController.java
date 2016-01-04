package gui;

import Game.ClientGame;
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
import javassist.bytecode.stackmap.TypeData;
import networking.finder.GameFinder;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class JoinViewController implements Initializable {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    GameFinder gameFinder;
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
    private ClientGame clientGame;

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
        clientGame = new ClientGame();
    }

    /**
     * When button joinCustom is pressed it will connect to the given ip
     * @param mouseEvent
     */
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
        view.stageController.setClientGame(clientGame);
    }

    /**
     * When button back is pressed change to MainView
     * @param mouseEvent
     */
    private void buttonBackOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                clientGame = null;
                view.stageController.setClientGame(null);
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
                String hostIp = listGames.getSelectionModel().getSelectedItem().toString();
                view.stageController.clientGame.setHostIp(hostIp);
                log.log(Level.INFO, "Host ip on the client has been set to: {0}", hostIp );
                view.stageController.clientGame.createPlayer(StageController.playerName, "127.0.0.1");

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

    /**
     * When this method is called the system will search for all available games on the subnet and will add these to the listview
     * Author: Kevin
     */
    private void searchAllGames()
    {
        ObservableList openServers = FXCollections.observableArrayList();
        gameFinder.findGames(openServers);
        listGames.setItems(openServers);
        log.log(Level.INFO, "Amount of servers that have been found: {0}", openServers.size());
    }

    /**
     * When this mehod is called it will check if the textfield is empty
     * if this is not the case it will search for an available game on the given IP address and connect to it if it's found
     * Author: Kevin
     */
    private void searchOneGame() {
        if (!tfCustomIP.getText().isEmpty()) {
            String hostIp = tfCustomIP.getText();
            view.stageController.clientGame.setHostIp(hostIp);
            log.log(Level.INFO, "Host ip on the client has been set to: {0}", hostIp);
            view.stageController.clientGame.createPlayer(StageController.playerName, "127.0.0.1");

            Platform.runLater(new Runnable() {
                public void run() {
                    LobbyView lobbyView = new LobbyView(view.stageController);
                    view.pass(lobbyView);
                }
            });

//            if (gameFinder.findSingleGame()) {
//                JOptionPane.showMessageDialog(null, "Game Found");
//                //naar de lobby van die game connecten
//            } else {
//                JOptionPane.showMessageDialog(null, "Game Not Found");
//            }
        }
    }
}
