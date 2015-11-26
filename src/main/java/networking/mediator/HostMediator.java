package networking.mediator;

import Game.HostGame;
import Game.Instruction;
import Game.Player;
import Game.Team;
import Game.adapters.InstructionAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;
import sun.nio.ch.Net;

import javax.print.DocFlavor;
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
            case "/player/":
                handlePlayers(networkRequest);
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

    @Override
    public void handlePanels(NetworkRequest networkRequest) {

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
