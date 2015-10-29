package gui;

import Game.Panel;
import gui.panel.IPanel;
import gui.panel.PanelButtonControl;
import gui.panel.PanelHorizontalControl;
import gui.panel.PanelVerticalControl;

/**
 * Created by david on 17-10-15.
 */
public class PanelFactory {
    public static IPanel getPanel(Panel panel, GameController gameController) {
        IPanel ret;

        switch (panel.getType()) {
            case Button:
                ret = new PanelButtonControl(panel, gameController);
                break;
            case HorizontalSlider:
                ret = new PanelHorizontalControl(panel, gameController);
                break;
            case VerticalSlider:
                ret = new PanelVerticalControl(panel, gameController);
                break;
            default:
                ret = new PanelHorizontalControl(panel, gameController);
                break;
        }
        return ret;
    }
}
