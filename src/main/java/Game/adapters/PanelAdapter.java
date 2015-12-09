package Game.adapters;

import Game.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelAdapter {

    /**
     * @param panel
     */
    public static String toString(Panel panel) {
        return JsonAdapter.toString(panel, Panel.class);
    }

    public static String toString(List<Panel> panels) {
        return JsonAdapter.toString(panels, List.class);
    }

    /**
     * @param input
     */
    public static Panel toObject(String input) {
        return (Panel) JsonAdapter.toObject(input, Panel.class);
    }

    public static List<Panel> toObjects(String input) {
        return (List<Panel>) JsonAdapter.toObject(input, List.class);

    }

    public static Panel toObjectsSinglePanel(String input) {
        return (Panel) JsonAdapter.toObject(input, Panel.class);

    }

    /**
     * Author Kamil Wasylkiewicz
     *
     * @param panel the panel that needs to be made sendable
     * @return the reference to the panel
     */
    public static Panel makeSendable(Panel panel) {
        Panel tempPanel = new Panel(panel.getId(), panel.getMinimumValue(), panel.getMaximumValue(), panel.getText(), panel.getPanelType());

        return tempPanel;
    }

    /**
     * Author Kamil Wasylkiewicz
     * @param panels the list of panels that needs to be made sendable
     * @return the reference to the panels
     */
    public static List<Panel> makeSendable(List<Panel> panels) {
        List<Panel> tempPanels = new ArrayList<>();
        for (Panel panel : panels) {
            Panel tempPanel = new Panel(panel.getId(), panel.getMinimumValue(), panel.getMaximumValue(), panel.getText(), panel.getPanelType());
            tempPanels.add(tempPanel);
        }
        return tempPanels;
    }

}