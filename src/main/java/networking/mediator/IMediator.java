package networking.mediator;

import networking.server.NetworkRequest;

/**
 * Created by David on 11/23/2015.
 */
public interface IMediator {
    void listen();

    void handlePlayers(NetworkRequest networkRequest);
}
