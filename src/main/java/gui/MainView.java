package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

/**
 * Created by david on 6-10-15.
 */
public class MainView extends AbstractView implements IView {
    private MainController mainController;
    @FXML
    private Button buttonJoin;

    public MainView(StageController stageController) {
        super(stageController);
        mainController = new MainController();
    }

    public boolean load() {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        try {
            URL location = this.getClass().getClassLoader().getResource("MainView.fxml");
            root = loader.load(location);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        scene = new Scene(root, 1280, 1024);

        stageController.loadScene(scene);

        mainController = (MainController)loader.getController();

        return true;
    }

    public boolean deload() {
        return false;
    }

    public boolean pass(IView nextView) {
        stageController.loadScene(nextView);
    }
}
