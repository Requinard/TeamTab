/**
 * Created by david on 6-10-15.
 */

import gui.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Main extends Application {

    private static boolean manageLogger() {
        String filename = String.valueOf(System.currentTimeMillis());
        Handler fh;

        try {
            File directory = new File("logs");
            directory.mkdir();

            fh = new FileHandler(filename.trim() + ".xml");
        } catch (IOException e) {
            System.out.println("Failed to acquire file lock!");
            e.printStackTrace();
            return false;
        }

        Logger.getLogger("").addHandler(fh);

        return true;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if (!manageLogger()) {
            return;
        } else {
            new StageController(primaryStage);
        }
    }
}
