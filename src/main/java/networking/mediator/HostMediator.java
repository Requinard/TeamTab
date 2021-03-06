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
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by David on 11/23/2015.
 */
public class HostMediator extends BaseMediator implements IMediator {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());
    HostGame hostGame;
    TimerTask timerTask1;
    java.util.Timer refreshTimer;

    public HostMediator(HostGame hostGame, int port) {
        super(port);
        this.hostGame = hostGame;
        log.log(Level.INFO,"hostgame set");
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
    @Override
    public void handlePlayersChangeStatus(NetworkRequest networkRequest) {
        log.log(Level.INFO, "handlePlayerChangeStatus is called");
        if (networkRequest.getType() == RequestType.POST) {
            // Makes a player object from the inputstream
            Player incomingPlayer = PlayerAdapter.toObject(networkRequest.getPayload());
            System.out.println(incomingPlayer.getUsername() + incomingPlayer.getPlayerStatus());
            log.log(Level.INFO, "networkRequest is translated to player {0}", incomingPlayer.toString());
            for (Player player : hostGame.getPlayers()) {
                if (incomingPlayer.getIp().equals(player.getIp())) {
                    player.setPlayerStatus(incomingPlayer.getPlayerStatus());

                    hostGame.startRound();
                    log.log(Level.INFO, "The playerstatus in the hostgame player has changed");
                }
            }
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        log.log(Level.FINER, "handlePlayers is called");
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "networkRequest type is GET");
            // Retrieve players
            List<Player> players = hostGame.getPlayers();
            log.log(Level.FINER, "hostgame players contains {0} players", players.size());
            // JSONify players
            String json = PlayerAdapter.toString(players);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, "/players/", json);

            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "networkRequest is sent to {0}", networkRequest.getNetworkMessage().getSender());

        } else if (networkRequest.getType() == RequestType.POST) {
            log.log(Level.FINER, "networkRequest type is POST");
            // Makes a player object from the inputstream
            Player player = PlayerAdapter.toObject(networkRequest.getPayload());
            log.log(Level.FINER, "networkRequest is translated to player: {0}", player.toString());
            hostGame.createPlayer(player.getUsername(), networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "player is created in the hostgame");
            log.log(Level.FINER, "player has been auto-assigned to a team");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleInstruction(NetworkRequest networkRequest) {
        log.log(Level.FINER, "handleInstruction is called");
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "networkRequset type is GET");
            String ip = networkRequest.getNetworkMessage().getSender();
            Instruction latestInstruction = hostGame.getPlayer(ip).getActiveInstruction();
            log.log(Level.FINER, "latest instruction of the player is:{0}", latestInstruction.toString());
            //JSonify instruction
            String json = InstructionAdapter.toString(latestInstruction);
            log.log(Level.FINER, "instruction got jsonified");
            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), ip);
            log.log(Level.FINER, "networkRequest has been send");
        } else if (networkRequest.getType() == RequestType.POST) {
            //todo In de api kijken of het hier om gaat
            log.log(Level.FINER, "networkRequest type is POST");
            Instruction expiredInstruction = InstructionAdapter.toObject(networkRequest.getPayload());
            expiredInstruction.setPlayer(hostGame.getPlayer(networkRequest.getNetworkMessage().getSender()));
            log.log(Level.FINER, "expiredInstruction is {0}", expiredInstruction.toString());
            Instruction newInstruction = hostGame.registerInvalidInstruction(expiredInstruction);

            if (newInstruction != null) {
                String json = InstructionAdapter.toString(newInstruction);
                NetworkRequest response = new NetworkRequest(RequestType.SEND, "/instruction/", json);
                networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
            }

            List<Team> teams = hostGame.getTeams();
            log.log(Level.FINER, "hostGame teams has {0} teams", teams.size());
            String json = TeamAdapter.toString(teams);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, "/teams/", json);
            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleScoreBoard(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "networkRequset type is GET");
            String ip = networkRequest.getNetworkMessage().getSender();
            List<String> scoreboard = hostGame.getScoreboard();
            log.log(Level.FINER, "latest instruction of the player is:{0}", scoreboard.toString());
            //JSonify instruction
            String json = new Gson().toJson(scoreboard).toString();
            log.log(Level.FINER, "instruction got jsonified");
            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), ip);
            log.log(Level.FINER, "networkRequest has been send");
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    @Override
    public void handleTeamPlayers(NetworkRequest networkRequest) {
        log.log(Level.FINER, "handleTeamPlayers is called");
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "RequestType is GET");
            List<Team> teams = hostGame.getTeams();
            log.log(Level.FINER, "hostGame teams has {0} teams", teams.size());
            HashMap<String, List<String>> map = new HashMap<>();

            for (Team team : teams) {
                List<String> usernames = new ArrayList<>();
                log.log(Level.FINER, "created usernamesList team:{0}", team.getName());
                for (Player p : team.getPlayers()) {
                    usernames.add(p.getUsername());
                    log.log(Level.FINER, "added player `{0}` in usernamesList", p.getUsername());
                }

                map.put(team.getName(), usernames);
                log.log(Level.FINER, "map put in hasmap for team {0}", team.getName());
            }

            Type t = new TypeToken<HashMap<String, List<String>>>() {
            }.getType();

            String json = new Gson().toJson(map, t).toString();
            log.log(Level.FINER, "json made for map");
            NetworkRequest response = new NetworkRequest(RequestType.SEND, "/teams/players/", json);
            networkServer.sendRequest(response, networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "NetworkRequest send");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleTeams(NetworkRequest networkRequest) {
        log.log(Level.FINER, "handleTeams is called");
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "RequestType is GET");
            //Retrieve teams
            List<Team> teams = hostGame.getTeams();
            log.log(Level.FINER, "hostGame teams has {0} teams", teams.size());
            String json = TeamAdapter.toString(teams);

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);
            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "NetworkRequest send");
        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement reached, requeueRequest uitgevoerd");
        }
    }

    /**
     * Author Qun
     * The GET returns all panels a game has
     * The Post processes a the panel a player pressed
     *
     * @param networkRequest the incoming request to get and also process panels
     */
    @Override
    public void handlePanels(NetworkRequest networkRequest) {
        log.log(Level.FINER, "handlePanels is called");
        if (networkRequest.getType() == RequestType.GET) {
            log.log(Level.FINER, "RequestType is GET");

            // Retrieve panels
            List<Panel> panels = hostGame.getPlayer(networkRequest.getNetworkMessage().getSender()).getPanels();
            ArrayList<Integer> idPanels = new ArrayList<>();
            for (Panel panel : panels) {
                idPanels.add(panel.getId());
            }
            String json = new Gson().toJson(idPanels).toString();
            log.log(Level.FINER, "hostGame panels has {} panels", panels.size());

            NetworkRequest response = new NetworkRequest(RequestType.SEND, networkRequest.getUrl(), json);

            networkServer.send(response.toString(), networkRequest.getNetworkMessage().getSender());
            log.log(Level.FINER, "NetworkRequest send");
        } else if (networkRequest.getType() == RequestType.POST) {
            log.log(Level.FINER, "RequestType is POST");
            // converting the incoming json to panel
            Panel panel = PanelAdapter.toObjectsSinglePanel(networkRequest.getPayload());

            log.log(Level.FINER, "panel {0} is created from the networkRequestPayload", panel.toString());
            Instruction newInstruction = hostGame.processPanel(hostGame.getPlayer(networkRequest.getNetworkMessage().getSender()), panel);


            List<Team> teams = hostGame.getTeams();
            String json1 = TeamAdapter.toString(teams);
            NetworkRequest response1 = new NetworkRequest(RequestType.SEND, "/teams/", json1);
            if (newInstruction != null) {
                //new instruction to correct player
                String json = InstructionAdapter.toString(newInstruction);
                NetworkRequest response = new NetworkRequest(RequestType.SEND, "/instruction/", json);
                networkServer.send(response.toString(), newInstruction.getPlayer().getIp());
                //team update for this player

                log.log(Level.FINER, "hostGame teams has {0} teams", teams.size());

                networkServer.send(response1.toString(), newInstruction.getPlayer().getIp());
            } else {
                //team update for sender
                log.log(Level.FINER, "hostGame teams has {0} teams", teams.size());

                networkServer.send(response1.toString(), networkRequest.getNetworkMessage().getSender());
            }


        } else {
            networkServer.requeueRequest(networkRequest);
            log.log(Level.FINE, "else statement reached, requeueRequest uitgevoerd");
        }
    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {
        if (networkRequest.getType() == RequestType.GET) {
            //todo Deze zal pas later worden geimplementeerd op het moment dat we precies weten welke informatie nodig is
        } else {
            networkServer.requeueRequest(networkRequest);
        }
    }

    public void handleTeamsCreate(NetworkRequest networkRequest) {
        log.log(Level.INFO, "handleTeamsCreate is called");
        Team team = TeamAdapter.toObject(networkRequest.getPayload());
        log.log(Level.INFO, "team `{0}` is made from networkRequestPayload", team.getName());
        hostGame.createTeam(team.getName());
        log.log(Level.INFO, "Team `{0}` added to the hostGame", team.getName());
    }

    public void handleTeamsAssign(NetworkRequest networkRequest) {
        log.log(Level.INFO, "handleTeamAssign is called");
        Team teamRequest = TeamAdapter.toObject(networkRequest.getPayload());
        log.log(Level.INFO, "team `{0}` is made from networkRequestPayload", teamRequest.getName());
        for (Team team : hostGame.getTeams()) {
            if (team.getName().equals(teamRequest.getName())) {
                hostGame.assignTeam(hostGame.getPlayer(networkRequest.getNetworkMessage().getSender()), team);
                log.log(Level.INFO, "Team `{0}` assigned to hostGame", team.getName());
            }
        }
    }
}
