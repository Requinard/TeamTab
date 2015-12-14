package gui;

import Game.Panel;
import gui.panel.IPanel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class GameController implements Initializable {
    private static final Logger log = Logger.getLogger(GameController.class.getName());
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
    private List<Panel> panelHolder;
    private boolean panelPushed;
    //private AudioPlayer audioPlayer;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * <p/>
     * start a timer for refreshView
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        log.log(Level.INFO, "Start initializing the gamecontroller");
        panelFactory = new PanelFactory();

        buttonStartTimer.setOnMouseClicked(this::buttonStartTimerOnClick);

        timerRefresh = new java.util.Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                refreshView();
            }
        };
        timerRefresh.schedule(timerTask, 0, 30);

        /*
        URL url = this.getClass().getClassLoader().getResource("audio/ExplosieMetBliep.mp3");
        log.log(Level.INFO, "Audiofile url set to: {0}", url.toString());
        try (FileInputStream fileInputStream = new FileInputStream(url.getPath())) {
            //audioPlayer = new AudioPlayer(fileInputStream.toString());
            log.log(Level.INFO, "explosion audioplayer created");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            log.log(Level.SEVERE, e1.toString(), e1);
        } catch (IOException e1) {
            e1.printStackTrace();
            log.log(Level.SEVERE, e1.toString(), e1);
        }
        */
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
        Platform.runLater(() -> {

            if (view.stageController.clientGame.localPlayer.getPanels().size() != panelHolder.size()) {
                panelHolder = view.stageController.clientGame.localPlayer.getPanels();
                fillGridWithPanels();
                log.log(Level.INFO, "Gridview filled with {0} panels", view.stageController.clientGame.localPlayer.getPanels().size());
            }
        });
    }

    /**
     * Sets the GameView
     * refreshes the View
     *
     * @param gameView the view where the game is played
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

        final ArrayList<Panel> panels = (ArrayList<Panel>) view.stageController.clientGame.localPlayer.getPanels();

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
        panelHolder = new ArrayList<>();
        panelHolder.addAll(panels);
    }

    /**
     * Shows the player Instruction in instructionLabel
     * refreshes ever 30 ms
     */
    private void showPlayerInstruction() {

        Platform.runLater(() -> {
            if (view.stageController.clientGame.localPlayer != null)
                log.log(Level.FINE, "Retrieving instruction for player {0}", view.stageController.clientGame.localPlayer.getUsername());
            if (view.stageController.clientGame.localPlayer.getActiveInstruction().getIntendedValue() == 0) {
                instructionLabel.setText("Press the " + view.stageController.clientGame.localPlayer.getActiveInstruction().getPanel().getText() + " button");
            } else {
                instructionLabel.setText("Set " + view.stageController.clientGame.localPlayer.getActiveInstruction().getPanel().getText() + " to: " + view.stageController.clientGame.localPlayer.getActiveInstruction().getIntendedValue());
            }
            //log.log(Level.FINE, "Instruction {0} is shown to player {0}", new Object[]{StageController.currentPlayer.getActiveInstruction().getPanel().getText(), StageController.currentPlayer.getUsername()});
        });
    }

    /**
     * Shows amount of teams correct instructions in labelCorrectInstructions
     * refreshes ever 30 ms
     */
    private void showTeamInstructionCount() {
        Platform.runLater(() -> {
            if (view.stageController.clientGame.localPlayer != null)
                log.log(Level.FINE, "Retrieving score for player {0}", view.stageController.clientGame.localPlayer.getUsername());

            labelCorrectInstructions.setText(view.stageController.clientGame.localPlayer.getTeam().getScore() + "");
            //log.log(Level.FINE, "Team {0} has a scored {1} point", new Object[]{StageController.currentPlayer.getTeam().getName(), StageController.currentPlayer.getTeam().getScore()});
        });
    }

    /**
     * Shows for every team the amount of lives left. represented in buldings that are still standing
     * refreshes ever 30 ms
     */
    private void showTeamLevens() {
        Platform.runLater(() -> {
            int levensTeam1;
            int levensTeam2;

            if (view.stageController.clientGame.localPlayer != null) {
                levensTeam1 = view.stageController.clientGame.getTeams().get(0).getLives();
                levensTeam2 = view.stageController.clientGame.getTeams().get(1).getLives();

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
                        // audioPlayer.play();
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
        });
    }

    /**
     * //TODO fix it so it works for multiple teams
     * Sets the name of the teams that are playing
     */
    private void setTeamNames() {
        Platform.runLater(() -> {
            lblTeamName1.setText(view.stageController.clientGame.getTeams().get(1).getName());
            lblTeamName2.setText(view.stageController.clientGame.getTeams().get(0).getName());
        });
        //log.log(Level.INFO, "The names of team {0} and {1} are set ", new Object[]{view.stageController.clientGame.getTeams().get(1).getName(), view.stageController.clientGame.getTeams().get(0).getName()});
    }

    /**
     * When button StartTimer is pressed start a timer which counts down the amount of time you have to fulfill a instruction
     *
     * @param mouseEvent starts the timer for the game
     */
    private void buttonStartTimerOnClick(MouseEvent mouseEvent) {
        buttonStartTimer.setVisible(false);

            timer = new Timer(1000, new ActionListener() {

                int counter = view.stageController.clientGame.localTeam.getTime();

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    //log.log(Level.FINE, "Timer for player {0} is started. Player {1} has {2} seconds", new Object[]{view.stageController.clientGame.localPlayer.getUsername(), StageController.currentPlayer, StageController.currentPlayer.getTeam().getTime()});
                    counter--;
                    //check if counter must be reset because a button or slider was used
                    if (correctIn()) {
                        counter = view.stageController.clientGame.localTeam.getTime();
                    }
                    Platform.runLater(() -> {
                        progressBar.setProgress(counter * 0.1);
                        timeLabel.setText(counter + "");
                    });
                    if (counter == 0) {
                        view.stageController.clientGame.registerInvalidInstruction(view.stageController.clientGame.localPlayer.getActiveInstruction());
                        //Team team = view.stageController.clientGame.localPlayer.getTeam();
                        counter = view.stageController.clientGame.localTeam.getTime();
                    }
                }
            });
            timer.start();
    }

    /**
     * Trigger for if a panel was pressed or a slider was used
     * sets panelPushed back to false after one time
     *
     * @return true if a panel was pressed or used
     */
    private boolean correctIn() {
        if (panelPushed) {
            panelPushed = false;
            log.log(Level.FINE, "panel is pushed");
            return true;
        }
        log.log(Level.FINE, "panel is not pushed");
        return false;
    }

    /**
     * Check if instructions was correctly completed
     * sets panelPused to true
     * If lives of a team is 0 change to ScoreView
     *
     * @param panel       pressed/used panel
     * @param sliderValue value of the panel
     */
    public void checkInstruction(Panel panel, int sliderValue) {
        log.log(Level.INFO, "Processing the panel for {0}", panel.getText());
        view.stageController.clientGame.processPanel(view.stageController.clientGame.localPlayer, panel);
        panelPushed = true;
        //audioPlayer = new AudioPlayer("src/main/resources/audio/doorknippen+loskoppelen.mp3");
        //audioPlayer.play();
        if (view.stageController.clientGame.hasGameEnded()) {
            timerTask.cancel();
            ScoreView scoreView = new ScoreView(view.stageController);
            view.pass(scoreView);
        }

    }
}
