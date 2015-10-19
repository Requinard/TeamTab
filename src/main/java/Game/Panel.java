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

    public Panel(int id, int type, String text, int min, int max) {
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
    public int getType(){
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
