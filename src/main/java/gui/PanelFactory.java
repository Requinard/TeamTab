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

    /**
     * @param panel
     * @param gameController
     */
    public static IPanel getPanel(Panel panel, GameController gameController) {
        IPanel ret;

        switch (panel.getPanelType()) {
            case Button:
                ret = new PanelButtonControl(panel, gameController);
                break;
            case HorizontalSlider:
                ret = new PanelHorizontalControl(panel, gameController);
                break;
            case VerticalSlider:
                ret = new PanelVerticalControl(panel, gameController);
                break;
            //todo : Fix dat hij button etc ziet als een paneltypeenum
            default:
                ret = new PanelHorizontalControl(panel, gameController);
                break;
        }
        return ret;
    }
}
