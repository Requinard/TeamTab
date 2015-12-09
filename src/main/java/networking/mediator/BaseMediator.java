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
        this(8085);
    }

    public BaseMediator(int port) {
        try {
            networkServer = NetworkServerSingleton.getNetworkServer(port);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error opening server", e);
        }
    }

    public Thread mediate() {
        Thread thread = new Thread(() -> {
            listen();
        }, this.getClass().getName());
        thread.start();

        return thread;
    }

    public void listen() {
        while (true) {
            try {
                NetworkRequest networkRequest = networkServer.consumeRequest();
                //System.out.println(networkRequest);
                if (networkRequest != null)
                    handle(networkRequest);
            }
            catch(Exception ex){
                logger.log(Level.SEVERE, "Exception occured in mediator", ex);
            }
        }
    }

    private void handle(NetworkRequest networkRequest) throws Exception {
        if (networkRequest.getUrl().equals("/players/")) {
            handlePlayers(networkRequest);

        } else if (networkRequest.getUrl().equals("/instruction/")) {
            handleInstruction(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/")) {
            handleTeams(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/assign/")) {
            handleTeamsAssign(networkRequest);

            handleTeamsCreate(networkRequest);
        } else if (networkRequest.getUrl().equals("/teams/players/")) {
            handleTeamPlayers(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/create/")) {
            handleTeamsCreate(networkRequest);

        } else if (networkRequest.getUrl().equals("/panels/")) {
            handlePanels(networkRequest);

        } else if (networkRequest.getUrl().equals("/status/")) {
            handleStatus(networkRequest);

        }
    }
}
