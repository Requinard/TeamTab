package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class ScoreView extends AbstractView implements IView{

    private static final Logger log = Logger.getLogger(ScoreView.class.getName());
    private ScoreViewController scoreViewController;

    public ScoreView(StageController stageController){
        super(stageController);
        scoreViewController = new ScoreViewController();
    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading ScoreView");
        Stage stage = new Stage();
        stage.setTitle("ScoreView");
        URL location = this.getClass().getResource("/ScoreView.fxml");
        log.log(Level.INFO, "loaded ScoreView from {0}", location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new ScoreViewController());
        try {
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);
            log.log(Level.FINER, "loaded pane in scene and set the scene");

        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, e.toString(), e);
        }

        scoreViewController = loader.getController();
        scoreViewController.setView(this);
        log.log(Level.INFO, "Loaded ScoreView in view");

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deload() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean pass(IView nextView) {
        stageController.loadScene(nextView);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean passScene(Scene scene) {
        stageController.loadScene(scene);
        return true;
    }
}
