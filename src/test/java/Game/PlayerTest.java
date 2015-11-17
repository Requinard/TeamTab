package Game;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Assert;

/**
 * Created by David on 11/16/2015.
 */
public class PlayerTest {

    Player player;
    Panel panel1;
    List<Panel> listPanels;
    Team team1;
    Instruction instruction1;

    @Before
    public void setUp() throws Exception {
        player = new Player("Frank Hartman", "127.0.0.1");
        team1 = new Team("team1");
        listPanels = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetUsername() throws Exception {
        Assert.assertEquals("Frank Hartman", player.getUsername());
    }

    @Test
    public void testSetUsername() throws Exception {
        String username = "Barry Badpak";
        player.setUsername(username);
        Assert.assertEquals(username, player.getUsername());
    }

    @Test
    public void testGetIp() throws Exception {
        Assert.assertEquals("127.0.0.1", player.getIp());
    }

    @Test
    public void testGetPanels() throws Exception {
        Assert.assertNotNull(player.getPanels());
    }

    @Test
    public void testGetSetTeam() throws Exception {

        Assert.assertNull(player.getActiveInstruction());

        Team team = new Team("team1");
        player.setTeam(team);
        Assert.assertEquals(team, player.getTeam());
    }


    @Test
    public void testGetActiveInstruction() throws Exception {

    }

    /**
     * Checks if the right amount of panels are assigned to a player
     * Author Kaj
     *
     * @throws Exception
     */
    @Test
    public void testGeneratePanels() throws Exception {
        int panelsForPlayer = 12;
        List<Panel> panels = new ArrayList<Panel>();
        //Fills a list with panels
        for (int i = 0; i < 20; i++) {
            Panel panel = new Panel(i, 1, 5, "panel", PanelTypeEnum.HorizontalSlider);
            panels.add(panel);
        }
        //Checks if only 12 panels are assigned to the player
        Assert.assertEquals("Wrong amount of panels for player", panelsForPlayer, player.generatePanels(panels).size());
    }

    /**
     * Checks if there are enough panels to assign to a player
     * Author Kaj
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGeneratePanelsNotEnoughPanels() {
        List<Panel> panels = new ArrayList<Panel>();
        //Fills a list with not enough panels
        for (int i = 0; i < 10; i++) {
            Panel panel = new Panel(i, 1, 5, "panel", PanelTypeEnum.HorizontalSlider);
            panels.add(panel);
        }
        player.generatePanels(panels);
    }

    @Test
    public void testHasPanel() throws Exception {
        // create two panels
        panel1 = new Panel(1, 2, 8, "Slider1", PanelTypeEnum.HorizontalSlider);
        Panel panel2 = new Panel(1, 2, 8, "Slider1", PanelTypeEnum.HorizontalSlider);
        // add only one panel to the list of panels
        listPanels.add(panel1);
        // set the list of panels of the player that contains only one panel
        player.setPanels(listPanels);
        // check if the inserted panel is added to the players panellist
        Assert.assertEquals(true, player.hasPanel(panel1));
        // check if the not inserted panel is in the players panellist
        Assert.assertEquals(false, player.hasPanel(panel2));

    }

    @Test
    public void testGenerateInstruction() throws Exception {
        // create new panel and initialize new panels list
        panel1 = new Panel(1, 2, 8, "Slider1", PanelTypeEnum.HorizontalSlider);
        listPanels = new ArrayList<>();
        // add panel to list
        listPanels.add(panel1);
        // add player to team
        team1.addPlayer(player);
        // generate panels on this player
        team1.generatePanels(listPanels);
        // test values of generated panel
        instruction1 = player.generateInstruction();
        Assert.assertEquals(instruction1.getPanel().getId(), 1);
        Assert.assertEquals(instruction1.getPanel().getMinimumValue(), 2);
        Assert.assertEquals(instruction1.getPanel().getMaximumValue(), 8);
        // clear the list from panels
        listPanels.clear();
        // remove player from team
        team1.removePlayer(player);

    }

    @Test
    public void testSetPanels() throws Exception {
        List<Panel> panels = new ArrayList<Panel>();
        player.setPanels(panels);
        Assert.assertEquals(panels, player.getPanels());

    }
}