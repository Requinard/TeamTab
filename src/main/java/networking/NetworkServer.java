package networking;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Handles incoming requests and sends outgoing requests
 */
public class NetworkServer {

    private Queue<NetworkMessage> messageQueue = new LinkedBlockingQueue<>();
    /**
     * Port number
     */
    private int port = 80085;

    public NetworkServer(int port) {
        this.port = port;
    }

    public Queue<NetworkMessage> getMessageQueue() {
        return messageQueue;
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

    /**
     * Returns a message item
     */
    public NetworkMessage consumeMessage() {
        return messageQueue.poll();
    }

    /**
     * Returns a boolean that determines whether there are messages waiting
     */
    public boolean peek() {
        return messageQueue.peek() != null;
    }

}