package gui.panel;

import Game.Panel;
import javafx.scene.layout.VBox;

/**
 * Created by Kevin on 12-10-2015.
 */
 public abstract class AbstractPanelControl extends VBox{
 Panel panelused;

 /**
  * @autor Kevin Jetten
  * the panel gets saved in the class
  * @param panel
  */
 public AbstractPanelControl(Panel panel) {
  this.panelused = panel;
 }
 public Panel getPanel()
 {
  return panelused;
 }
}
