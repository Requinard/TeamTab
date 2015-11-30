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

    public HostMediator(HostGame hostGame) {
        super();
        this.hostGame = hostGame;
    }


    public void mediate() {
        while (true) {
            NetworkRequest networkRequest = networkServer.consumeRequest();

            if (networkRequest != null) {
                handle(networkRequest);
            }
        }
    }

    private void handle(NetworkRequest networkRequest) {
        //TODO: start handling network requests
        switch (networkRequest.getUrl()) {
            case "/player":
                handlePlayers(networkRequest);
                break;
            case "/player/createPlayer":
                handlePlayersCreatePlayer(networkRequest);
                break;
            case "/player/changeStatus":
                handlePlayersChangeStatus(networkRequest);
                break;
            case "/instruction/":
                handleInstruction(networkRequest);
                break;
            case "/teams/":
                handleTeams(networkRequest);
                break;
            case "/teams/assign":
                handleTeamsAssign(networkRequest);
            case "/teams/create/":
                handleTeamsCreate(networkRequest);
                break;
            case "/panels/":
                handlePanels(networkRequest);
                break;
            case"/status/":
                handleStatus(networkRequest);
                break;
        }
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

    /**
     * Author Qun
     * This method receives a networkrequest to create player
     * After that it created the player with username and ip adress
     *
     * @param networkRequest the incoming request to create players
     */
    public void handlePlayersCreatePlayer(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.POST) {
            // Makes a player object from the inputstream
            Player player = PlayerAdapter.toObject(networkRequest.getPayload());
            hostGame.createPlayer(player.getUsername(), player.getIp());
        }
    }

    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            // Retrieve players
            List<Player> players = hostGame.getPlayers();

            // JSONify players
            String json = PlayerAdapter.toString(players);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());


        }
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        if(networkRequest.getType() == RequestType.GET){
            String ip = networkRequest.getNetworkMessage().getSender();
            Instruction latestInstruction = getPlayer(ip).getActiveInstruction();

            //JSonify instruction
            String json = InstructionAdapter.toString(latestInstruction);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), ip);
        }
        if(networkRequest.getType() == RequestType.POST){
            //todo In de api kijken of het hier om gaat
            Instruction expiredInstruction = InstructionAdapter.toObject(networkRequest.getPayload());
            hostGame.registerInvalidInstruction(expiredInstruction);
        }
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            //Retrieve teams
            List<Team> teams = hostGame.getTeams();

            String json = TeamAdapter.toString(teams);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);
            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
        }
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
        }
        if (networkRequest.getType() == RequestType.POST) {
            // converting the incoming json to panel
            Panel panel = PanelAdapter.toObjectsSinglePanel(networkRequest.getPayload());
            for (Player player : hostGame.getPlayers())
                //Processes the panel, it check which player it is by checking his IP adress
                if (player.getIp().equals(PlayerAdapter.toObject(networkRequest.getPayload()).getIp())) {
                    hostGame.processPanel(player, panel);
                }
        }
    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {
        if(networkRequest.getType() == RequestType.GET){
            //todo Deze zal pas later worden geimplementeerd op het moment dat we precies weten welke informatie nodig is
        }
    }
    public void handleTeamsCreate(NetworkRequest networkRequest){
        Team team = TeamAdapter.toObject(networkRequest.getPayload());
        hostGame.createTeam(team.getName());
    }
    public void handleTeamsAssign(NetworkRequest networkRequest)
    {
        Team teamRequest = TeamAdapter.toObject(networkRequest.getPayload());
        for(Team team : hostGame.getTeams()) {
            if(team.getName() == teamRequest.getName()) {
                hostGame.assignTeam(getPlayer(networkRequest.getNetworkMessage().getSender()),team);
            }
        }
    }

    //gets player by IP
    public Player getPlayer(String ipadress)
    {
        Player returnPlayer = null;
        for(Player player : hostGame.getPlayers()){
            if(player.getIp().equals(ipadress))
                returnPlayer = player;
        }
        return returnPlayer;
    }
}
