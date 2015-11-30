package networking.finder;

import com.sun.javafx.UnmodifiableArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Kevin on 30-11-2015.
 */
public class GameFinder {
    ExecutorService pool = Executors.newFixedThreadPool(10);

    public void findGames(ObservableList list) {
        ObservableList openServers = list;

        String subnet = "192.168";
        int timeout = 100;
        int prefix = 223 ;
        
        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + prefix + "." + i;
            pool.execute(() -> {
                    SocketAddress sockaddr = new InetSocketAddress(host, 8085);
                    Socket socket = new Socket();
                    try{
                        socket.connect(sockaddr,timeout);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                openServers.add(host);
                            }
                        });
                    }
                    catch (IOException ex)
                    {
                        //unreachable host
                    }
            });
            if (prefix < 224 && i == 254) {
                i = 1;
                prefix++;
            }
        }
    }
}
