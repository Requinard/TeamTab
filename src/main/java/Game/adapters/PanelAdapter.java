package Game.adapters;

import Game.Panel;

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

}