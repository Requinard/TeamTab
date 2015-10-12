package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {

    Stage stage;
    IView currentView;

    public StageController(Stage primaryStage) {

        stage = primaryStage;

        currentView = new MainView(this);

        currentView.load();
    }

    public void loadScene(IView nextView) {
        currentView.deload();

        nextView.load();

        refreshView();
    }

    public void loadScene(Scene scene) {
        stage.setTitle("Test");
        stage.close();
        stage.setScene(scene);
        if(!stage.isShowing()){
            stage.show();
        }
        stage.toFront();
    }

    public void refreshView() {
        return;
    }

}
