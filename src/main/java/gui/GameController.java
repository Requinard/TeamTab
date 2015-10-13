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
public class GameController implements Initializable{
    @FXML
    private Button buttonStart;

    private GameView view;

    public void initialize(URL location, ResourceBundle resources) {

        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartOnClick(event);
            }
        });
    }

    public void setView(GameView gameView)
    {
        view = gameView;
    }

    public void buttonStartOnClick(MouseEvent mouseEvent)
    {
        ScoreView scoreView = new ScoreView((view.stageController));
        view.pass(scoreView);
    }

}
