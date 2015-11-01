package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;

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
        horizontalSlider = new Slider(0, 0, 0);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/panels/PanelHorizontalControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            textField.setText(panel.getText());
            horizontalSlider.setMin(panel.getMin());
            horizontalSlider.setMax(panel.getMax());
            horizontalSlider.setEffect(new ColorAdjust(0,0,1,1));
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
        return horizontalSlider.getValue();
    }

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
