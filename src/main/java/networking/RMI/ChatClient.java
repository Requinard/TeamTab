package networking.RMI;

import javassist.bytecode.stackmap.TypeData;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatClient extends UnicastRemoteObject implements IChatClient {
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    // the name of the client
    private String name;
    //reference to the chatGUI
    private ChatUI chatUI;

    /**
     * The constructor of the class
     * Author Kamil
     *
     * @param name the name of the client
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    public ChatClient(String name) throws RemoteException {
        this.name = name;
    }

    /**
     * The GUI for this client will be set with this method
     * Author Kamil
     *
     * @param chatUI the GUI that will be for the user
     */
    public boolean setGUI(ChatUI chatUI) {
        log.log(Level.INFO, "ChatClient: gui is set for user " + name);
        this.chatUI = chatUI;
        return true;
    }

    /**
     * This method will tell the GUI to write te message that the client wants to say
     * Author Kamil
     *
     * @param tell the message that needs to be written to the GUI
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public boolean tell(String tell) throws RemoteException {
        log.log(Level.INFO, "ChatClient: " + name + " says " + tell);
        chatUI.writeMsg(tell);
        return true;
    }

    /**
     * This method will get the name of the client
     * Author Kamil
     *
     * @return the value of the name parameter
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    /**
     * This method will give a update to all user so that the new client will be visible in their connected user list
     * Author Kamil
     *
     * @param newUser the user that just joined
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    @Override
    public boolean updateUsers(Vector newUser) throws RemoteException {
        log.log(Level.INFO, "ChatClient: user updated: " + newUser.toString());
        chatUI.updateUsers(newUser);
        return true;
    }
}
