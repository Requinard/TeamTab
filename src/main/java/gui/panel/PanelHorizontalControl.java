package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelHorizontalControl extends AbstractPanelControl implements IPanel {
    @FXML
    private TextField textField;
    @FXML
    private Slider horizontalSlider;

    private String sliderName = "";

    public PanelHorizontalControl(Panel panel, GameController gameController) {
        super(panel, gameController);
        sliderName = panel.getText();
        textField = new TextField();
        horizontalSlider = new Slider(0,0,0);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/panels/PanelHorizontalControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            textField.setText(panel.getText());
            if(panel.getMin() >= 2){
                horizontalSlider.setMin(panel.getMin());
                horizontalSlider.setMax(panel.getMax());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML public void sliderValueChanged() {
        System.out.println(sliderName + " " + getSliderValue());
        Panel panel = getPanel();
        panel.setCurrent(1);
        gameController.checkInstruction(panel);
    }

    private Double getSliderValue(){
        return horizontalSlider.getValue();
    }



//    public String getText() {
//        return textField.getText();
//    }

    //public void setTextField(String txt){
    //    textField.setText(txt);
    // }
    //public void setHorizontalSlider(double sliderValue){
    //    horizontalSlider.setValue(sliderValue);
    // }
    //public void setMaxHorizontalSlider(double maxValue){horizontalSlider.setMax(maxValue);}
    public double getSlidervalue() {
        return horizontalSlider.getValue();
    }

    public void update() {

    }

    public void draw() {

    }

    public void remove() {

    }
}
