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
public class ScoreView extends AbstractView implements IView{

    private ScoreViewController scoreViewController;
    public ScoreView(StageController stageController){
        super(stageController);
        scoreViewController = new ScoreViewController();
    }
    public boolean load() {
        Stage stage = new Stage();
        stage.setTitle("ScoreView");
        URL location = this.getClass().getResource("/ScoreView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new ScoreViewController());
        try {
            Pane myPane = (Pane)loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

        scoreViewController = (ScoreViewController)loader.getController();
        scoreViewController.setView(this);

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
