package gui;

import Game.Panel;
import Game.Player;
import Game.Team;
import gui.panel.IPanel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;

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
    @FXML
    private Label secondenLabel;
    @FXML
    private Label instructionLabel;

    private Timer timer;
    @FXML
    private GridPane gridPane = new GridPane();

    @FXML
    private Label labelCorrectInstructions;
    @FXML
    private ImageView Team1Leven1;
    @FXML
    private ImageView Team1Leven2;
    @FXML
    private ImageView Team1Leven3;
    @FXML
    private ImageView Team2Leven1;
    @FXML
    private ImageView Team2Leven2;
    @FXML
    private ImageView Team2Leven3;

    private GameView view;
    private Runnable runnable;
    private PanelFactory panelFactory;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    private ArrayList<Panel> panelHolder;
    private boolean panelPushed;


    public void initialize(URL location, ResourceBundle resources) {
        //view.stageController.game.startGame();
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

        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                refreshView();
            }
        };
        timerRefresh.schedule(timerTask, 0, 30);
    }


    private void refreshView() {
        showTeamLevens();
        showTeamInstructionCount();
        showPlayerInstruction();
        panelChecker();
    }

    private void panelChecker(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!panelHolder.equals(view.stageController.game.getPlayerByName(view.stageController.playerName).getPanels())){
                    fillGridWithPanels();
                }
            }
        });
    }
    public void setView(GameView gameView) {
        view = gameView;
        fillGridWithPanels();
        showTeamLevens();
    }

    public void fillGridWithPanels() {
        gridPane.getChildren().clear();
        gridPane.setMinSize(0, 0);
        gridPane.setAlignment(Pos.CENTER);
        final ArrayList<Panel> panels = view.stageController.game.getPlayerByName(view.stageController.playerName).getPanels();
        panelHolder = panels;
        int column = 0;
        int row = 0;
        for (Panel panel : panels) {
            IPanel iPanel = PanelFactory.getPanel(panel, this);
            System.out.println(panel.getText());
            if (row < 4) {
                gridPane.add((Node) iPanel, row, column);
            } else {
                row = 0;
                column++;
                gridPane.add((Node) iPanel, row, column);
            }
            row++;
        }

    }

    // Als de player een instructie krijgt kan deze worden aangeroepen zodat die getoont wordt. Op dit moment is de playerinstructie leeg.
    private void showPlayerInstruction() {
        Platform.runLater(new Runnable() {
            public void run() {
                if (view.stageController.game.getPlayerByName(StageController.playerName) != null)
                    instructionLabel.setText(view.stageController.game.getPlayerByName(StageController.playerName).getInstruction().toString() + " to: " + view.stageController.game.getPlayerByName(StageController.playerName).getInstruction().getValue());
            }
        });
    }

    private void showTeamInstructionCount() {
        Platform.runLater(new Runnable() {
            public void run() {
                if (view.stageController.game.getPlayerByName(StageController.playerName) != null)
                    labelCorrectInstructions.setText(view.stageController.game.getPlayerByName(StageController.playerName).getTeam().getCorrectInstruction() + "");
            }
        });
    }

    private void showTeamLevens() {
        Platform.runLater(new Runnable() {
            public void run() {
                int levensTeam1;
                int levensTeam2;
                if (view.stageController.game.getPlayerByName(StageController.playerName) != null) {
                    levensTeam1 = view.stageController.game.allTeams().get(0).getLives();
                    levensTeam2 = view.stageController.game.allTeams().get(1).getLives();

                    switch (levensTeam2) {
                        case 1:
                            Team1Leven1.setVisible(false);
                            Team1Leven2.setVisible(true);
                            Team1Leven3.setVisible(true);
                            break;
                        case 2: {
                            Team1Leven1.setVisible(false);
                            Team1Leven2.setVisible(false);
                            Team1Leven3.setVisible(true);
                            break;
                        }
                        case 3: {
                            Team1Leven3.setVisible(false);
                            Team1Leven2.setVisible(false);
                            Team1Leven1.setVisible(false);
                            break;
                        }
                        default:
                            Team1Leven1.setVisible(true);
                            Team1Leven2.setVisible(true);
                            Team1Leven3.setVisible(true);
                            break;
                    }
                    switch (levensTeam1) {
                        case 1:
                            Team2Leven1.setVisible(false);
                            Team2Leven2.setVisible(true);
                            Team2Leven3.setVisible(true);
                            break;
                        case 2: {
                            Team2Leven2.setVisible(false);
                            Team2Leven1.setVisible(false);
                            Team2Leven3.setVisible(true);
                            break;
                        }
                        case 3: {
                            Team2Leven3.setVisible(false);
                            Team2Leven2.setVisible(false);
                            Team2Leven1.setVisible(false);
                            break;
                        }
                        default:
                            Team2Leven1.setVisible(true);
                            Team2Leven2.setVisible(true);
                            Team2Leven3.setVisible(true);
                            break;
                    }
                }

            }

        });
    }

    //get gamestatus update
    /*
        Hoeveel team levens?
        nieuwe instructie
*/
    private void buttonStartOnClick(MouseEvent mouseEvent) {
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


    private void buttonStartTimerOnClick(MouseEvent mouseEvent) {
        buttonStartTimer.setVisible(false);
        runnable = new Runnable() {
            @Override
            public void run() {
                timer = new Timer(1000, new ActionListener() {
                    int counter = view.stageController.game.getPlayerByName(StageController.playerName).getTeam().getTime();

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        counter--;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (correctIn()) {
                                    counter = view.stageController.game.getPlayerByName(StageController.playerName).getTeam().getTime();
                                }
                                progressBar.setProgress(counter * 0.1);
                                timeLabel.setText(Integer.toString(counter));
                            }
                        });
                        if (counter == 0) {
                            //dit moet worden verandert door een subtract time methode
                            Player player = view.stageController.game.getPlayerByName(StageController.playerName);

                            view.stageController.game.instructionIsToLate(player);
                            Team team = player.getTeam();
                            counter = team.getTime();
                        }
                    }
                });
                timer.start();
            }
        };
        runnable.run();
    }

    private boolean correctIn() {
        if (panelPushed) {
            panelPushed = false;
            return true;
        }
        return false;
    }

    public void checkInstruction(Panel panel, int sliderValue) {
        view.stageController.game.checkInstruction(panel, view.stageController.game.getPlayerByName(StageController.playerName), sliderValue);
        panelPushed = true;
        if(view.stageController.game.gameOver()){
            ScoreView scoreView = new ScoreView(view.stageController);
            view.pass(scoreView);
        }
    }
}
