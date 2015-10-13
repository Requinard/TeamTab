package gui;

import javafx.scene.Scene;

/**
 * Created by david on 6-10-15.
 */
public abstract class AbstractView {
    protected StageController stageController;
    protected Scene scene;

    /**
     * Loads the base of an abstract view
     * @param stageController Stagecontroller that is controlling the application
     */
    public AbstractView(StageController stageController) {
        this.stageController = stageController;
    }
}
