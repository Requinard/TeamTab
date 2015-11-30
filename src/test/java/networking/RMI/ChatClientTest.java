package networking.RMI;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatClientTest {

    private ChatClient chatClient;
    private ChatUI chatUI;
    @Before
    public void setUp() throws Exception {
        chatClient = new ChatClient("Vito");
        chatUI = new ChatUI("127.0.0.1");
    }

    @Test
    public void testSetGUI() throws Exception {
        Assert.assertEquals("Could not set the gui for the chatclient", true, chatClient.setGUI(chatUI));
    }

    @Test
    public void testTell() throws Exception {
        chatClient.setGUI(chatUI);
        Assert.assertEquals("Chatclient can't tell what he wants", true, chatClient.tell("Hi there, i'm a test"));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("The name is wrong", "Vito", chatClient.getName());
    }

    @Test
    public void testUpdateUsers() throws Exception {
        Vector vector = new Vector<>();
        chatClient.setGUI(chatUI);
        Assert.assertEquals("Could not set the gui for the chatclient", true, chatClient.updateUsers(vector));
    }
}