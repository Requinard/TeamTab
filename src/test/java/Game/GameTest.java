package Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by david on 12-10-15.
 */
public class GameTest {

    Game game;
    Player player1;
    Player player2;
    Player player3;
    ArrayList<Panel> pp1;
    Panel pan1;
    Panel pan2;
    Panel pan3;
    Panel pan4;
    Panel pan5;
    Panel pan6;
    Panel pan7;
    Panel pan8;
    Panel pan9;
    Panel pan10;
    Panel pan11;
    Panel pan12;

    Instruction in1;
    Instruction in2;
    Team t1;
    int time;

    Instruction in3;
    Panel p3;

    String commando;
    String commando1;

    @Before
    public void setUp() throws Exception {
        game = new Game(null);

        pan1 = new Panel(1, 1, "a", 0, 1);
        pan2 = new Panel(2, 1, "b", 0, 1);
        in1 = new Instruction(pan1, "Click on", 1); //newvalue moet nog in de game logic afgehandeld worden
        in2 = new Instruction(pan2, "Click off", 0);
        pan1 = new Panel(1, 1, "a", 0, 1);
        pan2 = new Panel(2, 1, "b", 0, 1);
        pan3 = new Panel(3, 1, "b", 0, 1);
        pan4 = new Panel(4, 1, "b", 0, 1);
        pan5 = new Panel(5, 1, "b", 0, 1);
        pan6 = new Panel(6, 1, "b", 0, 1);
        pan7 = new Panel(7, 1, "b", 0, 1);
        pan8 = new Panel(8, 1, "b", 0, 1);
        pan9 = new Panel(9, 1, "b", 0, 1);
        pan10 = new Panel(10, 1, "b", 0, 1);
        pan11 = new Panel(11, 1, "b", 0, 1);
        pan12 = new Panel(12, 1, "b", 0, 1);

        in1.setPanel(pan1);
        in2.setPanel(pan2);
        pp1 = new ArrayList<Panel>();
        pp1.add(pan1);
        pp1.add(pan2);
        pp1.add(pan3);
        pp1.add(pan4);
        pp1.add(pan5);
        pp1.add(pan6);
        pp1.add(pan7);
        pp1.add(pan8);
        pp1.add(pan9);
        pp1.add(pan10);
        pp1.add(pan11);
        pp1.add(pan12);




        player1 = new Player("1.1.1.1", "Kaj", 0, pp1, in1, game, null); //team later toevoegen
        player2 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, game, null);
        player3 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, game, null);
        //t1 = new Team(1, 1, 1);
        time= 0;

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
        game.addPlayerToTeam(player1);
        game.startGame();
    }

    /**
     * Is called at the beginning of the game
     * Check if both teams have the same amount of players
     * @author Frank HArtman
     * @throws Exception
     */
    @Test
    public void testStartGame() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        Player currentPlayer = game.startGame();
        assertEquals(player1.getName(), currentPlayer.getName());
        assertEquals(player1.getGame(), currentPlayer.getGame());
        assertEquals(player1.getInstruction(), currentPlayer.getInstruction());
        assertEquals(player1.getIpAdress(), currentPlayer.getIpAdress());
        assertEquals(player1.getScore(), currentPlayer.getScore());
        assertEquals(player1.getTeam(), currentPlayer.getTeam());
        assertEquals("Not enough panels", 12, player1.getPanels().size());
        assertEquals("Not enough panels", 12, player2.getPanels().size());
    }

    /**
     * Call this method to start a new round
     * Every value in the game gets a reset
     * @throws Exception
     */
    @Test
    public void testNewRound() throws Exception {
        game.newRound();
        Game game2 = new Game(null);
        game2.newRound();

        //mogelijk nog uitgebreid
    }

    /**
     * return list of players from the winning team + there score
     * @author kaj
     * @author Frank Hartman
     * @throws Exception
     */
    @Test
    public void testEndGame() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);

        assertEquals("Gives the wrong score", "You lost!", game.endGame(game.team1).get(0));
        assertEquals("Gives the wrong score", "You won!", game.endGame(game.team2).get(0));
    }

    /**
     * When joining a lobby the joined player is set into the team with the least amount of players
     * @throws Exception
     */
    @Test
    public void testAddPlayerToTeam() throws Exception {
        game.addPlayerToTeam(player1);
        assertEquals("added to wrong team", game.team1.getPlayers().get(0), player1);
        game.addPlayerToTeam(player2);
        assertEquals("added to wrong team", game.team2.getPlayers().get(0), player2);
        Player p3 = new Player("2.2.2.2", "Frank", 3, new ArrayList<Panel>(), null, game, null);
        game.addPlayerToTeam(p3);
        assertEquals("added to wrong team", game.team1.getPlayers().get(1), p3);
    }

    /**
     * Changing the player to the other team
     * @throws Exception
     */
    @Test
    public  void testchangeTeam() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        game.changeTeam(player1);
        assertEquals("Player didn't change from team", game.team2, player1.getTeam());
        assertEquals("Wrong size team", 0, game.team1.getPlayers().size());
        assertEquals("Wrong size team", 2, game.team2.getPlayers().size());
        game.changeTeam(player2);
        assertEquals("added to the wrong team", player2, game.team1.getPlayers().get(0));
        assertEquals("added to the wrong team", player1, game.team2.getPlayers().get(0));
        assertEquals("Player changed from team", false,game.changeTeam(player3) );
    }

    /**
     * When a team reaches a certain winstreak the game checks if they should recieve bonus time
     * @throws Exception
     */
    @Test
    public void testAddTimeAndCheckInstruction() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        game.team1.setCorrectInstruction(2);
        assertEquals("gives incorrect time", 9, game.team1.getTime());

        //Create a panel that gets the right instruction so it can be checked
        Panel testPanel = player1.getInstruction().getPanel();
        int testPanelValue = player1.getInstruction().getValue();
        testPanel.setCurrent(testPanelValue);

        //Test correct instruction
        //game.checkInstruction(testPanel, player1);
        assertEquals("gives incorrect time", 9, game.team1.getTime());

        //Test if bonustime is added. Set time to 8
        game.team1.setTime(8);
        game.team1.setCorrectInstruction(2);

        //Create a panel that gets the right instruction so it can be checked
        Panel testPanel2 = player1.getInstruction().getPanel();
        int testPanelValue2 = player1.getInstruction().getValue();
        testPanel.setCurrent(testPanelValue2);

        //Test correct instruction
        //game.checkInstruction(testPanel2,player1);
        assertEquals("No bonustime added",9, game.team1.getTime());

        //Test addTime so it returns a false. Now it doesn't add bonustime
        game.team1.setTime(8);
        game.team1.setCorrectInstruction(1);

        //Create a panel that gets the right instruction so it can be checked
        Panel testPanel3 = player1.getInstruction().getPanel();
        int testPanelValue3 = player1.getInstruction().getValue();
        testPanel.setCurrent(testPanelValue3);

        //Test correct instruction
       // game.checkInstruction(testPanel3,player1);
        assertEquals("No bonustime added",8, game.team1.getTime());

        //Create a new panel which doesn't exist yet
        Panel testPanel4 = new Panel(51,1,"Self-Destruct",1,2);
        int testPanelValue4 = player1.getInstruction().getValue()+1;

        testPanel.setCurrent(testPanelValue4);

        //Test incorrect instruction with a wrong panel
        //boolean testWrongInstruction = game.checkInstruction(testPanel4,player1);
        //assertEquals("The instruction is correct",false , testWrongInstruction);

        //Create a panel that gets the wrong instruction so it can be checked
        Panel testPanel5 = player1.getInstruction().getPanel();
        int testPanelValue5 = player1.getInstruction().getValue()+1;

        testPanel.setCurrent(testPanelValue5);

        //Test incorrect instruction
        //boolean testWrongInstruction2 = game.checkInstruction(testPanel5,player1);
        //assertEquals("The instruction is correct",false , testWrongInstruction);



    }

    /**
     * When the team has less than 3 seconds it should lose a life
     * @throws Exception
     */
    @Test
    public void testSubtractLives() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        assertEquals("gives the incorrect lives", 3, game.team1.getLives());
        game.team1.setTime(3);
        game.subtractLives(game.team1);
        assertEquals("gives the incorrect lives", 2, game.team1.getLives());
        assertEquals("newRound not started", 9, game.team1.getTime());
        game.team1.setTime(3);
        game.team1.setLives(1);
        game.subtractLives(game.team1);
        assertEquals("EndGame not started", 3, game.team1.getTime());
    }

    /**
     * If the team has a certain winstreak the other them should get less time for there upcomming instructions
     * @throws Exception
     */
    @Test
    public void testSubtractTime() throws Exception {
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        game.team1.setCorrectInstruction(5);
        assertEquals("gives incorrect time", 9, game.team2.getTime());
        game.subtractTime(game.team1);
        assertEquals("gives incorrect time", 9, game.team1.getTime());
        assertEquals("gives incorrect time", 8, game.team2.getTime());
    }

    /**
     * Is nog niet gemaakt omdat deze methode nog moet worden aangepast
     * @throws Exception
     */

    @Test
    public void testRemovePlayer() throws Exception {

    }



    /**
     * Test reset values from both teams
     * @throws Exception
     */
    @Test
    public void testReset(){
        game.addPlayerToTeam(player1);
        game.addPlayerToTeam(player2);
        game.subtractLives(game.team1);
        game.subtractTime(game.team1);
        game.team1.setCorrectInstruction(2);
        game.newRound();
        assertEquals("Time is not 9", 9, game.team1.getTime());
        assertEquals("Amount of correct instructions is not 0", 0, game.team1.getCorrectInstruction());
        assertEquals("Amount of playerpanels is not 0", 0, game.team1.getPlayerPanels().size());
        Team testTeam1 = game.team1;
        Team testTeam2 = game.team2;
        testTeam1.setLives(0);
        testTeam2.setLives(0);
        game.subtractLives(testTeam1);
        game.subtractLives(testTeam2);
        game.newRound();

    }

    /**
     *
     * De speler die een nieuwe instructie moet krijgen
     * @author Frank Hartman
     * @throws Exception
     */
    @Test
    public void testGivePlayerInstructions() throws Exception {
        // Make sure the player has no panels
        player1.setPanels(new ArrayList<Panel>());
        player2.setPanels(new ArrayList<Panel>());
        // Add a player to the team
        assertTrue(game.addPlayerToTeam(player1));
        assertTrue(game.addPlayerToTeam(player2));
        assertEquals("The player should not have panels at this stage", 0, player1.getPanels().size());
        // Load the panels
        game.loadPanels();
        // A new round gives panels to the players
        game.newRound();
        assertEquals("The player does not have the right amount of panels", 12, player1.getPanels().size());
        assertEquals("The player does not have the right amount of panels", 12, player2.getPanels().size());
    }

    @Test
    public void testLoadPanels() throws Exception {
        Game game = new Game(null);

        boolean b = game.loadPanels();

        assertTrue("No panels were loaded", b);

    }

    @Test
    public void testCheckInstruction(){
        game.addPlayerToTeam(player1);
        game.newRound();
        // Update the panel
        pan1.setCurrent(1);
        commando = in1.getCommando();
        //game.checkInstruction(pan1, player1);
        commando1 = in1.getCommando();
        assertEquals("No correct instructions added", 1, player1.getScore());
        assertThat("Krijgt geen nieuwe instructies", is(not(commando)));
        //assertNotEquals("Krijgt geen nieuwe instructies", not(commando), commando1);
    }
}