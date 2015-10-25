package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;
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
    @FXML
    private TextField userName;



    private MainView view;
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

    public void setView(MainView mainView) {
        view = mainView;
    }

    public void buttonJoinOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                if (userName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Kies een username");
                } else {
                    view.stageController.playerName = userName.getText();
                    Platform.runLater(new Runnable() {
                        public void run() {
                            System.out.println("MainView - Username is set to: " + view.stageController.playerName);
                            JoinView joinView = new JoinView(view.stageController);
                            view.pass(joinView);
                        }
                    });
                }
            }
        };
        runnable.run();
    }

    public void buttonStartOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                if (userName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kies een username");
                } else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            view.stageController.playerName = userName.getText();
                            System.out.println("MainView - Username is set to: " + view.stageController.playerName);
                            StartView startView = new StartView(view.stageController);
                            view.pass(startView);
                        }
                    });
                }
            }
        };
        runnable.run();
    }
}
