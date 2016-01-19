package Game;

public class Panel {

    private PanelTypeEnum panelType;
    private int id;
    private int minimumValue = 0;
    private int maximumValue = 1;
    private String text;
    private int value;

    /**
     * The constructor of a panel
     * Author Frank Hartman
     *
     * @param id           The id of the panel
     * @param minimumValue The minimum value
     * @param maximumValue The maximum value
     * @param text         The text on the panel
     * @param panelType    The panel type of the panel
     */
    public Panel(int id, int minimumValue, int maximumValue, String text, PanelTypeEnum panelType) {
        this.id = id;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.text = text;
        this.panelType = panelType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the id of a panel
     * Author Frank Hartman
     */
    public int getId() {
        return id;
    }

    /**
     * Get the panel type enum of a panel
     * Author Frank Hartman
     * @return the panelTypeEnum
     */
    public PanelTypeEnum getPanelType() {
        return this.panelType;
    }

    /**
     * Get the minimum value of the panel
     * Author Frank Hartman
     * @return the minimum value of the panel
     */
    public int getMinimumValue() {
        return this.minimumValue;
    }

    /**
     * Get the maximum value of the panel
     * @return the maximum value of the panel
     */
    public int getMaximumValue() {
        return this.maximumValue;
    }

    /**
     * Get the text from the panel
     * @return The text
     */
    public String getText() {
        return this.text;
    }

}