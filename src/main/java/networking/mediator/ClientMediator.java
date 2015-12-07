package networking.mediator;

import Game.*;
import Game.adapters.InstructionAdapter;
import Game.adapters.PanelAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import networking.server.NetworkRequest;
import networking.server.RequestType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class ClientMediator extends BaseMediator implements IMediator {
    ClientGame clientGame;

    public ClientMediator(ClientGame clientGame, int port) {
        super();
        this.clientGame = clientGame;
    }

    public ClientMediator(ClientGame clientGame) {
        this(clientGame, 8085);
    }


    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            List<Player> players = PlayerAdapter.toObjects(networkRequest.getPayload());

            clientGame.setPlayers(players);

            NetworkRequest getTeamRequest = new NetworkRequest(RequestType.GET, "/teams/players/", null);

            networkServer.sendRequest(getTeamRequest, networkRequest.getNetworkMessage().getSender());
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            Instruction instruction = InstructionAdapter.toObject(networkRequest.getPayload());

            clientGame.localInstruction = instruction;
        }
    }

    @Override
    public void handleTeamPlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            HashMap<String, List<String>> map;

            Type t = new TypeToken<HashMap<String, List<String>>>() {
            }.getType();

            map = new Gson().fromJson(networkRequest.getPayload(), t);

            clientGame.setTeams(map);
        }
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            List<Team> teams = TeamAdapter.toObjects(networkRequest.getPayload());

            clientGame.setTeams(teams);
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handlePanels(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            List<Panel> panels = PanelAdapter.toObjects(networkRequest.getPayload());

            clientGame.setPanels(panels);
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {
        return;
    }

    @Override
    public void handleTeamsAssign(NetworkRequest networkRequest) {
        return;
    }

    @Override
    public void handleTeamsCreate(NetworkRequest networkRequest) {

    }

    public void createPlayer(Player player) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/", PlayerAdapter.toString(player));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void createTeam(Team team) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/create/", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
    }

    public void assignTeam(Team team) {
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/assign/", TeamAdapter.toString(team));

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
