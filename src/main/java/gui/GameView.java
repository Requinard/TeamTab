package gui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
        Stage stage = new Stage();
        stage.setTitle("GameView");
        URL location = this.getClass().getResource("/GameView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new GameController());
        try {
            Pane myPane = (Pane)loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

        gameController = (GameController)loader.getController();
        gameController.setView(this);

        return true;
    }
    public boolean deload() {
        return false;
    }

    public boolean pass(IView nextView) {
        stageController.loadScene(nextView);
        return true;
    }

    public boolean passScene(Scene scene) {
        stageController.loadScene(scene);
        return true;
    }

}
