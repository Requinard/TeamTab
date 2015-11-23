package Game.adapters;

import Game.HostGame;
import Game.Player;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by David on 11/18/2015.
 */
public class JsonAdapterTest {

    @Test
    public void testToString() throws Exception {
        Player p = new Player("test", "test");

        System.out.println(JsonAdapter.toString(p));

        HostGame g = new HostGame();

        System.out.println(JsonAdapter.toString(g.getPanels(), ArrayList.class));
    }
}