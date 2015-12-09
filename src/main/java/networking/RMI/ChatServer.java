package networking.RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer {
    // logger for the class
    private static final Logger log = Logger.getLogger(ChatServer.class.getName());

    // vector object
    private Vector vectorsList = new Vector();

    /**
     * the construction for the class ChatServer
     * Author Kamil
     *
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    public ChatServer() throws RemoteException {

    }

    /**
     * This method will login an new chatclient
     * Author Kamil
     * @param chatClient the client that needs to be logged in
     * @return true if the login was succesfull
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public boolean login(IChatClient chatClient) throws RemoteException {
        log.log(Level.INFO, "ChatServer: " + chatClient.getName() + " is logging in");
        chatClient.tell("You have Connected successfully.");
        publish(chatClient.getName() + " has just connected.");
        vectorsList.add(chatClient);
        return true;
    }

    /**
     * This method will publish a given string to all the clients
     * Author Kamil     *
     * @param text the test message that needs to be published
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public void publish(String text) throws RemoteException {
        log.log(Level.INFO, "ChatServer: all the clients get a update of the connected client");
        log.log(Level.INFO, "ChatServer: " + text);
        for (int i = 0; i < vectorsList.size(); i++) {
            try {
                IChatClient tmp = (IChatClient) vectorsList.get(i);
                tmp.tell(text);
            } catch (Exception e) {
                log.log(Level.INFO, "ChatClient: cant publish message " + e.getMessage());
            }
        }
    }

    /**
     * this method will keep track of all the connected vectors
     * Author Kamil
     *
     * @return the new vector that just got connected
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public Vector getConnected() throws RemoteException {
        log.log(Level.INFO, "ChatServer: getting all the connected vectors");

        for (int i = 0; i < vectorsList.size(); i++) {
            try {
                IChatClient tmp = (IChatClient) vectorsList.get(i);
                tmp.updateUsers(vectorsList);
            } catch (Exception e) {
                log.log(Level.INFO, "ChatClient: could not get all the vectors " + e.getMessage());
            }
        }
        return vectorsList;
    }

    /**
     * This method will remove a chatclient that lefts the chat and update the remaining clients
     * Author Kamil
     *
     * @param chatClient that needs to be logged out
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public void logOut(IChatClient chatClient) throws RemoteException {
        log.log(Level.INFO, "ChatServer: logged out " + chatClient.getName());
        publish(chatClient.getName() + " has disconnected.");
        vectorsList.remove(chatClient);
        getConnected();
    }
}
