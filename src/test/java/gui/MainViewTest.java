package gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import sun.applet.Main;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 7-10-15.
 */
public class MainViewTest extends ApplicationTest {
    private Stage stage;
    private StageController stageController;

    @Test
    public void testLoad() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(new Runnable() {
            public void run() {
                boolean load = mainView.load();
                assertTrue(load);
            }
        });
    }

    @Test
    public void testDeload() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(new Runnable() {
            public void run() {
                boolean deload = mainView.deload();
                assertTrue(deload);
            }
        });
    }

    @Test
    public void testPass() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(new Runnable() {
            public void run() {
                IView nextView = new LobbyView(stageController);

                boolean pass = mainView.pass(nextView);

                assertTrue(pass);
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.stageController = new StageController(stage);
    }
}