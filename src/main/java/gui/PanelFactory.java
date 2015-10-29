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
    public static IPanel getPanel(Panel panel) {
        IPanel ret;

        switch (panel.getType()) {
            case Button:
                ret = new PanelButtonControl(panel);
                break;
            case HorizontalSlider:
                ret = new PanelHorizontalControl(panel);
                break;
            case VerticalSlider:
                ret = new PanelVerticalControl(panel);
                break;
            default:
                ret = new PanelHorizontalControl(panel);
                break;
        }
        return ret;
    }
}
