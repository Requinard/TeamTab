package Game;

public class Panel {

    private PanelTypeEnum panelType;
    private int id;
    private int minimumValue = 0;
    private int maximumValue = 1;
    private String text;
    public Panel(int id, int minimumValue, int maximumValue, String text, PanelTypeEnum panelType) {
        this.id = id;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.text = text;
        this.panelType = panelType;
    }

    public int getId() {
        return id;
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