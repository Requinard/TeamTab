package networking.mediator;

import Game.*;
import Game.adapters.InstructionAdapter;
import Game.adapters.PanelAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;

import java.util.List;

/**
 * Created by David on 11/23/2015.
 */
public class HostMediator extends BaseMediator implements IMediator {
    HostGame hostGame;

    public HostMediator(HostGame hostGame, int port) {
        super(port);
        this.hostGame = hostGame;
    }

    public HostMediator(HostGame hostGame) {
        this(hostGame, 8085);

    }


    /**
     * Author Qun
     * This method changes the status of the player
     * So when all players are ready the game can be started
     *
     * @param networkRequest the incoming request to change playerstatus
     */
    public void handlePlayersChangeStatus(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.POST) {
            // Makes a player object from the inputstream
            Player incomingPlayer = PlayerAdapter.toObject(networkRequest.getPayload());
            for (Player player : hostGame.getPlayers()) {
                if (incomingPlayer.getIp().equals(player.getIp())) {
                    player.setPlayerStatus(incomingPlayer.getPlayerStatus());
                }
            }
        }

    }

    private void handleAll() {
        List<Player> players = hostGame.getPlayers();
        List<Team> teams = hostGame.getTeams();
        String json;
        NetworkRequest send;


        // send the players
        json = PlayerAdapter.toString(players);
        send = new NetworkRequest(RequestType.SEND, "/players/", json);
        networkServer.send(send.toString(), "127.0.0.1");

        // send the teams
        json = TeamAdapter.toString(teams);
        send = new NetworkRequest(RequestType.SEND, "/teams/", json);
        networkServer.send(send.toString(), "127.0.0.1");


    }

    @Override
    public void handlePlayers(NetworkRequest networkRequest) {


        if (networkRequest.getType() == RequestType.GET) {
            // Retrieve players
            List<Player> players = hostGame.getPlayers();

            // JSONify players
            String json = PlayerAdapter.toString(players);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, "/players/", json);

            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());


        }

        if (networkRequest.getType() == RequestType.POST) {
            // Makes a player object from the inputstream
            Player player = PlayerAdapter.toObject(networkRequest.getPayload());
            hostGame.createPlayer(player.getUsername(), networkRequest.getNetworkMessage().getSender());
        } else {
            networkServer.requeueRequest(networkRequest);
        }
        handleAll();
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            String ip = networkRequest.getNetworkMessage().getSender();
            Instruction latestInstruction = getPlayer(ip).getActiveInstruction();

            //JSonify instruction
            String json = InstructionAdapter.toString(latestInstruction);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), ip);
        } else if (networkRequest.getType() == RequestType.POST) {
            //todo In de api kijken of het hier om gaat
            Instruction expiredInstruction = InstructionAdapter.toObject(networkRequest.getPayload());
            hostGame.registerInvalidInstruction(expiredInstruction);
        } else {
            networkServer.requeueRequest(networkRequest);
        }
        handleAll();
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            //Retrieve teams
            List<Team> teams = hostGame.getTeams();

            String json = TeamAdapter.toString(teams);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);
            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
        } else {
            networkServer.requeueRequest(networkRequest);
        }
        handleAll();
    }

    /**
     * Author Qun
     * The GET returns all panels a game has
     * The Post processes a the panel a player pressed
     * @param networkRequest    the incoming request to get and also process panels
     */
    @Override
    public void handlePanels(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            // Retrieve panels
            List<Panel> panels = hostGame.getPanels();

            // JSONify panels
            String json = PanelAdapter.toString(panels);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
        } else if (networkRequest.getType() == RequestType.POST) {
            // converting the incoming json to panel
            Panel panel = PanelAdapter.toObjectsSinglePanel(networkRequest.getPayload());
            for (Player player : hostGame.getPlayers()) {
                //Processes the panel, it check which player it is by checking his IP adress
                if (player.getIp().equals(networkRequest.getNetworkMessage().getSender())) {
                    hostGame.processPanel(player, panel);
                }
            }
        } else {
            networkServer.requeueRequest(networkRequest);
        }
        handleAll();
    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            //todo Deze zal pas later worden geimplementeerd op het moment dat we precies weten welke informatie nodig is
        } else {
            networkServer.requeueRequest(networkRequest);
        }
        handleAll();
    }

    public void handleTeamsCreate(NetworkRequest networkRequest) {
        Team team = TeamAdapter.toObject(networkRequest.getPayload());
        hostGame.createTeam(team.getName());
    }

    public void handleTeamsAssign(NetworkRequest networkRequest) {
        Team teamRequest = TeamAdapter.toObject(networkRequest.getPayload());
        for (Team team : hostGame.getTeams()) {
            if (team.getName() == teamRequest.getName()) {
                hostGame.assignTeam(getPlayer(networkRequest.getNetworkMessage().getSender()), team);
            }
        }
        handleAll();
    }

    //gets player by IP
    public Player getPlayer(String ipadress) {
        Player returnPlayer = null;
        for (Player player : hostGame.getPlayers()) {
            if (player.getIp().equals(ipadress))
                returnPlayer = player;
        }
        return returnPlayer;
    }
}
