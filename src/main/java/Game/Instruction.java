package Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Qun on 12-10-2015.
 */
public class Instruction {
    private Panel panel;
    private String commando;
    private int value;

    /**
     * @param panel         Panel of the current instruction
     * @param commando      Text what the player must do with the panel
     * @param value         Value the panel must have
     */
    public Instruction(Panel panel, String commando, int value) {
        this.panel = panel;
        this.commando = commando;
        this.value = value;
    }

    /**
     * Gets the panel from the player
     * @return the panel
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * sets panel for the player
     * @param panel
     */
    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    /**
     * Returns the command the player has to execute
     * @return the commando
     */
    public String getCommando() {
        return commando;
    }


    /**
     * Returns the value of the instruction
     * @return instruction value
     */
    public int getValue() {return value;}


    /**
     * Returns the commando the player has to execute
     * @author Frank Hartman
     * @return the tostring commando
     */
    @Override
    public String toString() {
        if (panel != null)
            return commando + " : " + panel.getText();
        else
            return commando;
    }

    /**
     * Change the current instruction to a new instruction
     * @param playerPanels The panels of the player that should get a new instruction
     */
    public boolean createNewInstruction(ArrayList<Panel> playerPanels, Instruction playerInstruction) {
        Random random = new Random();
        try {
            //Gets new panel which is not the current panel
            ArrayList<Panel> tempPanels = playerPanels;
            tempPanels.remove(playerInstruction.getPanel());
            this.panel = tempPanels.get(random.nextInt(tempPanels.size()));


            this.commando = "Change to";
            //Get new value which is not the current value for sliders
            this.value = random.nextInt((panel.getMax() - panel.getMin()) + 1) + panel.getMin();
            if(panel.getType() == PanelTypeEnum.HorizontalSlider ||  panel.getType() == PanelTypeEnum.VerticalSlider){
                if(value == playerInstruction.getPanel().getCurrent()){
                    if(value == panel.getMax()){
                        this.value = this.value - 1;
                    }
                    else
                    {
                        this.value = this.value + 1;
                    }
                }
                if(playerInstruction.getPanel().getCurrent() == 0){
                    if(value == panel.getMin()){
                        this.value = this.value + 1;
                    }
                }
            }
            // EERSTE ITERATIE

            System.out.println("Player gets a new commando");
            System.out.println(panel.getText() + ": " + commando + ": " + value);
            return true;
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
