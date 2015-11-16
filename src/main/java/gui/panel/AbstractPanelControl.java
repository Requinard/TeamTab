package gui.panel;

import gui.GameController;
import javafx.scene.layout.VBox;
import Game.*;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
 GameController gameController;
 private Panel panelused;

 /**
	 * the panel gets saved in the class
	 * @author Kevin Jetten
	 * @param panel Panel that is initialized
	 * @param gameController
	 */
 AbstractPanelControl(Panel panel, GameController gameController) {
  this.panelused = panel;
  this.gameController = gameController;
 }

 /**
  * Gets panel
  * @return the panel
  */
  Panel getPanel()
 {
  return panelused;
 }

}
