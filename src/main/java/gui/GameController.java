package gui;

import Game.Panel;
import gui.panel.IPanel;
import gui.panel.PanelButtonControl;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class GameController implements Initializable {
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStartTimer;
    @FXML
    private ProgressBar progressBar = new ProgressBar(1);
    @FXML
    private Label timeLabel;
    private Timer timer;
    @FXML private GridPane gridPane;

    private GameView view;
    private Runnable runnable;
    PanelFactory panelFactory;


    public void initialize(URL location, ResourceBundle resources) {
        panelFactory = new PanelFactory();
        fillGridWithPanels();
        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartOnClick(event);
            }
        });
        buttonStartTimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                buttonStartTimerOnClick(event);
            }
        });
    }

    public void setView(GameView gameView) {
        view = gameView;
    }
    public void fillGridWithPanels()
    {
        //Alleen als voorbeeld, wordt dalijk met een forloop door de lijst van panels van een player gegaan
        Panel panel = new Panel(1,0,"testpanel",0,0);
        Panel panel1 = new Panel(2,1,"testpanel",1,5);
        Panel panel2 = new Panel(3,2,"testpanel",1,5);
        IPanel iPanel = panelFactory.getPanel(panel);
        IPanel iPanel1 = panelFactory.getPanel(panel1);
        IPanel iPanel2 = panelFactory.getPanel(panel2);
        gridPane.add((Node) iPanel, 0, 0);
        gridPane.add((Node) iPanel1, 0, 1);
        gridPane.add((Node) iPanel2, 0, 2);
    }
    //get gamestatus update
    /*
        Huidige tijd *optional*
        Hoeveel team levens?
        nieuwe instructie
     */

    public void buttonStartOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            public void run() {

                Platform.runLater(new Runnable() {
                    public void run() {
                        ScoreView scoreView = new ScoreView((view.stageController));
                        view.pass(scoreView);
                    }
                });
            }
        };
        runnable.run();
    }

    public void buttonStartTimerOnClick(MouseEvent mouseEvent) {
        runnable = new Runnable() {
            @Override
            public void run() {
                timer = new Timer(1000, new ActionListener() {
                    int counter = 9;
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        counter--;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(counter * 0.1);
                                timeLabel.setText(Integer.toString(counter));
                            }
                        });
                    }
                });
                timer.start();
            }
        };
        runnable.run();
    }


}
