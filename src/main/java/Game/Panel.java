package game;

public class Panel {

    private PanelTypeEnum panelType;
    private int minimumValue = 0;
    private int maximumValue = 1;
    private boolean text;

    public PanelTypeEnum getPanelType() {
        return this.panelType;
    }

    public int getMinimumValue() {
        return this.minimumValue;
    }

    public int getMaximumValue() {
        return this.maximumValue;
    }

    public boolean isText() {
        return this.text;
    }

}