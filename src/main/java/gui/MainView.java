package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

        Stage stage = new Stage();
        stage.setTitle("MainView");
        URL location = this.getClass().getResource("/MainView.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(new MainController());
        try {
            Pane myPane = (Pane)loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

       mainController = (MainController)loader.getController();


        return true;
    }

    public boolean deload() {
        return false;
    }

    public boolean pass(IView nextView) {
        stageController.loadScene(nextView);

        return true;
    }
}
