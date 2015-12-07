package networking.RMI;

import javassist.bytecode.stackmap.TypeData;

import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatAppDefusalSquad {

    // logger for events
    private static final Logger log = Logger.getLogger(TypeData.ClassName.class.getName());

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for chat application
    private static final String bindingName = "chatApplication";

    // References to registry and chatserver
    private Registry registry;
    private IChatServer chatServer;

    // reference to ip adress
    private String ipAddress;
    //reference to the Chat GUI
    private ChatUI chatUI;

    /**
     * The construction of the class
     * Author Kamil
     *
     * @throws UnknownHostException          if the server of the ipaddress is not known
     * @throws java.net.UnknownHostException if the server of the ipaddress is not known
     */
    public ChatAppDefusalSquad() throws UnknownHostException, java.net.UnknownHostException {
        log.log(Level.INFO, "ChatAppDefusalSquad: initialized");
        log.log(Level.INFO, "ChatAppDefusalSquad: Portnumber for RMI is " + portNumber);
    }

    /**
     * This method will create a new chatserver and try to bind this to a registry
     * Author Kamil
     */
    public void createAndBindRegistry() {

        // create instance of chatserver
        try {
            chatServer = new ChatServer();
            log.log(Level.INFO, "ChatAppDefusalSquad: instance of chat application created");

        } catch (RemoteException ex) {
            log.log(Level.INFO, "ChatAppDefusalSquad: Cannot create chat instance");
            log.log(Level.INFO, "ChatAppDefusalSquad: RemoteException: " + ex.getMessage());
            chatServer = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            log.log(Level.INFO, "ChatAppDefusalSquad: Registry created on port number " + portNumber);

        } catch (RemoteException ex) {
            log.log(Level.INFO, "ChatAppDefusalSquad: Cannot create registry");
            return;
        }

        // Bind chat using registry
        try {
            registry.rebind(bindingName, chatServer);
            log.log(Level.INFO, "ChatAppDefusalSquad: chatserver is binded");
        } catch (RemoteException ex) {
            log.log(Level.INFO, "ChatAppDefusalSquad: Cannot bind chatserver\"");
            log.log(Level.INFO, "ChatAppDefusalSquad: RemoteException: " + ex.getMessage());
            return;
        }
    }

    /**
     * this method will start the chat GUI
     * Author Kamil
     */
    public boolean startChatApp() {
        log.log(Level.INFO, "ChatAppDefusalSquad: statapplication is starting");
        chatUI = new ChatUI(ipAddress);
        return true;
    }

    /**
     * This method will call the close GUI method
     * Author Kamil
     */
    public boolean closeChatApp() {
        log.log(Level.INFO, "ChatAppDefusalSquad: chatapplication is closing");
        return chatUI.closeChatApp();
    }

    /**
     * this method will return the IP address that is set for this connection
     * Authhor Kamil
     *
     * @return the ipaddress that is set
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * this method will set the ipaddress
     * Author Kamil
     *
     * @param ipAddress the new ipaddress
     */
    public void setIpAddress(String ipAddress) {
        log.log(Level.INFO, "ChatAppDefusalSquad: ipaddress is set to: " + ipAddress);
        this.ipAddress = ipAddress;
    }


}

