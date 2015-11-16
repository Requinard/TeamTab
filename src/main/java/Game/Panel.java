package Game;

public class Panel {

    private PanelTypeEnum panelType;
    private int minimumValue = 0;
    private int maximumValue = 1;
    private string text;

    public PanelTypeEnum getPanelType() {
        return this.panelType;
    }

    public int getMinimumValue() {
        return this.minimumValue;
    }

    public int getMaximumValue() {
        return this.maximumValue;
    }

    public string getText() {
        return this.text;
    }

}