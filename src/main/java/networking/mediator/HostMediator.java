package networking.mediator;

import Game.HostGame;
import Game.Player;
import Game.adapters.PlayerAdapter;
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
            case "/player/":
                handlePlayers(networkRequest);
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

    }

    @Override
    public void handleTeam(NetworkRequest networkRequest) {

    }

    @Override
    public void handlePanels(NetworkRequest networkRequest) {

    }

    @Override
    public void handleStatus(NetworkRequest networkRequest) {

    }
}
