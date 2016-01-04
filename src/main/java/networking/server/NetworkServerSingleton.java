package networking.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NetworkServerSingleton {
    private static Map<Integer, NetworkServer> servers;

    public static NetworkServer getNetworkServer() throws IOException {
        return getNetworkServer(8085);
    }

    public static NetworkServer getNetworkServer(int port) throws IOException {
        if (servers == null)
            servers = new HashMap<>();

        if (servers.containsKey(port)) {
            return servers.get(port);
        } else {
            NetworkServer server = new NetworkServer(port);
            server.startListeners();
            servers.put(port, server);
            return server;
        }
    }


    public static void stopAllServers() {
        if (servers != null) {
            for (NetworkServer server : servers.values()) {
                server.stopListeners();
            }
        }
    }


}