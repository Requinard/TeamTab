package Game;

/**
 * Created by Qun on 12-10-2015.
 */
public class Panel {
    private int id;
    private PanelTypeEnum type;
    private String text;
    private int min;
    private int max;
    private int current;
    private Instruction instruction;


    /**
     * @param id          Panel number id
     * @param type        Type of panel (Button, horizontal or vertical)
     * @param text        Text that gives a description
     * @param min         Minimum value a panel ccan have
     * @param max         Maximum value a panel can have
     * @author David
     */
    public Panel(int id, int type, String text, int min, int max) {
        this.id = id;
        this.type = PanelTypeEnum.values()[type];
        this.text = text;
        this.min = min;
        this.max = max;
    }

    public Panel(int id, PanelTypeEnum type, String text, int min, int max) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the ID of the panel
     * @return id of the panel
     */
    public int getId() {
        return id;
    }


    /**
    * @Returns the type of the panel
     */
    public PanelTypeEnum getType(){
        return type;
    }

    /**
     * Returns the text on the panel
     * @return text on the panel
     */
    public String getText(){
        return text;
    }


    /**
     * the min value of the control for example if you have a slidercontrol between 0-5 it returns 0
     * @return minimum value of the control
     */
    public int getMin(){
        return min;
    }


    /**
     * the max value of the control for example if you have a slidercontrol between 0-5 it returns 5
     * @return maximum value of the control
     */
    public int getMax(){
        return max;
    }

    /**
     * Returns currenct value of the control
     * @return panelcontrol value
     */
    public int getCurrent(){return current;}

    /**
     * Sets the current value of the control
     * @param value
     */
    public void setCurrent(int value){current = value;}

    /**
     * Returns the instruction on the panel
     * @return the instruction on the panel
     */
    public Instruction getInstruction() {
        return instruction;
    }
}
