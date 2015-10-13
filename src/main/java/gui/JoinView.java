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
public class JoinView extends AbstractView implements IView {
    private JoinViewController joinViewController;

    public JoinView(StageController stageController) {
        super(stageController);
        joinViewController = new JoinViewController();

    }

    public boolean load() {
        Stage stage = new Stage();
        stage.setTitle("JoinView");
        URL location = this.getClass().getResource("/JoinView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new JoinViewController());
        try {
            Pane myPane = (Pane)loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

        joinViewController = (JoinViewController)loader.getController();
        joinViewController.setView(this);

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
