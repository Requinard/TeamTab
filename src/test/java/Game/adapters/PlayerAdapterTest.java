package Game.adapters;

import Game.Player;
import Game.Team;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 12/9/2015.
 */
public class PlayerAdapterTest extends TestCase {
    Player firstPlayer;
    Player secondPlayer;

    public void setUp(){
        firstPlayer =  new Player("p1", "localhost");
        secondPlayer = new Player("p2", "localhost");
    }
    public void testToString() throws Exception {

    }

    public void testToObjects() throws Exception {

    }

    // Tests for multiple
    @Test
    public void testToString1() throws Exception {
        List<Player> playerList = new ArrayList<>();

        playerList.add(firstPlayer);
        playerList.add(secondPlayer);

        String string = PlayerAdapter.toString(playerList);

        assertNotNull(string);
        assertTrue(string.length() > 0);

        // Now we add a team
        Team team = new Team("test");

        team.addPlayer(firstPlayer);
        team.addPlayer(secondPlayer);

        string  = PlayerAdapter.toString(playerList);

        assertNotNull(string);


    }

    public void testToObject() throws Exception {

    }
}