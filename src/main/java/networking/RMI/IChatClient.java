package networking.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * Created by Vito Corleone on 28-11-2015.
 */
public interface IChatClient extends Remote {

    /**
     * This method will tell the name of the client that is joined
     * Author Kamil
     *
     * @param name the name of the client
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    void tell(String name) throws RemoteException;

    /**
     * this method will return the name of the client
     * Author Kamil
     *
     * @return the name of the client
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    String getName() throws RemoteException;

    /**
     * this method will update all the users with the new user
     * Author Kamil
     *
     * @param newUser the new user that joined
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    void updateUsers(Vector newUser) throws RemoteException;
}
