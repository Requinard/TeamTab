package gui;

import Game.Panel;
import Game.Player;
import Game.Team;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    @FXML private TextField textFieldInstruction;
    @FXML private ImageView Team1Leven1;
    @FXML private ImageView Team1Leven2;
    @FXML private ImageView Team1Leven3;
    @FXML private ImageView Team2Leven1;
    @FXML private ImageView Team2Leven2;
    @FXML private ImageView Team2Leven3;

    private GameView view;
    private Runnable runnable;
    PanelFactory panelFactory;

    public void initialize(URL location, ResourceBundle resources) {
        panelFactory = new PanelFactory();
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
        fillGridWithPanels();
        showTeamLevens();
    }
    public void fillGridWithPanels() {
        ArrayList<Panel> panels = view.stageController.game.getPlayerByName(view.stageController.playerName).getPanels();
        int x = 0;
        int y = 0;
        for (Panel panel : panels) {
            IPanel iPanel = panelFactory.getPanel(panel);
            gridPane.add((Node) iPanel, x, y);
            y++;
            if (y == 5) {
                x++;
                y = 0;
            }
        }
    }
    //Als de player een instructie krijgt kan deze worden aangeroepen zodat die getoont wordt. Op dit moment is de playerinstructie leeg.
    public void showPlayerInstruction()
    {
        textFieldInstruction.setText(view.stageController.game.getPlayerByName(view.stageController.playerName).getInstructions().toString());
    }
    public void showTeamLevens()
    {
        List<Team> teams =  view.stageController.game.allTeams();
        Team team1 = teams.get(0);
        Team team2 = teams.get(1);
        int levensTeam1 = team1.getLives();
        int levensTeam2 = team2.getLives();
        switch (levensTeam1){
            case 1: Team1Leven1.setVisible(false);
            case 2: {Team1Leven2.setVisible(false); Team1Leven1.setVisible(false);}
            case 3: {Team1Leven3.setVisible(false);Team1Leven2.setVisible(false);Team1Leven1.setVisible(false);}
        }
        switch (levensTeam2){
        case 1: Team2Leven1.setVisible(false);
        case 2: {Team2Leven2.setVisible(false);Team2Leven1.setVisible(false);}
        case 3: {Team2Leven3.setVisible(false);Team2Leven2.setVisible(false);Team2Leven1.setVisible(false);}
    }
    }
    //get gamestatus update
    /*
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
                    int counter = view.stageController.game.getPlayerByName(view.stageController.playerName).getTeam().getTime();
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
                        if(counter == 0)
                        {
                            //dit moet worden verandert door een subtract time methode
                            view.stageController.game.getPlayerByName(view.stageController.playerName).getTeam().setTime(5);
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
        };
        runnable.run();
    }


}
