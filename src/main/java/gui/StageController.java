package gui;

import javafx.scene.Scene;
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
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    public void refreshView() {
        return;
    }

}
