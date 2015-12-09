package networking.RMI;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatUITest {

    ChatUI chatUI;

    @Before
    public void setUp() throws Exception {
        chatUI = new ChatUI("127.0.0.1");
    }

    @Test
    public void testCloseChatApp() throws Exception {
        Assert.assertEquals("Could not close the gui", true, chatUI.closeChatApp());
    }
}