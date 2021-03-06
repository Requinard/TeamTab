package networking.mediator;

import networking.server.NetworkRequest;
import networking.server.NetworkServer;
import networking.server.NetworkServerSingleton;
import tracker.JanitorSingleton;

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

        JanitorSingleton.getInstance().trackThread(thread);

        return thread;
    }

    public void listen() {
        while (true) {
            NetworkRequest networkRequest = networkServer.consumeRequest();
            if (networkRequest != null) {
                //System.out.println(networkRequest);
                handle(networkRequest);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Thread.yield();
        }
    }

    private void handle(NetworkRequest networkRequest) {
        if (networkRequest.getUrl().equals("/players/")) {
            handlePlayers(networkRequest);

        } else if (networkRequest.getUrl().equals("/instruction/")) {
            handleInstruction(networkRequest);

        } else if (networkRequest.getUrl().equals("/scoreboard/")) {
            handleScoreBoard(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/")) {
            handleTeams(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/assign/")) {
            handleTeamsAssign(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/players/")) {
            handleTeamPlayers(networkRequest);

        } else if (networkRequest.getUrl().equals("/teams/create/")) {
            handleTeamsCreate(networkRequest);

        } else if (networkRequest.getUrl().equals("/panels/")) {
            handlePanels(networkRequest);

        } else if (networkRequest.getUrl().equals("/status/")) {
            handleStatus(networkRequest);

        } else if (networkRequest.getUrl().equals("/players/changeStatus/")) {
            handlePlayersChangeStatus(networkRequest);

        }
    }
}
