package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Created by david on 6-10-15.
 */
public class MainView extends AbstractView implements IView {
    public MainView(StageController stageController) {
        super(stageController);
    }

    public boolean load() {
        Parent root = null;
        try {
            URL location = this.getClass().getClassLoader().getResource("MainView.fxml");
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
}
