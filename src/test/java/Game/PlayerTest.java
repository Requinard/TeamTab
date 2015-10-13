package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class PlayerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetPanel() throws Exception {
        Panel testPanel;
        ArrayList<Panel> Panels = new ArrayList<Panel>();
        for (int i = 0; i < 3 ; i++) {
            Panels.add(new Panel());
        }
        Random r = new Random();
        int Random = r.nextInt(panels.size());
        testPanel =  Panels.get(Random);
        for(Panel p : Panels)
        {
            if(p == testPanel)
            {
                Assert.assertEquals(Random, Panels.indexOf(p));
            }
        }


    }
}