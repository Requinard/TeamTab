package Game.adapters;

import Game.Panel;

import java.util.List;

public class PanelAdapter {

    /**
     * @param panel
     */
    public String toString(Panel panel) {
        return JsonAdapter.toString(panel, Panel.class);
    }

    public String toString(List<Panel> panels) {
        return JsonAdapter.toString(panels, List.class);
    }

    /**
     * @param input
     */
    public Panel toObject(String input) {
        return (Panel) JsonAdapter.toObject(input, Panel.class);
    }

    public List<Panel> toObjects(String input) {
        return (List<Panel>) JsonAdapter.toObject(input, List.class);

    }

}