package PanelControlTest;

import Game.Panel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gui.GameController;
import gui.panel.PanelHorizontalControl;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
/**
 * Created by Kevin on 19-10-2015.
 */
public class PanelHolizontalControlTest extends ApplicationTest{
    PanelHorizontalControl panelHorizontalControl;
        /**
         * @author Kevin Jetten
         * First sets the textfield with a value.
         * Next it will get the set text of the textfield and then compairs is to the original value.
         * If the values are the same the test will succeed.
         */
        @Test
        public void TestSetAndGetText() {

            String textValue = "test";
            panelHorizontalControl.setTextField(textValue);
            assertEquals(panelHorizontalControl.getText(), textValue);
        }

    /**
     * @autor Kevin Jetten
     * First sets the slider to a value.
     * Next it will get the current value of the slider.
     * Then it will compair the original set value to the slider value
     */
    @Test
    public void TestSetAndGetSliderValue(){
        double sliderValue = 3;
        panelHorizontalControl.setHorizontalSlider(sliderValue);
        assertTrue(panelHorizontalControl.getSlidervalue() == sliderValue);
    }
    /**
     * starts the stage and than creates a new panel in it.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        GameController gameController = new GameController();
        Panel mainPanel = new Panel(1,1,"TestControl",1,1);
         panelHorizontalControl = new PanelHorizontalControl(mainPanel, gameController);
    }
}
