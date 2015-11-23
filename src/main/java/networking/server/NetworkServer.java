package networking.server;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles incoming requests and sends outgoing requests
 */
public class NetworkServer {
    private static final Logger logger = Logger.getLogger(NetworkServer.class.getName());

    private Queue<NetworkMessage> messageQueue;
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private ServerSocket socket;
    /**
     * Port number
     */
    private int port = 80085;

    public NetworkServer(int port) {
        this.port = port;
        messageQueue = new ConcurrentLinkedQueue<>();
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
     * author: David
     * src: http://stackoverflow.com/questions/32941368/how-to-get-input-from-console-in-java-client-server-program
     */
    private void listen() {
        try {
            while (true) {
                // Accept incoming connections
                Socket clientSocket = this.socket.accept();

                String full = recv(clientSocket);

                // Read it into an object
                NetworkMessage networkMessage = new NetworkMessage(full, clientSocket.getInetAddress().toString(), clientSocket.getLocalSocketAddress().toString());

                logger.log(Level.INFO, "Server received the following data", networkMessage.getText());
                synchronized (this) {
                    messageQueue.add(networkMessage);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception on port", e);
        }
    }

    /**
     * Handles the input from a request
     * author David
     *
     * @param clientSocket Socket that has input waiting
     * @return string in buffer
     * @throws IOException Exception when socket stream cannot be read
     */
    @NotNull
    private String recv(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        String full = "";

        // Read the data into a string
        while ((line = bufferedReader.readLine()) != null) {
            full += line.trim();
            logger.log(Level.FINE, "Network packet received", line);
        }
        return full;
    }

    /**
     * Starts the thread listeners
     * author: David
     */
    public void startListeners() throws IOException {
        final int THREADPOOLSIZE = 4;

        stopListeners();

        logger.log(Level.INFO, "Starting listeners");

        // Initialize all variables
        executorService = Executors.newFixedThreadPool(THREADPOOLSIZE);
        messageQueue = new ConcurrentLinkedQueue<>();
        socket = new ServerSocket(port);

        // Create runnable
        Runnable worker = () -> listen();

        // Execute
        executorService.execute(worker);

        logger.log(Level.INFO, "Listeners have been started");
    }

    /**
     * Clears all local variables and stops listening
     * author: David
     */
    public void stopListeners() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Socket could not be closed", e);
        }
        executorService.shutdown();
        messageQueue.clear();
    }


    /**
     * Sends a message over networking
     * author: David
     *
     * @param message  Message that needs to be sent
     * @param receiver IP that a message will be sent to
     */
    public boolean send(String message, String receiver) {
        try {
            Socket socket = new Socket(receiver, this.getPort());

            // Get input streams
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeBytes(message);

            outputStream.close();
            socket.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sends a network request over the wire
     *
     * @param request  NetworkRequest object that needs to be sent
     * @param receiver IP of the receiving party
     * @return Success of the operation
     */
    public boolean sendRequest(NetworkRequest request, String receiver) {
        return send(request.toString(), receiver);
    }

    /**
     * Returns a message item
     * author: David
     *
     * @return NetworkMessage that was first in the queue. Null if there was none
     */
    public NetworkMessage consumeMessage() {
        logger.log(Level.INFO, "Network server is consuming a message");
        synchronized (this) {
            return messageQueue.poll();
        }
    }


    /**
     * Gets the latest network message as a request type
     * author: David
     *
     * @return The latest network request if there is one
     */
    public NetworkRequest consumeRequest() {
        NetworkMessage networkMessage = consumeMessage();
        if (networkMessage != null)
            return new NetworkRequest(networkMessage);
        else
            return null;
    }

    /**
     * Returns a boolean that determines whether there are messages waiting
     * author: David
     *
     * @return True if there's an object in the queue.
     */
    public boolean peek() {
        logger.log(Level.INFO, "Network server is peeking at the message queue");
        return messageQueue.peek() != null;
    }
}