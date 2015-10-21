package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by david on 12-10-15.
 */
public class GameTest {

    Game g;
    Player p1;
    Player p2;
    ArrayList<Panel> pp1;
    Panel pan1;
    Panel pan2;
    Instruction in1;
    Instruction in2;
    Team t1;
    int time;
    Panel pan3;
    Instruction in3;
    Panel p3;

    @Before
    public void setUp() throws Exception {
        g  = new Game();
        in1 = new Instruction(pan1, "Click on", 0); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 1);
        pan1 = new Panel(1, 1, "a", 0, 1, in1);
        pan2 = new Panel(2, 1, "b", 0, 1, in2);
        in1.setPanel(pan1);
        in2.setPanel(pan2);
        pp1 = new ArrayList<Panel>();
        pp1.add(pan1);
        pp1.add(pan2);
        p1 = new Player("1.1.1.1", "Kaj", 0, pp1, in1, g, null); //team later toevoegen
        p2 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, g, null);
        //t1 = new Team(1, 1, 1);
        time= 0;
        p3 = new Panel(3, 1, "Press it down", 0, 1, null);
    }

    @After
    public void tearDown() throws Exception {

    }
    /**
     *
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @throws Exception
     */
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStartGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.startGame();
    }

    /**
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @throws Exception
     */
    @Test
    public void testStartGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.startGame();
        assertEquals("not all player panels added", 2, g.team1.getPlayerPanels().size());
        assertEquals("not all player panels added", 0, g.team2.getPlayerPanels().size());
    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     * @throws Exception
     */
    @Test
    public void testNewRound() throws Exception {
        g.newRound();
        //mogelijk nog uitgebreid
    }

    /**
     * return list of players from the winning team + there score
     * @throws Exception
     */
    @Test
    public void testEndGame() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        assertEquals("Gives the wrong score", "Kaj: 0", g.endGame(g.team1).get(0));
        assertEquals("Gives the wrong score", "Frank: 3", g.endGame(g.team2).get(0));

        pan3 = new Panel(2, 1, "c", 0, 1, in3);
        in3 = new Instruction(pan3, "Click on", 0);
        ArrayList<Panel> pal = new ArrayList<Panel>();
        pal.add(pan3);
        Player p3 = new Player("1.2.3.4","Bas",5,pal, null, g,null);
        g.addPlayerToTeam(p3);

        assertEquals("Wrong order", "Bas: 5", g.endGame(g.team1).get(0));
        assertEquals("Not everyone added", 2, g.endGame(g.team1).size());
    }

    /**
     * When joining a lobby the joined player is set into the team with the least amount of players
     * @throws Exception
     */
    @Test
    public void testAddPlayerToTeam() throws Exception {
        g.addPlayerToTeam(p1);
        assertEquals("added to wrong team", g.team1.getPlayers().get(0), p1);
        g.addPlayerToTeam(p2);
        assertEquals("added to wrong team", g.team2.getPlayers().get(0), p2);
        Player p3 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, g, null);
        g.addPlayerToTeam(p3);
        assertEquals("added to wrong team", g.team1.getPlayers().get(1), p3);
    }

    /**
     * Changing the player to the other team
     * @throws Exception
     */
    @Test
    public  void testchangeTeam() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.changeTeam(p1);
        assertEquals("Wrong size team", 0, g.team1.getPlayers().size());
        assertEquals("Wrong size team", 2, g.team2.getPlayers().size());
        g.changeTeam(p2);
        assertEquals("added to the wrong team", p2, g.team1.getPlayers().get(0));
        assertEquals("added to the wrong team", p1, g.team2.getPlayers().get(0));
    }

    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     * @throws Exception
     */
    @Test
    public void testAddTime() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.team1.setCorrectInstruction(2);
        assertEquals("gives incorrect time", 9, g.team1.getTime());
        g.checkInstruction(p1.getPanels().get(0), p1);
        assertEquals("gives incorrect time", 10, g.team1.getTime());
    }

    /**
     * When the team has less than 3 seconds it should lose a life
     * @throws Exception
     */
    @Test
    public void testSubtractLives() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        assertEquals("gives the incorrect lives", 3, g.team1.getLives());
        g.team1.setTime(3);
        g.subtractLives(g.team1);
        assertEquals("gives the incorrect lives", 2, g.team1.getLives());
        assertEquals("newRound not started", 9, g.team1.getTime());
        g.team1.setTime(3);
        g.team1.setLives(1);
        g.subtractLives(g.team1);
        assertEquals("EndGame not started", 3, g.team1.getTime());
    }

    /**
     * If the team has a certain winstreak the other them should get less time for there upcomming instructions
     * @throws Exception
     */
    @Test
    public void testSubtractTime() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.team1.setCorrectInstruction(5);
        assertEquals("gives incorrect time", 9, g.team2.getTime());
        g.subtractTime(g.team1);
        assertEquals("gives incorrect time", 9, g.team1.getTime());
        assertEquals("gives incorrect time", 8, g.team2.getTime());
    }

    /**
     * Is nog niet gemaakt omdat deze methode nog moet worden aangepast
     * @throws Exception
     */
    //Vanaf hier doet Frank
    @Test
    public void testRemovePlayer() throws Exception {
        
    }

    /**
     * Deze code moet nog worden aangepast
     * @throws Exception
     */
    @Test
    public void testAddCorrectInstruction() throws Exception {
        g.addPlayerToTeam(p1);
        g.addPlayerToTeam(p2);
        g.checkInstruction(p1.getPanels().get(0), p1);

        assertEquals("Amount of correct instructions is not 1", 1, g.team1.getCorrectInstruction());
        assertEquals("Amount of time isn't raised by 1",9 , g.team1.getTime());
        assertNotEquals("Player didn't get a new panel", pan1 , p1.getPanels().get(1));
        g.checkInstruction(p1.getPanels().get(1), p1);
        assertEquals("Amount of correct instructions is not 0", 0, g.team1.getCorrectInstruction());
    }


    /**
     * Test reset values from both teams
     * @throws Exception
     */
    @Test
    public void testReset() throws Exception {
        g.addPlayerToTeam(p1);
        g.team1.resetTeam();
        assertEquals("Time is not 9", 9, g.team1.getTime());
        assertEquals("Amount of correct instructions is not 0", 0, g.team1.getCorrectInstruction());
        assertEquals("Amount of playerpanels is not 0", 0, g.team1.getPlayerPanels().size());
        assertEquals("Amount of players is not 0", 0, g.team1.getPlayers().size());
    }

    /**
     *
     * De speler die een nieuwe instructie moet krijgen
     * @throws Exception
     */
    @Test
    public void testGivePlayerInstructions() throws Exception {
        //Internet advices not to test private methodes several times
        //Have to ask the teacher about this first before testing it
    }

}