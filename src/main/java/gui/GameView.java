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
public class GameView extends AbstractView implements IView {
    private static final Logger log = Logger.getLogger(GameView.class.getName());
    private GameController gameController;

    /**
     * @param stageController Stagecontroller that is controlling the application
     */
    public GameView(StageController stageController){
        super(stageController);
        gameController = new GameController();

    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading gameview");
        Stage stage = new Stage();
        stage.setTitle("GameView");
        URL location = this.getClass().getResource("/GameView.fxml");
        log.log(Level.INFO, "loaded gameview from {0}", location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new GameController());
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

        gameController = loader.getController();
        gameController.setView(this);
        log.log(Level.INFO, "Loaded gameview in view");
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
