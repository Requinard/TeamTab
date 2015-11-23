package networking.server;

import java.io.IOException;

public class NetworkServerSingleton {

    private static NetworkServer networkServer;

    public static NetworkServer getNetworkServer() throws IOException {
        if (networkServer == null) {
            networkServer = new NetworkServer(8085);
        }

        networkServer.startListeners();

        return networkServer;
    }

}