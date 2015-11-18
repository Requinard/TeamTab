package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 11/16/2015.
 */
public class TeamTest {
    Team team;

    Player player1;
    Player player2;

    List<Panel> listPanels;
    @Before
    public void setUp() throws Exception {
        team = new Team("Appelmuppets");
        player1 = new Player("Frank", "0.0.0.0");
        player2 = new Player("Kevin", "1.1.1.1");
        listPanels = new ArrayList<>();

    }

    @After
    public void tearDown() throws Exception {

    }

   @Test
    public void testGetPlayers() throws Exception {

    }

    @Test
    public void testGetPanels() throws Exception {
   }

   @Test
    public void testGetName() throws Exception {

   }

    @Test
    public void testGetActiveInstructions() throws Exception {

    }

    @Test
    public void testGetLives() throws Exception {
   }

   @Test
    public void testGetTime() throws Exception {
   }

    @Test
    public void testGetScore() throws Exception {
   }

    /**
     * Checks if the list is empty or if the player is already in the team
     * if there is a team and the person is not in it, the player is added to the team
     * @throws Exception
     */
    @Test
    public void testAddPlayer() throws Exception {
        // Add player to a team
        team.addPlayer(player1);
        // Add player to a team
        Assert.assertEquals("Player is not added to team", 1, team.getPlayers().size());
        // Add the same player again
        Assert.assertEquals("Player is added to team", null, team.addPlayer(player1));
        // Add another unique player to the team
        team.addPlayer(player2);
        Assert.assertEquals("Player is not added to team", 2, team.getPlayers().size());
    }

    @Test
    public void testRemovePlayer() throws Exception {
        // Remove an existing player from a team
        team.addPlayer(player1);
        Assert.assertEquals("Player has not been removed",player1,team.removePlayer(player1));
        // Remove a non existing player from a team
        Assert.assertEquals("Player is removed from team", null, team.removePlayer(player2));
    }

    /**
     * Author Qun
     * This method generates panels for the team based on the amount of players
     * It can't contain the same panels.
     * @throws Exception
     */
  @Test
    public void testGeneratePanels() throws Exception {
      team.addPlayer(player1);
      // Team has exactly enough panels to distribute over the players
      for (int i = 0; i < 12; i++) {
          Panel panel = new Panel(i, 1, 5, "panel", PanelTypeEnum.HorizontalSlider);
          listPanels.add(panel);
      }
      Assert.assertEquals("Panels are distributed over the teammembers", 0, team.generatePanels(listPanels).size());
      // Team has too much panels to distribute over the players
      for (int i = 0; i < 14; i++) {
          Panel panel = new Panel(i, 1, 5, "panel", PanelTypeEnum.HorizontalSlider);
          listPanels.add(panel);
      }
      Assert.assertEquals("Panels are distributed over the teammembers", 2, team.generatePanels(listPanels).size());

  }

    /**
     * Author Frank Hartman
     */
    @Test
    public void testChangeLives() throws Exception {
        int currentLives = team.getLives();
        // Check if the amount of starting lives is 3
        Assert.assertEquals("The starting amount of lives is not 3", currentLives, 3);

        // Decrease the lives of a team
        team.changeLives(-1);
        currentLives = team.getLives();
        Assert.assertEquals("The current amount of lives has not been decreased", currentLives, 2);

        // Increase the amount of lives of a team
        team.changeLives(2);
        currentLives = team.getLives();
        Assert.assertEquals("The current amount of lives has not been increased", currentLives, 4);
    }


    /**
     * Author Frank Hartman
     *
     * @throws Exception
     */
    @Test
    public void testChangeTime() throws Exception {
        int currentTime = team.getTime();
        // Check if the amount of time at the start is 9
        Assert.assertEquals("The starting amount of time is not 9", 9, currentTime);

        // Decrease the time of a team
        team.changeTime(-3);
        currentTime = team.getTime();
        Assert.assertEquals("The current amount of time has not been decreased", 6, currentTime);

        // Increase the time of a team
        team.changeTime(2);
        currentTime = team.getTime();
        Assert.assertEquals("The current amount of time has not been increased", 8, currentTime);

    }

    @Test
    public void testHasChanged() throws Exception {

    }

    @Test
    public void testIsAlive() throws Exception {
    }

    @Test
    public void testReset() throws Exception {
        // Soft resetting
        team.changeTime(-4);
        team.reset(false);
        Assert.assertEquals("The amount of time has not been reset to 9", 9, team.getTime());

        // Hard reset
        team.changeTime(-2);
        team.changeLives(-1);
        team.reset(true);
        Assert.assertEquals("The amount of time has not been reset to 9", 9, team.getTime());
        Assert.assertEquals("The amount of lives has not been reset to 3", 3, team.getLives());

    }

    @Test
    public void testCorrectInstructionPreformed() throws Exception {
        Panel panel = new Panel(1, 1, 5, "test", PanelTypeEnum.HorizontalSlider);
        Instruction instruction = new Instruction(panel, 1);
        //removal of instruction can't be test because it still can't be added
        //Assert.assertTrue("Instruction has never in the active instructions list", team.getActiveInstructions().contains(instruction));
        //team.correctInstructionPreformed(instruction);
        //Assert.assertTrue("Instruction has not been removed from active instructions list", !team.getActiveInstructions().contains(instruction));

        //tests if the score is added
        //Assert.assertEquals("score has not been added", 1, team.getScore());
    }
}