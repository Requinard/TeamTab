package Game;

public class Panel {

    private PanelTypeEnum panelType;
    private int minimumValue = 0;
    private int maximumValue = 1;
    private String text;

    public Panel(PanelTypeEnum panelType, int minimumValue, int maximumValue, String text) {
        this.panelType = panelType;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.text = text;
    }

    public PanelTypeEnum getPanelType() {
        return this.panelType;
    }

    public int getMinimumValue() {
        return this.minimumValue;
    }

    public int getMaximumValue() {
        return this.maximumValue;
    }

    public String getText() {
        return this.text;
    }

}