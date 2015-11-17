package Game;

import Game.Panel;
import Game.PanelTypeEnum;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David on 11/16/2015.
 */
public class PanelTest {
    Panel panel;
    PanelTypeEnum panelTypeEnum;
    String text;
    int id;
    int min;
    int max;
    @Before
    public void setUp() throws Exception {
        panelTypeEnum = PanelTypeEnum.values()[1];
        text = "Explosion";
        id = 1;
        min = 0;
        max = 1;

        panel = new Panel(id, min, max, text, panelTypeEnum);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPanelType() throws Exception {
        Assert.assertEquals(panelTypeEnum, panel.getPanelType());
    }

    @Test
    public void testGetMinimumValue() throws Exception {
        Assert.assertEquals(min, panel.getMinimumValue());
    }

    @Test
    public void testGetMaximumValue() throws Exception {
        Assert.assertEquals(max, panel.getMaximumValue());
    }

    @Test
    public void testIsText() throws Exception {
        Assert.assertEquals(text, panel.getText());
    }
}