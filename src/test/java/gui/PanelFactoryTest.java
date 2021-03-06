package gui;

import Game.Panel;
import gui.panel.IPanel;
import gui.panel.PanelButtonControl;
import gui.panel.PanelHorizontalControl;
import gui.panel.PanelVerticalControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by david on 17-10-15.
 */
public class PanelFactoryTest {

    Panel panel;

    /**
     * Sets up the test class
     *
     * @throws Exception
     * @author David
     * @note removed null in panel init
     */
    @Before
    public void setUp() throws Exception {
        /*
        this.panel = new Panel(1, 1, "Red Wire", 0, 1);
        		//todo: de constructor is verandert
        */
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests getting panels from a factory
     * @author David
     * @note removed null from panel init
     * @throws Exception
     */

    /*
    @Test
    public void testGetPanel() throws Exception {

        GameController gameController = new GameController();

        // Test button
        IPanel ipanel = PanelFactory.getPanel(this.panel, gameController);

        boolean assignableFrom = ipanel instanceof PanelButtonControl;

        assertTrue("Panel was not from the correct class", assignableFrom);

        // Test horizontal slider

        Panel panel_horizontal = new Panel(1, 2, "slide", 1, 10);

        ipanel = PanelFactory.getPanel(panel_horizontal, gameController);

        assignableFrom = ipanel instanceof PanelHorizontalControl;

        assertTrue("Panel was not of the correct type", assignableFrom);

        // Test vertical

        Panel panel_vertical = new Panel(1, 3, "vertial", 1, 10);

        ipanel = PanelFactory.getPanel(panel_vertical, gameController);

        assignableFrom = ipanel instanceof PanelVerticalControl;

        assertTrue("Panel was not of the correct type", assignableFrom);

        // Test default

        Panel panel_default_to_button = new Panel(1, 4, "default", 0, 1);

        ipanel = PanelFactory.getPanel(panel_default_to_button, gameController);

        assignableFrom = ipanel instanceof PanelButtonControl;

        assertTrue("Panel was not of the correct type", assignableFrom);
    }
    		//todo: overal waar een panel wordt aangemaakt moeten de waarden worden verandert omdat de constructor anders is. De rest van de code hoort wel te werken
    */
}