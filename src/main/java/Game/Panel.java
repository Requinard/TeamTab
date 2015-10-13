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

    public Panel(int Id, int Type, String Text, int Min, int Max) {
        this.id = Id;
        this.type = Type;
        this.text = Text;
        this.min = Min;
        this.max = Max;
    }

    public int getId() {
        return id;
    }

    public int getType(){
        return type;
    }

    public String getText(){
        return text;
    }
    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
    }

}
