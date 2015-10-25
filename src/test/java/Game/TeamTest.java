package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class TeamTest {

    Game g;
    Player p1;
    Player p2;
    Player p3;
    ArrayList<Panel> pp1;
    Instruction in1;
    Instruction in2;
    Instruction in3;
    Team t;
    Panel pan1;
    Panel pan2;
    Team TeamCorrect;

    @Before
    public void setUp() throws Exception {
        g = new Game();
        t = new Team(1,3,0,"TestTeam");
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        in3 = new Instruction(pan2, "Set to 50", 1);
        pp1 = new ArrayList<Panel>();
        p1 = new Player("111.11.11.11", "testPersoon", 2, pp1, in1, g, null);
        p2 = new Player("222.22.22.22", "testPersoon2", 3, pp1, in2, g, null);
        p3 = new Player("333.33.33.33", "testPersoon3", 3, pp1, in3, g, null);
        //TeamCorrect = new Team(1, 3, 0);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testAddPlayerToTeam() throws Exception {
        assertEquals("Player isn't added", true, t.addPlayerToTeam(p1));
        t.addPlayerToTeam(p1);
        assertEquals("Player is added to the list ", false, t.addPlayerToTeam(p1));
    }

    @Test
    public void testRemovePlayer() {
        t.addPlayerToTeam(p1);
        assertEquals("Player is not removed", true, t.removePlayer(p1));
        assertEquals("Player is removed", false, t.removePlayer(p1));
    }

    @Test
    public void testResetTeam() {
        t.resetTeam();
        assertEquals("Team hasn't been reset", true, t.resetTeam());
        assertEquals("Time has not been reset", 9, t.getTime());
        assertEquals("Lives have not been reset", 3, t.getLives());
        assertEquals("Amount of correct instructions have not been reset ", 0, t.getCorrectInstruction());
    }

    @Test
    public void testSortedPlayerByScore() {
        t.addPlayerToTeam(p1);  // Score 2
        t.addPlayerToTeam(p2);  // Score 3
        t.addPlayerToTeam(p3);  // Score 3
        t.sortedPlayerByScore();
        assertEquals("Wrong player is first", p2, t.getPlayers().get(0));
        assertEquals("Wrong player is second", p3, t.getPlayers().get(1));
        assertEquals("Wrong player is third", p1, t.getPlayers().get(2));
    }


    @Test
    public void testIsPlayerInTeam() {
        t.addPlayerToTeam(p1);
        assertEquals("Player is not in team", true, t.isPlayerInTeam(p1));
        assertEquals("Player is in team", false, t.isPlayerInTeam(p2));
    }

    @Test
    public void testRefreshPlayerPanels() {
        //Internet advices not to test private methodes several times
        //Have to ask the teacher about this first before testing it
    }
}

