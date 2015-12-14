package networking.mediator;

import Game.*;
import org.junit.After;
import org.junit.Before;

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

    //todo: @Test
    public void joinGameTest() throws InterruptedException {

        hostGame.createTeam("a");
        hostGame.createTeam("b");
        clientGame.createPlayer("frank1", "127.0.0.1");
        Thread.sleep(1000);
        while (true) {
            if ((hostGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(hostGame.getPlayers().size() > 0);
    }

    // ********************** PLAYER mediator integration test ***********************

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
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

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
    public void playerGetTeam() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        List<Team> teamsList;
        while (true) {
            if ((clientGame.getTeams().size() > 0)) {
                teamsList = clientGame.getTeams();
                if (teamsList.size() > 0) {
                    break;
                }
            }
        }
        boolean result = false;
        for (Team team : teamsList) {
            for (Player player : team.getPlayers()) {
                if (player.getIp().equals("127.0.0.1")) {
                    result = true;
                    break;
                }
            }
        }
        assertEquals(true, result);
    }

    /**
     * Author Kamil Wasylkiewcz
     * @throws InterruptedException
     */
    //todo: @Test
    public void playerSetTeam() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        Team team1 = hostGame.createTeam("Corleone");
        while (true) {
            if ((clientGame.getTeams().size() == 3))
                break;
        }
        assertTrue(clientGame.getTeams().size() == 3);
        clientGame.getPlayer("Vito").setTeam(team1);
        Team team2 = null;
        while (true) {
            if ((clientGame.getPlayer("Vito").getTeam().equals(team1))) {
                team2 = clientGame.getPlayer("Vito").getTeam();
                break;
            }
        }
        assertEquals(team1, team2);
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    //todo: @Test
    public void playerGeneratePanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        assertTrue(clientGame.getPlayers().size() > 0);

        List<Panel> listPanels = new ArrayList<>();
        Panel panel1 = new Panel(1, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel2 = new Panel(2, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel3 = new Panel(3, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel4 = new Panel(4, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel5 = new Panel(5, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel6 = new Panel(6, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel7 = new Panel(7, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel8 = new Panel(8, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel9 = new Panel(9, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel10 = new Panel(10, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel11 = new Panel(11, 1, 1, "test", PanelTypeEnum.Button);
        Panel panel12 = new Panel(12, 1, 1, "test", PanelTypeEnum.Button);
        listPanels.add(panel1);
        listPanels.add(panel2);
        listPanels.add(panel3);
        listPanels.add(panel4);
        listPanels.add(panel5);
        listPanels.add(panel6);
        listPanels.add(panel7);
        listPanels.add(panel8);
        listPanels.add(panel9);
        listPanels.add(panel10);
        listPanels.add(panel11);
        listPanels.add(panel12);

        hostGame.getPlayer("127.0.0.1").setPanels(listPanels);
        int aantal = 0;
        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                aantal = clientGame.getPlayer("Vito").getPanels().size();
                break;
            }
        }
        assertEquals(12, aantal);
        clientGame.getPlayer("Vito").generatePanels(listPanels);
        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                aantal = clientGame.getPlayer("Vito").getPanels().size();
                break;
            }
        }
        assertEquals(listPanels.size(), aantal);
    }

    /*
    Test if the teams are added to the hostgame and if they are received in the clientgame
    also tests if the teamname is still set
     */
    //todo: @Test
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
    //todo: @Test
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
    //todo: @Test
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
    //todo: @Test
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
    //todo: @Test
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
    //todo: @Test
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

    /**
     * Author Qun
     * Creates 2 players and 2 teams
     * After that the game starts
     * And this method then tests if the panel
     * the player pressed is proccessed correctly
     *
     * @throws InterruptedException If the connection is lost during the game
     */
    //todo: @Test
    public void panelProcessPanel() throws InterruptedException {
        Player player1 = new Player("Qun", "127.0.0.1");
        // Create team alpha and beta
        hostGame.createTeam("Team beta");
        hostGame.createTeam("Team alpha");

        // Create player Qun and Kamil
        clientGame.createPlayer(player1.getUsername(), player1.getIp());

        clientGame.setLocalIP("127.0.0.1");
        while (true) {
            if (hostGame.getPlayers().size() > 0) {
                break;
            }
        }

        //clientGame.changePlayerStatus(true);

        // The hostgame starts the game after all players are ready for the game
        while (true) {
            if (clientGame.getPlayer("Qun").getActiveInstruction().getPanel() != null) {
                break;
            }
        }
        //Client sends a correct panel with instruction to the host so it gets processed
        clientGame.processPanel(player1, player1.getActiveInstruction().getPanel());

        //Checks if after the right instruction is send to the hostgame, if the score of the team is set to 1
        assertTrue("Score is not correct", player1.getTeam().getScore() > 0);

        // Test a wrong instruction for player1
        clientGame.processPanel(player1, new Panel(51, 0, 1, "Press wrong", PanelTypeEnum.Button));
        // Test if the teamtime is substracted by 1 second
        assertTrue("Time is not subtracted by 1 second, and set to 8", player1.getTeam().getTime() == 8);
    }

    /**
     * Author Qun
     * If the player doesn't have anymore time to fill in an instruction
     * then registerInvalidInstruction() will count it as an incorrect instruction
     *
     * @throws InterruptedException If the connection is lost during the game
     */
    //todo: @Test
    public void InstructionRegisterInvalidInstruction() throws InterruptedException {
        Player player1 = new Player("Qun", "127.0.0.1");

        // Create team alpha and beta
        hostGame.createTeam("Team beta");
        hostGame.createTeam("Team alpha");
        clientGame.localPlayer = player1;

        // Create player Qun
        clientGame.createPlayer(player1.getUsername(), player1.getIp());
        while (true) {
            if (clientGame.localPlayer != null) {
                break;
            }
        }

        clientGame.changePlayerStatus(true);
        Thread.sleep(100);
        clientGame.changePlayerStatus(true);
        Thread.sleep(100);
        clientGame.changePlayerStatus(true);
        Thread.sleep(100);
        clientGame.changePlayerStatus(true);
        Thread.sleep(100);
        clientGame.changePlayerStatus(true);
        Thread.sleep(100);

        // create a  instruction
        Panel panel = new Panel(1, 1, 1, "test", PanelTypeEnum.Button);
        Instruction instruction = new Instruction(panel, 1, player1);
        while (true) {
            if (instruction != null) {
                System.out.println("instruction not null");
                break;
            }
        }

        // set the local ip of the game and add a localplayer to the clientgame
        clientGame.setLocalIP("127.0.0.1");
        List<Player> listPlayer = new ArrayList<>();
        listPlayer.add(player1);
        clientGame.setPlayers(listPlayer);
        while (true) {
            if (clientGame.getPlayers().size() > 0)
                break;
        }

        // set the instrucition as the active instruction for the player
        clientGame.getPlayer("Qun").setActiveInstruction(instruction);
        while (true) {
            if (clientGame.getPlayer("Qun").getActiveInstruction() == instruction) {
                System.out.println("getactive instruction oke");
                break;
            }
        }

        // register the instruction as invalid
        clientGame.registerInvalidInstruction(clientGame.getPlayer("Qun").getActiveInstruction());
        boolean result = false;
        while (true) {
            if (clientGame.getPlayer("Qun").getActiveInstruction() != instruction) {
                System.out.println("instruction registerd invalid");
                result = true;
                break;
            }
        }

        //Checks if the Invalid Instruction is registered in the his team
        assertTrue("Time is not subtracted by 1, and set to 8", result);
}}
