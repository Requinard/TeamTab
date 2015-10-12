package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class JoinView extends AbstractView implements IView {
    private JoinViewController joinViewController;

    public JoinView(StageController stageController) {
        super(stageController);
        joinViewController = new JoinViewController();

    }

    public boolean load() {
        Parent root = null;
        try {
            URL location = this.getClass().getClassLoader().getResource("JoinView.fxml");
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
