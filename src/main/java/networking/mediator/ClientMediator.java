package networking.mediator;

import Game.ClientGame;
import Game.Player;
import Game.adapters.PlayerAdapter;
import networking.server.NetworkRequest;

import java.util.List;

/**
 * Created by David on 11/23/2015.
 */
public class ClientMediator extends BaseMediator implements IMediator {
    ClientGame clientGame;

    public ClientMediator(ClientGame clientGame) {
        super();
        this.clientGame = clientGame;
    }


    @Override
    public void handlePlayers(NetworkRequest networkRequest) {
        List<Player> players = PlayerAdapter.toObjects(networkRequest.getPayload());

        clientGame.setPlayers(players);
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
