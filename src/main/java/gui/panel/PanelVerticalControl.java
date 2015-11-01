package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;

import java.io.IOException;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelVerticalControl extends AbstractPanelControl implements IPanel {
    @FXML
    private TextField textField;
    @FXML
    private Slider verticalSlider;
    private String sliderName;

    public PanelVerticalControl(Panel panel, GameController gameController) {
        super(panel, gameController);

        verticalSlider = new Slider(0, 0, 0);
        sliderName = panel.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/panels/PanelVerticalControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            textField.setText(panel.getText());
            verticalSlider.setMin(panel.getMin());
            verticalSlider.setMax(panel.getMax());
            verticalSlider.setEffect(new ColorAdjust(0,0,1,1));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void sliderValueChanged() {
        System.out.println(sliderName + " " + getSliderValue().intValue());
        Panel panel = getPanel();
        panel.setCurrent(getSliderValue().intValue());
        gameController.checkInstruction(panel, getSliderValue().intValue());
    }

    private Double getSliderValue() {
        return verticalSlider.getValue();
    }


    public void update() {

    }

    public void draw() {

    }

    public void remove() {

    }
}
