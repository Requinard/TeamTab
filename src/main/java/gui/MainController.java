package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by david on 6-10-15.
 */

public class MainController implements Initializable {
    @FXML
    private Button buttonJoin;
    @FXML
    private Button buttonStart;

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

        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartOnClick(event);
            }
        });
    }

    public void setView(MainView mainView)
    {
        view = mainView;
    }

    public void buttonJoinOnClick(MouseEvent mouseEvent)
    {
        JoinView joinView = new JoinView(view.stageController);
        view.pass(joinView);
    }

    public void buttonStartOnClick(MouseEvent mouseEvent)
    {
        StartView startView = new StartView(view.stageController);
        view.pass(startView);
    }





}
