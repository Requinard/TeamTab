package gui;

import Game.Panel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javassist.bytecode.stackmap.TypeData;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class GameController implements Initializable {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
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
    @FXML
    private TextField lblTeamName1;
    @FXML
    private TextField lblTeamName2;
    private GameView view;
    private Runnable runnable;
    private PanelFactory panelFactory;
    private java.util.Timer timerRefresh;
    private TimerTask timerTask;
    private ArrayList<Panel> panelHolder;
    private boolean panelPushed;
    private AudioPlayer audioPlayer;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * start a timer for refreshView
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        log.log(Level.INFO, "Start initializing the gamecontroller");
        panelFactory = new PanelFactory();

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

        URL url = this.getClass().getClassLoader().getResource("audio/ExplosieMetBliep.mp3");
        log.log(Level.INFO, "Audiofile url set to: {0}", url.toString());
        try (FileInputStream fileInputStream = new FileInputStream(url.getPath())) {
            audioPlayer = new AudioPlayer(fileInputStream.toString());
            log.log(Level.INFO, "explosion audioplayer created");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            log.log(Level.SEVERE, e1.toString(), e1);
        } catch (IOException e1) {
            e1.printStackTrace();
            log.log(Level.SEVERE, e1.toString(), e1);
        }
    }

    /**
     * Call methods to refresh the View
     */
    private void refreshView() {
        log.log(Level.FINER, "refreshView started");
        showTeamLevens();
        showTeamInstructionCount();
        showPlayerInstruction();
        panelChecker();
        log.log(Level.FINER, "refreshView ended");
    }

    /**
     * checks if grid with panels is filled
     */
    private void panelChecker() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                /*
                if (!panelHolder.equals(view.stageController.game.getPlayerByName(StageController.playerName).getPanels())) {
                    fillGridWithPanels();
                    log.log(Level.INFO, "Gridview filled with panels");
                }
                Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo:De oude methode vervangenen met de nieuwe methodes
                */
            }
        });
    }

    /**
     * Sets the GameView
     * refreshes the View
     * @param gameView
     */
    public void setView(GameView gameView) {
        log.log(Level.FINER, "setView started");
        view = gameView;
        fillGridWithPanels();
        showTeamLevens();
        setTeamNames();
        log.log(Level.FINER, "setView ended");
    }

    /**
     * Fills the gridPane with panels in the right row and column
     * refreshes every Round
     */
    public void fillGridWithPanels() {
        log.log(Level.INFO, "fillGridWithPanels started");
        gridPane.getChildren().clear();
        gridPane.setMinSize(0, 0);
        gridPane.setAlignment(Pos.CENTER);
        log.log(Level.INFO, "gridPane children cleared, minsize set and alignment set");
     /*
        final ArrayList<Panel> panels = view.stageController.game.getPlayerByName(StageController.playerName).getPanels();
        panelHolder = panels;
        int column = 0;
        int row = 0;
        log.log(Level.INFO, "Add panels to the gridpane");
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
            log.log(Level.FINER, "Added panel with text {0}", panel.getText());
            row++;
        }
        log.log(Level.INFO, "Loaded {0} panels in the gridPane", panels.size());

           Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: de Arralist panels moet opgehaald worden vanuit de nieuwe game klassen, de rest van de code is wel nog valide
        */
    }

    /**
     * Shows the player Instruction in instructionLabel
     * refreshes ever 30 ms
     */
    private void showPlayerInstruction() {

        Platform.runLater(new Runnable() {
            public void run() {
                /*
                if (view.stageController.game.getPlayerByName(StageController.playerName) != null)
                    instructionLabel.setText(view.stageController.game.getPlayerByName(StageController.playerName).getInstruction().toString() + " to: " + view.stageController.game.getPlayerByName(StageController.playerName).getInstruction().getValue());
             Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo:De oude methode vervangenen met de nieuwe methodes
          */
            }
        });
    }

    /**
     * Shows amount of teams correct instructions in labelCorrectInstructions
     * refreshes ever 30 ms
     */
    private void showTeamInstructionCount() {
        Platform.runLater(new Runnable() {
            public void run() {
                /*
                if (view.stageController.game.getPlayerByName(StageController.playerName) != null)
                    labelCorrectInstructions.setText(view.stageController.game.getPlayerByName(StageController.playerName).getTeam().getCorrectInstruction() + "");
               Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo:De oude methode vervangenen met de nieuwe methodes
            */
            }
        });
    }

    /**
     * Shows for every team the amount of lives left. represented in buldings that are still standing
     * refreshes ever 30 ms
     */
    private void showTeamLevens() {
        Platform.runLater(new Runnable() {
            public void run() {
                int levensTeam1;
                int levensTeam2;
                /*
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
                            audioPlayer.play();
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
                       Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: De if statement goed maken en de levens van de teams setten. De rest van de code is valide
                }*/
            }
        });
    }

    /**
     * Sets the name of the teams that are playing
     */
    private void setTeamNames()
    {
        /*
        lblTeamName1.setText(view.stageController.game.allTeams().get(1).getName());
        lblTeamName2.setText(view.stageController.game.allTeams().get(0).getName());
           Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: De labels voorzien van de juiste teamname
        */
    }

    /**
     * When button StartTimer is pressed start a timer which counts down the amount of time you have to fulfill a instruction
     * @param mouseEvent
     */
    private void buttonStartTimerOnClick(MouseEvent mouseEvent) {
        buttonStartTimer.setVisible(false);
        runnable = new Runnable() {
            @Override
            public void run() {
                /*
                timer = new Timer(1000, new ActionListener() {

                    int counter = view.stageController.game.getPlayerByName(StageController.playerName).getTeam().getTime();

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        counter--;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //check if counter must be reset because a button or slider was used
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
                     Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: De methodes getPlayerByName vervangen en de methode instructionIsToLate vervangen
                });*/
                timer.start();
            }
        };
        runnable.run();
    }

    /**
     * Trigger for if a panel was pressed or a slider was used
     * sets panelPushed back to false after one time
     * @return true if a panel was pressed or used
     */
    private boolean correctIn() {
        if (panelPushed) {
            panelPushed = false;
            return true;
        }
        return false;
    }

    /**
	 * Check if instructions was correctly completed
	 * sets panelPused to true
	 * If lives of a team is 0 change to ScoreView
	 * @param panel pressed/used panel
	 * @param sliderValue value of the panel
	 */
    public void checkInstruction(Panel panel, int sliderValue) {
        /*
        view.stageController.game.checkInstruction(panel, view.stageController.game.getPlayerByName(StageController.playerName), sliderValue);
        panelPushed = true;
        audioPlayer = new AudioPlayer("src/main/resources/audio/doorknippen+loskoppelen.mp3");
        audioPlayer.play();
        for (Team teams : view.stageController.game.allTeams()) {
            if (teams.getLives() <= 0) {
                ScoreView scoreView = new ScoreView(view.stageController);
                view.pass(scoreView);
            }
        }
         Deze code is uitgecomment zodat we weten welke oude methode er stond
                todo: De methodes getPlayerByName vervangen. Ook de methodes allTeams.
        */
    }
}
