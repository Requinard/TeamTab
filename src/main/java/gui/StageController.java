package gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {
    @NotNull
    Stage stage;

    @NotNull
    IView currentView;

    public StageController(Stage primaryStage) {
        stage = primaryStage;

        currentView = new MainView(this);

        currentView.load();
    }

    public void loadScene(IView nextView) {
        throw new NotImplementedException();
    }

    public void loadScene(Scene scene) {
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    public void refreshView() {
        throw new NotImplementedException();
    }

}
