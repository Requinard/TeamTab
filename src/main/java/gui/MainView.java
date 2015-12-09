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
 * Created by david on 6-10-15.
 */
public class MainView extends AbstractView implements IView {
    private static final Logger log = Logger.getLogger(MainView.class.getName());
    private MainController mainController;

    public MainView(StageController stageController) {
        super(stageController);
        mainController = new MainController();
    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading MainView");
        Stage stage = new Stage();
        stage.setTitle("MainView");
        URL location = this.getClass().getResource("/MainView.fxml");
        log.log(Level.INFO, "loaded MainView from {0}", location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new MainController());
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

        mainController = loader.getController();
       mainController.setView(this);
        log.log(Level.INFO, "Loaded MainView in view");

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
