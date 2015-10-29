package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
 Panel panelused;
 GameController gameController;

 /**
  * @autor Kevin Jetten
  * the panel gets saved in the class
  * @param panel
  */
 public AbstractPanelControl(Panel panel, GameController gameController) {
  this.panelused = panel;
  this.gameController = gameController;
 }
 public Panel getPanel()
 {
  return panelused;
 }


}
