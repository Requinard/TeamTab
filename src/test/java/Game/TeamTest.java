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
    Team team;
    Panel pan1;
    Panel pan2;
    Team TeamCorrect;

    @Before
    public void setUp() throws Exception {
        g = new Game();
        team = new Team(9,3);
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        in3 = new Instruction(pan2, "Set to 50", 1);
        pp1 = new ArrayList<Panel>();
        pp1.add(pan1);
        p1 = new Player("111.11.11.11", "testPersoon", 2, pp1, in1, g, null);
        p2 = new Player("222.22.22.22", "testPersoon2", 3, pp1, in2, g, null);
        p3 = new Player("333.33.33.33", "testPersoon3", 3, pp1, in3, g, null);
        //TeamCorrect = new Team(1, 3, 0);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * @Author Qun
     * Test AddPlayerToTeam()
     * Tests Add a player to the team
     * Tests Add a existing teamplayer to team
     * @throws Exception
     */
    @Test public void testAddPlayerToTeam() throws Exception {
        assertEquals("Player isn't added", true, team.addPlayerToTeam(p1));
        team.addPlayerToTeam(p1);
        assertEquals("Player is added to the list ", false, team.addPlayerToTeam(p1));
    }

    /**
     * @Author Qun
     * Test RemovePlayer
     * Test if player is removed
     * Test if a player who is not in the team is removed
     * @throws Exception
     */
     @Test
    public void testRemovePlayer() throws Exception {
        team.addPlayerToTeam(p1);
        assertEquals("Player is not removed", true, team.removePlayer(p1));
        assertEquals("Player is removed", false, team.removePlayer(p1));
    }

    /**
     * @Author Qun
     * Test ResetTeam
     * Test if the team is totally reset
     * Test Time = 9, Lives = 3, CorrectInstructions = 0
     */
    @Test
    public void testResetTeam() {
        team.resetTeam();
        assertEquals("Team hasn't been reset", true, team.resetTeam());
        assertEquals("Time has not been reset", 9, team.getTime());
        assertEquals("Lives have not been reset", 3, team.getLives());
        assertEquals("Amount of correct instructions have not been reset ", 0, team.getCorrectInstruction());
    }

    /**
     * @Author Qun
     * Test SortedPlayerByScore
     * Adds 3 testperson
     * 2 persons with the samen score
     * 1 person with a lower score
     * Check if the first person with same score is set first
     * Check if lowest score is set at the last position
     */
    @Test
    public void testSortedPlayerByScore() {
        team.addPlayerToTeam(p1);  // Score 2
        team.addPlayerToTeam(p2);  // Score 3
        team.addPlayerToTeam(p3);  // Score 3
        team.sortedPlayerByScore();
        assertEquals("Wrong player is first", p2, team.getPlayers().get(0));
        assertEquals("Wrong player is second", p3, team.getPlayers().get(1));
        assertEquals("Wrong player is third", p1, team.getPlayers().get(2));
    }

    /**
     *@Author Qun
     * Test IsPlayerInTeam()
     * Add a player to team to see if it's in the team
     * And check if someone is in the team when
     * it's not added to the team
     */
    @Test
    public void testIsPlayerInTeam() {
        team.addPlayerToTeam(p1);
        assertEquals("Player is not in team", true, team.isPlayerInTeam(p1));
        assertEquals("Player is in team", false, team.isPlayerInTeam(p2));
    }


    /**
     * @Author Qun
     * Test the private void refreshPlayerPanels()
     * This is used in addPlayerToTeam and removePlayer
     * Test if this method adds a panel and removes a panel
     */
    @Test
    public void testRefreshPlayerPanels() {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        int test = g.team1.getPlayerPanels().size();
        assertEquals("Panel is not added for the new player team1", 1, test);
        assertEquals("Panel is not added for the new player team2", 1, g.team2.getPlayerPanels().size());
        g.team1.removePlayer(p1);
        assertEquals("Panel is not deleted after remove", 0, g.team1.getPlayerPanels().size());
    }

    /**
     * @author Frank Hartman
     */
    @Test
    public void testAddTeamTime() {
        team.setTime(8);
        // Not allowed to add more time than the maximum time
        assertFalse(team.addTeamTime(10, 9));

        assertTrue(team.addTeamTime(1, 9));
        assertEquals(9, team.getTime());
        // Make sure the time is not more than the maximum amount of time
        assertTrue(team.addTeamTime(2,9));
        assertEquals(9, team.getTime());



    }
}

