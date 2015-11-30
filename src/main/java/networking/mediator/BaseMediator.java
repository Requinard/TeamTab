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

    public Thread mediate() {
        Thread thread = new Thread(() -> listen());
        thread.start();

        return thread;
    }

    public void listen() {
        while (true) {
            NetworkRequest networkRequest = networkServer.consumeRequest();

            if (networkRequest != null)
                handle(networkRequest);
        }
    }

    private void handle(NetworkRequest networkRequest) {

        if (networkRequest.getUrl().equals("/players/")) {
            handlePlayers(networkRequest);

        } else if (networkRequest.getUrl().equals("/instruction/")) {
            handleInstruction(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/")) {
            handleTeams(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/assign")) {
            handleTeamsAssign(networkRequest);

            handleTeamsCreate(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/create/")) {
            handleTeamsCreate(networkRequest);

        } else if (networkRequest.getUrl().equals("/panels/")) {
            handlePanels(networkRequest);

        } else if (networkRequest.getUrl().equals("/status/")) {
            handleStatus(networkRequest);

        }
    }
}
