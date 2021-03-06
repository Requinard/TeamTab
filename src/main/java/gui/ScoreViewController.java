package gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javassist.bytecode.stackmap.TypeData;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class ScoreViewController implements Initializable {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    @FXML
    private Button buttonBackLobby;
    @FXML
    private TextField scoreField;
    @FXML
    private TextArea scoreArea;

    private ScoreView view;
    private Runnable runnable;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * <p/>
     * Starts a timer for fillScoreBoard
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt>
     */
    public void initialize(URL location, ResourceBundle resources) {
        buttonBackLobby.setOnMouseClicked(this::buttonBackLobbyOnClick);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fillScoreBoard();
            }
        });
    }

    /**
     * Shows if the team has won or lost
     * Prints the score of the players in TextArea
     */

    private void fillScoreBoard() {
        while (view.stageController.clientGame.localGame.getScoreBoard().isEmpty())
            Thread.yield();

        final List<String> scoreBoard = view.stageController.clientGame.localGame.getScoreBoard();
        for (String score : scoreBoard) {
            scoreArea.appendText(score + "\n");
        }

    }

    /**
     * Sets the scoreView
     *
     * @param scoreView View where the player can see the score
     */
    public void setView(ScoreView scoreView) {
        view = scoreView;
    }

    /**
     * When button back to lobby is pressed, game hardReset and change to LobbyView
     *
     * @param mouseEvent This mouseEvent brings the player back to the MainView
     */
    private void buttonBackLobbyOnClick(MouseEvent mouseEvent) {
        runnable = () -> {
            view.stageController.hostGame.reset(true);
            view.stageController.clientGame.changePlayerStatus(false);
            Platform.runLater(() -> {
                LobbyView lobbyView = new LobbyView((view.stageController));
                view.pass(lobbyView);
            });

        };

        log.log(Level.INFO, "Returning back to the lobbyview");
        runnable.run();
    }
}
