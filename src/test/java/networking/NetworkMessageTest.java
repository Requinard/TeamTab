package networking;

import com.pholser.junit.quickcheck.ForAll;
import org.junit.Before;
import org.junit.Test;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 11/17/2015.
 */
@RunWith(Theories.class)
public class NetworkMessageTest {
    private String text = "Test text";
    private String newtext = "new text";
    private String receiver = "Test receiver";
    private String sender = "Test sender";
    private NetworkMessage networkMessage;

    @Before
    public void setUp() throws Exception {
        networkMessage = new NetworkMessage(text, sender, receiver);
    }

    @Test
    public void testGetText() throws Exception {
        assertEquals(networkMessage.getText(), text);
    }

    @Theory
    public void testSetText(@ForAll String newText) throws Exception {
        networkMessage.setText(newText);
        assertEquals(networkMessage.getText(), newText);
    }

    @Test
    public void testGetSender() throws Exception {
        assertEquals(networkMessage.getSender(), sender);
    }

    @Test
    public void testSetSender() throws Exception {
        networkMessage.setSender(newtext);

        assertEquals(networkMessage.getSender(), newtext);
    }

    @Test
    public void testGetReceiver() throws Exception {
        assertEquals(networkMessage.getReceiver(), receiver);
    }

    @Test
    public void testSetReceiver() throws Exception {
        networkMessage.setReceiver(newtext);

        assertEquals(networkMessage.getReceiver(), newtext);
    }
}