package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javassist.bytecode.stackmap.TypeData;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by david on 6-10-15.
 */

public class MainController implements Initializable {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    private final String pattern = "[ \n\r\t]+";
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
     * @param resources The resources used to localize the root object, or <tt>null</tt>
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

    /**
     * Sets the mainView
     * @param mainView
     */
    public void setView(MainView mainView) {
        view = mainView;
    }

    /**
     * When button join is pressed, Check if username is filled in and change to JoinView
     * @param mouseEvent
     */
    private void buttonJoinOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {
                if (userName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kies een username");
                } else if (userName.getText().matches(pattern)) {
                    JOptionPane.showMessageDialog(null, "Je username bevat karakters die niet zijn toegestaan!");
                } else {
                    StageController.playerName = userName.getText();
                    Platform.runLater(new Runnable() {
                        public void run() {
                            log.log(Level.INFO, "Username has been set to : {0}", StageController.playerName);

                            JoinView joinView = new JoinView(view.stageController);
                            view.pass(joinView);
                        }
                    });
                }
            }
        };
        runnable.run();
    }

    /**
     * When button start is pressed, Check if username is filled in and change to StartView
     * @param mouseEvent
     */
    private void buttonStartOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                if (userName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kies een username");
                } else if (userName.getText().matches(pattern)) {
                    JOptionPane.showMessageDialog(null, "Je username bevat ongewenste karakters!");
                } else {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            StageController.playerName = userName.getText();
                            log.log(Level.INFO, "Username is set to: " + StageController.playerName );
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
