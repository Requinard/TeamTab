package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javassist.bytecode.stackmap.TypeData;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class StartView extends AbstractView implements IView {
    private StartViewController startViewController;
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    public StartView(StageController stageController){
        super(stageController);
        startViewController = new StartViewController();
    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading StartView");
        Stage stage = new Stage();
        stage.setTitle("StartView");
        URL location = this.getClass().getResource("/StartView.fxml");
        log.log(Level.INFO, "loaded ScoreView from {0}",location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new StartViewController());
        try {
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);
            log.log(Level.FINER, "loaded pane in scene and set the scene");

        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, e.toString(), e);
            return false;
        }

        startViewController = loader.getController();
        startViewController.setView(this);
        log.log(Level.INFO, "Loaded StartView in view");

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
