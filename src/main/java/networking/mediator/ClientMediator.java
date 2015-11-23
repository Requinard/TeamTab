package networking.mediator;

import Game.*;
import Game.adapters.InstructionAdapter;
import Game.adapters.PanelAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;

import java.util.List;


public class ClientMediator extends BaseMediator implements IMediator {
    ClientGame clientGame;

    public ClientMediator(ClientGame clientGame) {
        super();
        this.clientGame = clientGame;
    }


    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            List<Player> players = PlayerAdapter.toObjects(networkRequest.getPayload());

            clientGame.setPlayers(players);
        }
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            Instruction instruction = InstructionAdapter.toObject(networkRequest.getPayload());
        }
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            List<Team> teams = TeamAdapter.toObjects(networkRequest.getPayload());

            clientGame.setTeams(teams);
        }
    }

    @Override
    public void handlePanels(NetworkRequest networkRequest) {

    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {

    }

    public void createPlayer(Player player) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/createPlayer", PlayerAdapter.toString(player));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void createTeam(Team team) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/create", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void assignTeam(Team team) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/assign", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void processPanel(Panel panel) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/panels/", PanelAdapter.toString(panel));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void registerInvalidInstruction(Instruction instruction) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/instruction/", InstructionAdapter.toString(instruction));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void setPlayerStatus(Player localePlayer) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/changeStatus/", PlayerAdapter.toString(localePlayer));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }
}
