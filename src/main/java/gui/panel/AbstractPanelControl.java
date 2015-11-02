package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
 GameController gameController;
 private Panel panelused;

 /**
  * @author Kevin Jetten
  * the panel gets saved in the class
  * @param panel Panel that is initialized
  */
 AbstractPanelControl(Panel panel, GameController gameController) {
  this.panelused = panel;
  this.gameController = gameController;
 }

 Panel getPanel()
 {
  return panelused;
 }


}
