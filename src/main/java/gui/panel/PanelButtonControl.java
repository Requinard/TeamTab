package gui.panel;

import Game.Panel;
import gui.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Kevin on 12-10-2015.
 */
public class PanelButtonControl extends AbstractPanelControl implements IPanel {

    @FXML
    private TextField textField;
    @FXML
    private Button btnPanelClick;
    private String panelName = "";

    public PanelButtonControl(Panel panel, GameController gameController) {
        super(panel, gameController);
        panelName = panel.getText();
        textField = new TextField();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/panels/PanelButtonControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            btnPanelClick.setText(panel.getText());
            btnPanelClick.setMinWidth(250);
            btnPanelClick.setMaxWidth(250);
            //textField.setText(panel.getText());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @FXML
    public void btnPanelClick() {
        System.out.println(panelName + " was clicked!");
        Panel panel = getPanel();
        panel.setCurrent(1);
        gameController.checkInstruction(panel, 1);
    }

    public void update() {

    }

    public void draw() {

    }

    public void remove() {

    }
}
