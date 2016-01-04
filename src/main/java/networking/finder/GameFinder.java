package networking.finder;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin on 30-11-2015.
 */
public class GameFinder {
    private final int TIMEOUT = 10;
    ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * Finds all the hosts which are on the same subnet as the finder
     * Author: David
     * WORKS ONLY FOR MASKS OF 8
     *
     * @param list is the listview which will receive all the ip's of available games
     */
    public void findGames(ObservableList list) {
        // Get network interfaces
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // Loop through network interfaces
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            // Loop through interface addresses
            while (inetAddresses.hasMoreElements()) {
                InetAddress address = inetAddresses.nextElement();

                // If adress is IPv6 we skip
                if (address instanceof Inet6Address)
                    continue;

                String localHost;
                localHost = address.getHostAddress();

                // Prepare for subnetting
                String[] nibbles = localHost.split("/")[0].split("\\.");

                localHost = String.format("%s.%s.%s", nibbles[0], nibbles[1], nibbles[2]);

                if (nibbles[0].equals("127"))
                    continue;

                // Loop through all local subips
                for (int i = 1; i <= 255; i++) {
                    String host = String.format("%s.%s", localHost, i);

                    // Prepare funtion to execute
                    pool.execute(() -> {
                        SocketAddress sockaddr = new InetSocketAddress(host, 8085);
                        Socket socket = new Socket();
                        try {
                            socket.connect(sockaddr, TIMEOUT);

                            // If we're not finding our localhost it should be fine
                            if (!host.equals(address.getHostAddress().split("/")))
                                list.add(host);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }

        }
    }

    public boolean findSingleGame(String ipAddress) {
        boolean connectionValid = false;
        SocketAddress sockaddr = new InetSocketAddress(ipAddress, 8085);
        Socket socket = new Socket();
        try {
            socket.connect(sockaddr, 1000);
            connectionValid = true;
        } catch (IOException ex) {
            //unreachable host
        }
        return connectionValid;
    }
}
