package gui.panel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelButtonControl extends AbstractPanelControl {

    @FXML private TextField textField;

    public PanelButtonControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "panels/PanelButtonControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML protected void btnClick() {
        System.out.println("The button was clicked!");
    }
    public String getText(){
        return textField.getText();
    }
    public void setTextField(String txt){
        textField.setText(txt);
    }
}
