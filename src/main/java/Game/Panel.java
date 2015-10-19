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
    * @Returns the id of the Panel
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
    * @Return the text that it set on the Panel
    */
    public String getText(){
        return text;
    }

    /**
    * @Returns the min value of the control
    * For example if you have a slidercontrol
    * between 0-5 it returns 0
    */
    public int getMin(){
        return min;
    }

    /**
    * @Returns the max value of the control
    * For example if you have a slidercontrol
    * between 0-5 it returns 5
    */
    public int getMax(){
        return max;
    }

    /**
    * @Returns the instruction on the panel
    */
}
