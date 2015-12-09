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
public class LobbyView extends AbstractView implements IView {
    private static final Logger log = Logger.getLogger(LobbyView.class.getName());
    private LobbyViewController lobbyViewController;

    public LobbyView(StageController stageController){
        super(stageController);
        lobbyViewController = new LobbyViewController();
    }

    /**
     * {@inheritDoc}
     */
    public boolean load() {
        log.log(Level.INFO, "Start loading LobbyView");
        Stage stage = new Stage();
        stage.setTitle("LobbyView");
        URL location = this.getClass().getResource("/LobbyView.fxml");
        log.log(Level.INFO, "loaded LobbyView from {0}", location.toString());
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new LobbyViewController());

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

        lobbyViewController = loader.getController();
        lobbyViewController.setView(this);
        log.log(Level.INFO, "Loaded LobbyView in view");
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
