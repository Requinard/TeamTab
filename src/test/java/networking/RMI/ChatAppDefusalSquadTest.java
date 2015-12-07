package networking.RMI;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatAppDefusalSquadTest {

    ChatAppDefusalSquad chatAppDefusalSquad;

    @Before
    public void setUp() throws Exception {
        chatAppDefusalSquad = new ChatAppDefusalSquad();
    }

    @Test
    public void testStartChatApp() throws Exception {
        Assert.assertEquals("Cannot start chatapp", true, chatAppDefusalSquad.startChatApp());
    }

    @Test
    public void testCloseChatApp() throws Exception {
        chatAppDefusalSquad.startChatApp();
        Assert.assertEquals("Cannot close chatApp", true, chatAppDefusalSquad.closeChatApp());
    }

    @Test
    public void testGetIpAddress() throws Exception {
        chatAppDefusalSquad.setIpAddress("127.0.0.1");
        Assert.assertEquals("Wrong ip address", "127.0.0.1", chatAppDefusalSquad.getIpAddress());
    }

    @Test
    public void testSetIpAddress() throws Exception {
        chatAppDefusalSquad.setIpAddress("127.0.0.1");
        Assert.assertEquals("Wrong ip address", "127.0.0.1", chatAppDefusalSquad.getIpAddress());
    }
}