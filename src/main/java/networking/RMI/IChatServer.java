package networking.RMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by Vito Corleone on 28-11-2015.
 */
public interface IChatServer extends Remote {

    /**
     * This method will login the new chatclient
     * Author Kamil
     *
     * @param chatClient the chatclient that needs to be logged in
     * @return true if the login was succeful
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    boolean login(IChatClient chatClient) throws RemoteException;

    /**
     * this method will publish a text to all the other chatclients
     * Author Kamil
     *
     * @param text that needs to be published     *
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    void publish(String text) throws RemoteException;

    /**
     * this method will connect a new vector to all the other connected vectors and return all the vectors
     * Author Kamil
     *
     * @return all the connected vectors
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    Vector getConnected() throws RemoteException;

    /**
     * this method will logout the chatclient
     * Author Kamil
     *
     * @param chatClient that needs to be logged out
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    void logOut(IChatClient chatClient) throws RemoteException;
}
