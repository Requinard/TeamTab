package gui.panel;

import gui.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;

import java.io.IOException;
import Game.*;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelVerticalControl extends AbstractPanelControl implements IPanel {
    @FXML
    private TextField textField;
    @FXML
    private Slider verticalSlider;
    private String sliderName;

    /**
     * Create the vertical slider with values from panel
     * @param panel
     * @param gameController
     */
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
            verticalSlider.setMin(panel.getMinimumValue());
            verticalSlider.setMax(panel.getMaximumValue());
            verticalSlider.setEffect(new ColorAdjust(0,0,1,1));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * When the value of the slider changes
     */
    @FXML
    public void sliderValueChanged() {
        System.out.println(sliderName + " " + getSliderValue().intValue());
        Panel panel = getPanel();
        gameController.checkInstruction(panel, getSliderValue().intValue());
    }

    /**
     * get slider value
     * @return value of slider
     */
    private Double getSliderValue() {
        return verticalSlider.getValue();
    }


    /**
     * {@inheritDoc}
     */
    public void update() {

    }

    /**
     * {@inheritDoc}
     */
    public void draw() {

    }

    /**
     * {@inheritDoc}
     */
    public void remove() {

    }
}
