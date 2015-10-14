package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class StartView extends AbstractView implements IView {
    private StartViewController startViewController;
    public StartView(StageController stageController){
        super(stageController);
        startViewController = new StartViewController();
    }
    public boolean load() {

        Stage stage = new Stage();
        stage.setTitle("StartView");
        URL location = this.getClass().getResource("/StartView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new StartViewController());
        try {
            Pane myPane = loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        startViewController = loader.getController();
        startViewController.setView(this);

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
