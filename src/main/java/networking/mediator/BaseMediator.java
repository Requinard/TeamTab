package networking.mediator;

import networking.server.NetworkRequest;
import networking.server.NetworkServer;
import networking.server.NetworkServerSingleton;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by David on 11/23/2015.
 */
public abstract class BaseMediator implements IMediator {
    NetworkServer networkServer;
    Logger logger = Logger.getLogger(BaseMediator.class.getName());

    public BaseMediator() {
        try {
            networkServer = NetworkServerSingleton.getNetworkServer();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error opening server", e);
        }
    }

    public void listen() {
        while (true) {
            NetworkRequest networkRequest = networkServer.consumeRequest();

            if (networkRequest != null)
                handle(networkRequest);
        }
    }

    private void handle(NetworkRequest networkRequest) {
        switch (networkRequest.getUrl()) {
            case "/players/":
                handlePlayers(networkRequest);
                break;
            case "/instruction/":
                handleInstruction(networkRequest);
                break;
            case "/teams/":
                handleTeams(networkRequest);
                break;
            case "/panels/":
                handleTeams(networkRequest);
                break;
            case "/status/":
                handleStatus(networkRequest);
                break;
        }
    }
}
