package gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class GameView extends AbstractView implements IView {
        private GameController gameController;
        public GameView(StageController stageController){
            super(stageController);
            gameController = new GameController();
        }
    public boolean load() {
        Parent root = null;
        try {
            URL location = this.getClass().getClassLoader().getResource("GameView.fxml");
            root = FXMLLoader.load(location);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        scene = new Scene(root, 100, 100);

        stageController.loadScene(scene);

        return true;
    }

        public boolean deload() {
            return false;
        }

        public boolean pass(IView nextView) {
            return false;
        }

    public boolean passScene(Scene scene) {
        return false;
    }
}
