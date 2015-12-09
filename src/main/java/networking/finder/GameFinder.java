package networking.finder;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin on 30-11-2015.
 */
public class GameFinder {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    private final int TIMEOUT = 10;

    /**
     * Finds all the hosts which are on the same subnet as the finder
     * Author: David
     * WORKS ONLY FOR MASKS OF 8
     *
     * @param list is the listview which will receive all the ip's of available games
     */
    public void findGames(ObservableList list) {
        ObservableList openServers = list;
        String localHost;
        try {
            localHost = Inet4Address.getLocalHost().toString();
        } catch (UnknownHostException e) {
            localHost = "192.168.0.1";
        }


        // Prepare for subnetting
        String[] nibbles = localHost.split("/")[1].split("\\.");

        localHost = String.format("%s.%s.%s",nibbles[0], nibbles[1], nibbles[2]);

        for (int i = 1; i <= 255; i++) {
            String host = String.format("%s.%s", localHost, i);
            pool.execute(() -> {
                SocketAddress sockaddr = new InetSocketAddress(host, 8085);
                Socket socket = new Socket();
                try {
                    socket.connect(sockaddr, TIMEOUT);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            openServers.add(host);
                        }
                    });
                } catch (IOException ex) {
                    //unreachable host
                }
            });
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
