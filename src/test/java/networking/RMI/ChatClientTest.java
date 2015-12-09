package networking.RMI;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testGetName() throws Exception {
        Assert.assertEquals("The name is wrong", "Vito", chatClient.getName());
    }
}