package networking.mediator;

import networking.server.NetworkRequest;
import sun.nio.ch.Net;

/**
 * Created by David on 11/23/2015.
 */
public interface IMediator {
    void listen();

    void handlePlayers(NetworkRequest networkRequest);
    void handleInstruction(NetworkRequest networkRequest);
    void handleTeam(NetworkRequest networkRequest);
    void handlePanels(NetworkRequest networkRequest);
    void handleStatus(NetworkRequest networkRequest);
}
