package gui;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Kevin on 5-10-2015.
 */
public class StageController {
    @NotNull
    Stage stage;

    IView currentView;

    public StageController(Stage primaryStage) {
        stage = primaryStage;

        setUpStage();
    }

    private void setUpStage() {
        stage.setTitle("AEX banner");
        //primaryStage.setScene(scene);
        stage.show();
        stage.toFront();
    }


    public void loadScene(IView nextView) {
        throw new NotImplementedException();
    }

    public void refreshView() {
        throw new NotImplementedException();
    }

}
