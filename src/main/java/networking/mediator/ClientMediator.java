package networking.mediator;

import Game.*;
import Game.adapters.InstructionAdapter;
import Game.adapters.PanelAdapter;
import Game.adapters.PlayerAdapter;
import Game.adapters.TeamAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javassist.bytecode.stackmap.TypeData;
import networking.server.NetworkRequest;
import networking.server.RequestType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientMediator extends BaseMediator implements IMediator {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    ClientGame clientGame;

    public ClientMediator(ClientGame clientGame, int port) {
        super();
        log.log(Level.INFO, "client mediator has started");
        this.clientGame = clientGame;
        log.log(Level.INFO, "client set");
        log.log(Level.INFO, "client mediator has ended");
    }

    public ClientMediator(ClientGame clientGame) {
        this(clientGame, 8085);
    }


    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handlePlayers has started, networkRequest type is SEND");
            List<Player> players = PlayerAdapter.toObjects(networkRequest.getPayload());
            log.log(Level.FINER, "client players contains: {0} players", players.size());
            clientGame.setPlayers(players);
            log.log(Level.FINER, "client players have been set");
            //NetworkRequest getTeamRequest = new NetworkRequest(RequestType.GET, "/teams/players/", null);

            //networkServer.sendRequest(getTeamRequest, networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "client handlePlayers has ended, networkRequest has been send");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement handlePlayers reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handleInstruction has started, networkRequest type is SEND");
            Instruction instruction = InstructionAdapter.toObject(networkRequest.getPayload());
            log.log(Level.FINER, "client instruction contains panel text: {0}", instruction.getPanel().getText());

            clientGame.localInstruction = instruction;
            log.log(Level.FINER, "client handleInstruction has ended, instruction has been set");
        } else {
            networkServer.requeueRequest(networkRequest);
        }

    }

    @Override
    public void handleInstructions(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handleInstructions has started, networkRequest type is SEND");
            List<Instruction> instructions = InstructionAdapter.toObjects(networkRequest.getPayload());

            clientGame.fillScoreView(instructions);
            log.log(Level.FINER, "client handleInstruction has ended, instruction has been set");
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handleTeamPlayers(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handleTeamPlayers has started, networkRequest type is SEND");
            HashMap<String, List<String>> map;

            Type t = new TypeToken<HashMap<String, List<String>>>() {
            }.getType();

            map = new Gson().fromJson(networkRequest.getPayload(), t);
            log.log(Level.FINER, "client handleTeamPlayers map team contains: {0} teams", map.size());
            clientGame.setTeams(map);
            log.log(Level.FINER, "client handleTeamPlayers has ended, teams have been set");
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handleTeams has started, networkRequest type is SEND");
            List<Team> teams = TeamAdapter.toObjects(networkRequest.getPayload());
            log.log(Level.FINER, "client teams contains: {0} teams", teams.size());
            clientGame.setTeams(teams);
            log.log(Level.FINER, "client handleTeams has ended, teams have been set");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement handleTeams reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handlePanels(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.SEND) {
            log.log(Level.FINER, "client handlePanels has started, networkRequest type is SEND");
            //List<Panel> panels = PanelAdapter.toObjects(networkRequest.getPayload());
            Type t = new TypeToken<List<Integer>>() {
            }.getType();
            ArrayList<Integer> idPanels = new Gson().fromJson(networkRequest.getPayload(), t);
            ArrayList<Panel> panels = new ArrayList<>();

            for (int idPanel : idPanels) {
                panels.add(clientGame.getPanels().get(idPanel - 1));
            }
            log.log(Level.FINER, "client panels contains: {0} teams", panels.size());
            clientGame.localPanels = panels;
            log.log(Level.FINER, "client handlePanels has ended, panels have been set");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement handlePanels reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {
        networkServer.requeueRequest(networkRequest);
    }

    @Override
    public void handleTeamsAssign(NetworkRequest networkRequest) {
        networkServer.requeueRequest(networkRequest);
    }

    @Override
    public void handleTeamsCreate(NetworkRequest networkRequest) {
        networkServer.requeueRequest(networkRequest);
    }

    @Override
    public void handlePlayersChangeStatus(NetworkRequest networkRequest) {
        networkServer.requeueRequest(networkRequest);
    }

    public void createPlayer(Player player) {

        log.log(Level.FINER, "client createPlayer with player name: {0} has started", player.getUsername());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/", PlayerAdapter.toString(player));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client createPlayer has ended, POST NetworkRequest has been send");
    }

    public void createTeam(Team team) {
        log.log(Level.FINER, "client createTeam with team name: {0} has started", team.getName());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/create/", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client createTeam has ended, POST NetworkRequest has been send");
    }

    public void assignTeam(Team team) {
        log.log(Level.FINER, "client assignTeam to team name: {0} has started", team.getName());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/teams/assign/", TeamAdapter.toString(team));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client assignTeam has ended, POST NetworkRequest has been send");
    }

    public void processPanel(Panel panel) {
        log.log(Level.FINER, "client processPanel with panel text: {0} has started", panel.getText());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/panels/", PanelAdapter.toString(panel));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client processPanel has ended, POST NetworkRequest has been send");
    }

    public void registerInvalidInstruction(Instruction instruction) {
        log.log(Level.FINER, "client registerInvalidInstruction with instruction panel text: {0} has started", instruction.getPanel().getText());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/instruction/", InstructionAdapter.toString(instruction));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client registerInvalidInstruction has ended, POST NetworkRequest has been send");
    }

    public void setPlayerStatus(Player localePlayer) {
        log.log(Level.FINER, "client setPlayerStatus with player name: {0} has started", localePlayer.getUsername());
        NetworkRequest request = new NetworkRequest(RequestType.POST, "/players/changeStatus/", PlayerAdapter.toString(localePlayer));

        networkServer.send(request.toString(), clientGame.getHostIP());
        log.log(Level.FINER, "client setPlayerStatus has ended, POST NetworkRequest has been send");
    }


    /**
     * Gets the newest set of players from the server
     * Author: david
     */
    public void getPlayers() {
        log.log(Level.FINER, "client getPlayers has started");
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/players/", "");

        networkServer.sendRequest(request, clientGame.getHostIP());
        log.log(Level.FINER, "client getPlayers has ended");
    }

    /**
     * Gets the newest teams
     * Author: David
     */
    public void getTeams() {
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/teams/", "");

        networkServer.sendRequest(request, clientGame.getHostIP());
    }

    /**
     * Gets the team assignments from the server
     * Author: David
     */
    public void getTeamAssignments() {
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/teams/players/", "");

        networkServer.sendRequest(request, clientGame.getHostIP());
    }

    /**
     * Gets the newest panels of the player
     * Author: Kaj
     */
    public void getPanels() {
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/panels/", "");

        networkServer.sendRequest(request, clientGame.getHostIP());
    }

    /**
     * Gets the list of instructions for scoreView
     * Author: Kaj
     */
    public void getInstructions() {
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/instructions/", "");

        networkServer.sendRequest(request, clientGame.getHostIP());
    }
}
