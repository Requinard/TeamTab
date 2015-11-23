package networking.mediator;

import Game.ClientGame;
import Game.Instruction;
import Game.Player;
import Game.Team;
import Game.adapters.InstructionAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import networking.server.NetworkRequest;
import networking.server.RequestType;

import java.util.List;

/**
 * Created by David on 11/23/2015.
 */
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
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/", PlayerAdapter.toString(player));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void assignTeam(Team team) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }
}
