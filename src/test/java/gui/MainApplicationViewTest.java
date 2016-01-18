package gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by david on 7-10-15.
 */
public class MainApplicationViewTest {
    private Stage stage;
    private StageController stageController;

    @Test
    public void testLoad() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(() -> {
            boolean load = mainView.load();
            assertTrue(load);
        });
    }

    @Test
    public void testDeload() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(() -> {
            boolean deload = mainView.deload();
            assertTrue(deload);
        });
    }

    @Test
    public void testPass() throws Exception {
        final MainView mainView = new MainView(stageController);

        Platform.runLater(() -> {
            IView nextView = new LobbyView(stageController);

            boolean pass = mainView.pass(nextView);

            assertTrue(pass);
        });
    }


}