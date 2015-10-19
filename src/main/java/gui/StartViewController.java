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
public class StartViewController implements Initializable{
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonBack;

    private StartView view;

    public void initialize(URL location, ResourceBundle resources) {

        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartOnClick(event);
            }
        });

        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonBackOnClick(event);
            }
        });
    }

    public void setView(StartView startView)
    {
        view = startView;
    }

    public void buttonStartOnClick(MouseEvent mouseEvent)
    {
        GameView gameView = new GameView((view.stageController));
        view.pass(gameView);
    }

    public void buttonBackOnClick(MouseEvent mouseEvent)
    {
        MainView mainView = new MainView((view.stageController));
        view.pass(mainView);
    }


}
