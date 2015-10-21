package Game;

/**
 * Created by Qun on 12-10-2015.
 */
public class Panel {
    private int id;
    private int type;
    private String text;
    private int min;
    private int max;
    private int current;
    private Instruction instruction;

    public Panel(int id, int type, String text, int min, int max, Instruction instruction) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.min = min;
        this.max = max;
        this.instruction = instruction;
    }

    /**
     * Returns the id of the planel
     * @return the id of the panel
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the type of the panel
     * @return the type of panel
     */
    public int getType(){
        return type;
    }

    /**
     * Returns the text that is set on the panel
     * @return the text that is set on the panel
     */
    public String getText(){
        return text;
    }

    /**
     * the min value of the control For example if you have a slidercontrol between 0-5 it returns 0
     * @return the minimum value
     */
    public int getMin(){
        return min;
    }

    /**
     * the max value of the control For example if you have a slidercontrol between 0-5 it returns 5
     * @return the maximum value
     */
    public int getMax(){
        return max;
    }

    /**
    * @Returns the instruction on the panel
    */

    /**
     * @returns the current value of the control
     */
    public int getCurrent(){return current;}

    /**
     * Sets the current value of the control
     * @param value the value the control will be set to
     */
    public void setCurrent(int value){current = value;}

    public Instruction getInstruction() {
        return instruction;
    }
}
