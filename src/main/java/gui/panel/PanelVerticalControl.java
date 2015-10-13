package gui.panel;

import com.sun.deploy.panel.TextFieldProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelVerticalControl extends AbstractPanelControl {
    @FXML private TextField textField;
    @FXML private Slider verticalSlider;

    public PanelVerticalControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "panels/PanelVerticalControl.fxml"));
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
    public double getSlidervalue(){
        return verticalSlider.getValue();
    }
}
