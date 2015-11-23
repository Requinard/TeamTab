package networking.mediator;

import networking.server.NetworkRequest;

/**
 * Created by David on 11/23/2015.
 */
public interface IMediator {
    void listen();

    void handlePlayers(NetworkRequest networkRequest);
    void handleInstruction(NetworkRequest networkRequest);

    void handleTeams(NetworkRequest networkRequest);
    void handlePanels(NetworkRequest networkRequest);
    void handleStatus(NetworkRequest networkRequest);
}