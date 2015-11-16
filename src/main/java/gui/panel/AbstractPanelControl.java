package gui.panel;

import game.Panel;
import gui.GameController;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
 GameController gameController;
 private Panel panelused;

 /**
  * the panel gets saved in the class
  * @param panel           Panel that is initialized
  * @param gameController
  * @author Kevin Jetten
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
