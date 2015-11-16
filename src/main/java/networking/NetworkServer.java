package networking;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * Handles incoming requests and sends outgoing requests
 */
public class NetworkServer {

    Queue<NetworkMessage> messageQueue = new SynchronousQueue<>();
    /**
     * Port number
     */
    private int port = 80085;

    public NetworkServer(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Listens for incoming network requests and synchronises them to the main thread
     */
    public void listen() {
        // TODO - implement NetworkServer.listen
        throw new UnsupportedOperationException();
    }

    /**
     * @param message  Message that needs to be sent
     * @param receiver IP that a message will be sent to
     */
    public boolean send(String message, String receiver) {
        // TODO - implement NetworkServer.send
        throw new UnsupportedOperationException();
    }

}