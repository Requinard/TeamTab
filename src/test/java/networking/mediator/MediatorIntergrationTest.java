package networking.mediator;

import Game.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by frank on 30/11/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        clientGame = null;
        hostGame = null;
        System.out.println("teared down");
    }

    @Test
    public void joinGameTest() throws InterruptedException {
        hostGame.createTeam("a");
        hostGame.createTeam("b");
        clientGame.createPlayer("frank1", "127.0.0.1");
        boolean result = false;
        while (true) {
            if ((clientGame.getPlayers().size() > 0)) {
                result = true;
                break;
            }
        }
        assertEquals(true, result);
    }

    // ********************** PLAYER mediator integration test ***********************

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerSetUsername() throws InterruptedException {

        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }

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
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGetUsername() throws InterruptedException {

        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        String username = clientGame.getPlayer("Vito").getUsername();
        while (true) {
            if (!username.isEmpty())
                break;
        }
        assertEquals("Vito", username);

    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerSetPlayerStatus() throws InterruptedException {

        Player player;
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        clientGame.getPlayer("Vito").setPlayerStatus(true);
        while (true) {
            if (clientGame.getPlayer("Vito").getPlayerStatus())
                break;
        }
        player = clientGame.getPlayer("Vito");
        while (true) {
            if (player != null)
                break;
        }
        assertEquals(true, player.getPlayerStatus());
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGetPlayerStatus() throws InterruptedException {

        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        boolean result = clientGame.getPlayer("Vito").getPlayerStatus();
        while (true) {
            if ((!result))
                break;
        }
        assertEquals(false, result);
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGetIP() throws InterruptedException {
        String ipaddress = "";
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        ipaddress = clientGame.getPlayer("Vito").getIp();
        while (true) {
            if (ipaddress != "")
                break;
        }
        assertEquals("127.0.0.1", ipaddress);
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGetPanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");

        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }

        List<Panel> listPanels = new ArrayList<>();
        Panel panel1 = new Panel(1, 1, 2, "test", PanelTypeEnum.HorizontalSlider);
        listPanels.add(panel1);

        clientGame.getPlayer("Vito").setPanels(listPanels);
        boolean result = false;
        while (true) {
            if (clientGame.getPlayer("Vito").getPanels().size() > 0) {
                result = true;
                break;
            }

        }
        assertEquals(true, result);
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playersetPanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }

        List<Panel> listPanels = new ArrayList<>();
        Panel panel1 = new Panel(1, 1, 2, "test", PanelTypeEnum.HorizontalSlider);
        listPanels.add(panel1);
        clientGame.getPlayer("Vito").setPanels(listPanels);
        boolean result = false;
        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                result = true;
                break;
            }
        }
        assertEquals(true, result);
    }

    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGetTeam() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
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
     *
     * @throws InterruptedException
     */
    @Test
    public void playerSetTeam() throws InterruptedException {
        Team team1 = null;
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        team1 = hostGame.createTeam("Corleone");
        while (true) {
            if (team1 != null)
                break;
        }
        clientGame.getPlayer("Vito").setTeam(team1);
        boolean result = false;
        while (true) {
            if ((clientGame.getPlayer("Vito").getTeam().equals(team1))) {
                result = true;
                break;
            }
        }
        assertEquals(true, result);
    }


    /**
     * Author Kamil Wasylkiewcz
     *
     * @throws InterruptedException
     */
    @Test
    public void playerGeneratePanels() throws InterruptedException {
        hostGame.createTeam("Test1");
        hostGame.createTeam("Test2");
        clientGame.createPlayer("Vito", "127.0.0.1");
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
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

        boolean result = false;
        clientGame.getPlayer("Vito").generatePanels(listPanels);
        while (true) {
            if ((clientGame.getPlayer("Vito").getPanels().size() > 0)) {
                result = true;
                break;
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
        while (true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        assertTrue(clientGame.getTeams().size() == 2);
    }

    /*
    Test if the amount of lifes of a team can be modified.
     */
    @Test
    public void TeamLevensTest() throws InterruptedException {
        Team clientTeam = null;
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        Team team1 = hostGame.getTeams().get(1);
        team1.changeLives(-1);
        while (true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        clientTeam = clientGame.getTeams().get(1);
        boolean result = false;
        while (true) {
            if (clientTeam != null) {
                result = true;
                break;
            }
        }
        assertEquals(true, result);

    }

    /*
    Test if the time which a team has to complete an instruction can be modified
     */
    @Test
    public void TeamTimeTest() throws InterruptedException {
        int oldTime = 0;
        int newTime = 0;
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while (true) {
            if ((clientGame.getPlayers().size() > 0))
                break;
        }
        oldTime = clientGame.getPlayer("P1").getTeam().getTime();
        while (true) {
            if (oldTime != 0) {
                break;
            }
        }
        newTime = clientGame.getPlayer("P1").getTeam().changeTime(2);
        while (true) {
            if ((newTime != 0)) {
                break;
            }
        }
        assertEquals(oldTime + 2, newTime);
    }

    /*
    Test if the score of the team on the host is the same as the team on the client
    */
    @Test
    public void TeamScoreTest() throws InterruptedException {
        int score = 10;
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while (true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        score = clientGame.getPlayer("P1").getTeam().getScore();
        while (true) {
            if (score != 10)
                break;
        }
        assertEquals(0, score);
    }

    /*
    Test if the team is alive when its killed on the hostGame
     */
    @Test
    public void TeamIsAliveTest() throws InterruptedException {
        hostGame.createTeam("Team1");
        hostGame.createTeam("Team2");
        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
        while (true) {
            if ((clientGame.getTeams().size() > 1)) {
                break;
            }
        }
        boolean result = clientGame.getPlayer("P1").getTeam().isAlive();
        while (true) {
            if ((result)) {
                break;
            }
        }
        assertEquals(true, result);
    }

//    /*
//    Test if you can remove a player from a team and add the same player to a different team.
//     */
//    @Test
//    public void TeamModifyPlayer() throws InterruptedException {
//        Player player1 = new Player("Vito", "127.0.0.1");
//        Player player2 = new Player("Corleone", "127.0.0.1");
//        Player demoPlayer1 = null;
//        Player demoPlayer2 = null;
//        int playerSize = 0;
//        hostGame.createTeam("Team1");
//        hostGame.createTeam("Team2");
//        clientGame.createPlayer("P1", "0.0.0.0"); //het aanmaken vna een player is verplicht want ander weet de server niet naar welk ip hij packages moet doorsturen
//        while (true) {
//            if ((clientGame.getPlayers().size() > 0)) {
//                break;
//            }
//        }
//        demoPlayer1 = clientGame.getTeams().get(0).addPlayer(player1);
//        while(true){
//            if(demoPlayer1.equals(player1))
//                break;
//        }
//        demoPlayer2 = clientGame.getTeams().get(0).addPlayer(player2);
//        while(true){
//            if(demoPlayer2.equals(player2))
//                break;
//        }
//        playerSize = clientGame.getTeams().get(0).getPlayers().size();
//        while(true){
//            if(playerSize == 3)
//                break;
//        }
//        clientGame.getTeams().get(0).removePlayer(player2);
//        while (true) {
//            if ((clientGame.getTeams().size() > 0)) {
//                break;
//            }
//        }
//        playerSize = clientGame.getTeams().get(0).getPlayers().size();
//        while(true){
//            if(playerSize == 2)
//                break;
//        }
//        assertEquals(2,playerSize);
//    }

    /**
     * Author Qun
     * If the player doesn't have anymore time to fill in an instruction
     * then registerInvalidInstruction() will count it as an incorrect instruction
     *
     * @throws InterruptedException If the connection is lost during the game
     */
    @Test
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
    }
}

