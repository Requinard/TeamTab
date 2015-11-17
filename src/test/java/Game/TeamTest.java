package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David on 11/16/2015.
 */
public class TeamTest {
    Team team;

    @Before
    public void setUp() throws Exception {
        team = new Team("Appelmuppets");
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
    public void testAddPlayer() throws Exception {

    }

    @Test
    public void testRemovePlayer() throws Exception {

    }

    @Test
    public void testGeneratePanels() throws Exception {

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