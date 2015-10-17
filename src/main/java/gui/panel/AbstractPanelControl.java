package gui.panel;

import Game.Panel;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
    Panel panel;

    public AbstractPanelControl(Panel panel) {
        this.panel = panel;
    }
}
