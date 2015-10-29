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
public class PanelVerticalControl extends AbstractPanelControl implements IPanel {
    @FXML private TextField textField;
    @FXML private Slider verticalSlider;

    public PanelVerticalControl(Panel panel, GameController gameController) {
        super(panel, gameController);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/panels/PanelVerticalControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public String getText(){
        return textField.getText();
    }
    public void setTextField(String txt){
        textField.setText(txt);
    }
    public void setVerticalSlider(double sliderValue){
        verticalSlider.setValue(sliderValue);
    }
    public void setMaxVerticalSlider(double maxValue) {verticalSlider.setMax(maxValue);}
    public double getSlidervalue(){
        return verticalSlider.getValue();
    }

    public void update() {

    }

    public void draw() {

    }

    public void remove() {

    }
}
