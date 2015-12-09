package networking.mediator;

import Game.*;
import Game.ClientGame;
import Game.HostGame;
import Game.Player;
import Game.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by frank on 30/11/2015.
 */
public class MediatorIntergrationTest {

    HostGame hostGame;
    ClientGame clientGame;

    @Before
    public void setUp() throws Exception {
        hostGame = new HostGame();
        clientGame = new ClientGame();
        clientGame.setHostIp("127.0.0.1");
    }

    @After
    public void tearDown() throws Exception {
        hostGame = null;
        clientGame = null;
        System.out.println("teared down");
    }

    @Test
    public void joinGameTest() throws InterruptedException {

        hostGame.createTeam("a");
        hostGame.createTeam("b");
        clientGame.createPlayer("frank1", "127.0.0.1");
        Thread.sleep(1000);
        while(true) {
            if ((hostGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(hostGame.getPlayers().size() > 0);
    }

    // ********************** PLAYER mediator integration test ***********************
    @Test
    public void playerSetUsername() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);
        clientGame.getPlayer("Vito").setUsername("Vito2");
        Player player = clientGame.getPlayer("Vito2");


        while (true) {
            if ((player.getUsername().contains("Vito2")))
                break;
        }
        assertEquals("Vito2", player.getUsername());
    }

    @Test
    public void playerGetUsername() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);
        Player player = clientGame.getPlayer("Vito");
        while (true) {
            if ((player.getUsername().contains("Vito")))
                break;
        }
        assertEquals("Vito", player.getUsername());
    }

    @Test
    public void playerSetPlayerStatus() throws InterruptedException {
        Player player = null;
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);
        clientGame.getPlayer("Vito").setPlayerStatus(true);
        while (true) {
            if ((clientGame.getPlayer("Vito").getPlayerStatus()))
                player = clientGame.getPlayer("Vito");
            break;
        }
        assertEquals(true, player.getPlayerStatus());

    }

    @Test
    public void playerGetPlayerStatus() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);
        // default playerstatus is FALSE
        boolean result = clientGame.getPlayer("Vito").getPlayerStatus();
        while (true) {
            if ((!result))
                break;
        }
        assertEquals(false, result);
    }

    @Test
    public void playerGetIP() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);
        String ip = clientGame.getPlayer("Vito").getIp();
        while (true) {
            if ((ip.equals("127.0.0.1")))
                break;
        }
        assertEquals("127.0.0.1", ip);
    }

    @Test
    public void playerGetPanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        List<Panel> listPanels = new ArrayList<>();
        Panel panel1 = new Panel(1, 1, 2, "test", PanelTypeEnum.HorizontalSlider);
        listPanels.add(panel1);

        hostGame.getPlayer("127.0.0.1").setPanels(listPanels);
        int aantal = 0;

        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                aantal = clientGame.getPlayer("Vito").getPanels().size();
                break;
            }

        }
        assertEquals(1, aantal);
    }

    @Test
    public void playersetPanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        List<Panel> listPanels = new ArrayList<>();
        Panel panel1 = new Panel(1, 1, 2, "test", PanelTypeEnum.HorizontalSlider);
        listPanels.add(panel1);
        hostGame.getPlayer("127.0.0.1").setPanels(listPanels);
        int aantal = 0;
        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                aantal = clientGame.getPlayer("Vito").getPanels().size();
                break;
            }

        }
        assertEquals(1, aantal);
    }


    @Test
    public void playerGetTeam() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        List<Team> teamsList = clientGame.getTeams();
        while (true) {
            if ((teamsList.size() > 0)) {
                break;
            }
        }
        boolean result = false;
        for (Team team : teamsList) {
            for (Player player : team.getPlayers()) {
                if (player.getIp().equals("127.0.0.1")) {
                    result = true;
                }
            }
        }
        assertEquals(true, result);
    }












    /*
    Test if the teams are added to the hostgame and if they are received in the clientgame
    also tests if the teamname is still set
     */
    @Test
    public void getTeamsTest() throws InterruptedException {
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
               System.out.println("Teams: "+clientGame.getTeams().size());
                break;
            }
        }
        assertTrue(clientGame.getTeams().size() > 1);
        assertTrue(hostGame.getTeams().size() > 1);
        assertEquals("Team1", clientGame.getTeams().get(0).getName());
        assertEquals("Team2",clientGame.getTeams().get(1).getName());
    }
    /*
    Test if the amount of lifes of a team can be modified.
     */
    @Test
    public void TeamLevensTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        Team team1 = hostGame.getTeams().get(1);
        team1.changeLives(-1);
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        Team clientTeam = clientGame.getTeams().get(1);
        assertTrue(clientTeam.getLives() == hostGame.getTeams().get(1).getLives());
        assertTrue(clientTeam.getLives() == 2);
    }
    /*
    Test if the time which a team has to complete an instruction can be modified
     */
    @Test
    public void TeamTimeTest() throws InterruptedException {
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        Team team1 = hostGame.getTeams().get(1);
        team1.changeTime(-2);
        System.out.println(team1.getTime());
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        Team clientTeam = clientGame.getTeams().get(1);
        assertTrue(clientTeam.getTime() == hostGame.getTeams().get(1).getTime());
        assertTrue(clientTeam.getTime() == 7);
    }
    /*
    Test if the score of the team on the host is the same as the team on the client
    */
    @Test
    public void TeamScoreTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertEquals(0, clientGame.getTeams().get(1).getScore());
    }
    /*
    Test if the team is alive when its killed on the hostGame
     */
    @Test
    public void TeamIsAliveTest() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        hostGame.getTeams().get(1).changeLives(-3);
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertEquals(clientGame.getTeams().get(1).isAlive(), false);
    }
    /*
    Test if you can remove a player from a team and add the same player to a different team.
     */
    @Test
    public void TeamModifyPlayer() throws InterruptedException{
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        clientGame.createPlayer("P2", "0.0.0.0"); //Aanmaken van een tweede speler
        while(true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertEquals(clientGame.getPlayers().size(), 2); //checks if there are two players in total in the game
        Player player = hostGame.getTeams().get(1).getPlayers().get(0);
        hostGame.getTeams().get(1).removePlayer(player); //removes the player from team 1
        hostGame.getTeams().get(0).addPlayer(player);   //adds the player to team 1
        while(true) {
            if (clientGame.getTeams().get(1).getPlayers().size() <1) {
                break;
            }
        }
        assertEquals(clientGame.getTeams().get(1).getPlayers().size(),0);
        assertEquals(clientGame.getTeams().get(0).getPlayers().size(),2);
    }
}