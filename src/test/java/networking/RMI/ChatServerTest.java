package networking.RMI;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatServerTest {

    ChatServer chatServer;
    ChatClient chatClient;
    ChatUI chatUI;

    @Before
    public void setUp() throws Exception {
        chatServer = new ChatServer();
        chatClient = new ChatClient("Vito");
        chatUI = new ChatUI("127.0.0.1");
    }

    @Test
    public void testLogin() throws Exception {
        chatClient.setGUI(chatUI);
        Assert.assertEquals("Could not login the chatclient", true, chatServer.login(chatClient));
    }

    @Test
    public void testGetConnected() throws Exception {
        Vector vector = new Vector();
        chatClient.setGUI(chatUI);
        chatClient.updateUsers(vector);
        Assert.assertEquals("Could not get the connected", vector, chatServer.getConnected());
    }
}