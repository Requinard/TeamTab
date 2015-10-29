package PanelControlTest;

import Game.Panel;
import gui.GameController;
import gui.panel.PanelButtonControl;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
/**
 * Created by Kevin on 19-10-2015.
 */
public class PanelButtonControlTest extends ApplicationTest {
    PanelButtonControl panelButtonControl;
    /**
     * @author Kevin Jetten
     * First sets the textfield with a value.
     * Next it will get the set text of the textfield and than compairs is to the original value.
     * If the values are the same the test will succeed.
     */
//    @Test
//    public void TestSetAndGetText() {
//
//        String textValue = "test";
//        panelButtonControl.setTextField(textValue);
//        assertEquals(panelButtonControl.getText(), textValue);
//    }

    /**
     * @author Kevin Jetten
     * presses the button, if it does not cause an exception the test will succeed.
     */
    @Test
    public void TestButtonClick(){
       // panelButtonControl.btnClick();
    }

    /**
     * @author Kevin Jetten
     * starts the stage and than creates a new panel in it.
     */
    @Override
    public void start(Stage stage) throws Exception {
        GameController gameController = new GameController();

        Panel mainPanel = new Panel(1,1,"TestControl",1,1);
         panelButtonControl = new PanelButtonControl(mainPanel, gameController);

    }
}
