package gui;

import javafx.scene.Scene;

/**
 * Created by david on 6-10-15.
 */
public interface IView {

    /**
     * Attempts to load a view's FXML and render it in the scene
     * @return Success of the operations
     */
    boolean load();

    /**
     * Attempts to load the next scene
     * @param scene next scene that needs to be loaded
     * @return Success of the operations
     */
    boolean passScene(Scene scene);

    /**
     * Attempts to deload a scene.
     * @return Whether the operations was successful
     */
    boolean deload();

    /**
     * Attempts to deload this view, and load the next iView
     * @param nextView Next view that needs to be loaded
     * @return Success of the operations
     */
    boolean pass(IView nextView);
}
