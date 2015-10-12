package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Vito Corleone on 6-10-2015.
 */
public class LobbyView extends AbstractView implements IView {
    private LobbyViewController lobbyViewController;

    public LobbyView(StageController stageController){
        super(stageController);
        lobbyViewController = new LobbyViewController();
    }

    public boolean load() {

        Stage stage = new Stage();
        stage.setTitle("LobbyView");
        URL location = this.getClass().getResource("/LobbyView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new LobbyViewController());
        try {
            Pane myPane = (Pane)loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            passScene(scene);
            //stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        lobbyViewController = (LobbyViewController)loader.getController();
        lobbyViewController.setView(this);

        return true;
    }

    public boolean deload() {
        return false;
    }

    public boolean pass(IView nextView) {
        stageController.loadScene(nextView);
        return true;
    }

    public boolean passScene(IView nextView) {

        return true;
    }

    public boolean passScene(Scene scene) {
        stageController.loadScene(scene);
        return true;
    }
}
