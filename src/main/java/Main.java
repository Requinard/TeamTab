/**
 * Created by david on 6-10-15.
 */

import gui.StageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import networking.server.NetworkServerSingleton;
import tracker.JanitorSingleton;

import java.io.File;
import java.util.logging.*;

public class Main extends Application {

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     * <p/>
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     * <p/>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void stop() throws Exception {
        JanitorSingleton.getInstance().clean();
        NetworkServerSingleton.stopAllServers();
        Platform.exit();
        System.exit(0);
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
        if (!(new File("logs").exists()))
            new File("logs").mkdir();
        Handler fh = new FileHandler(String.format("logs/%d.xml", System.currentTimeMillis()));
        Logger.getLogger("").addHandler(fh);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        Logger.getGlobal().addHandler(consoleHandler);
        new StageController(primaryStage);
    }
}
