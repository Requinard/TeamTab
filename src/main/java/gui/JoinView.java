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
public class JoinView extends AbstractView implements IView {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    private JoinViewController joinViewController;

    public JoinView(StageController stageController) {
        super(stageController);
        joinViewController = new JoinViewController();

    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading JoinView");
        Stage stage = new Stage();
        stage.setTitle("JoinView");
        URL location = this.getClass().getResource("/JoinView.fxml");
        log.log(Level.INFO, "loaded JoinView from {0}", location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new JoinViewController());

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

        joinViewController = loader.getController();
        joinViewController.setView(this);
        log.log(Level.INFO, "Loaded JoinView in view");
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
