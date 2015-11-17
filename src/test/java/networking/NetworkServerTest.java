package networking;

import junit.framework.TestCase;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Queue;

/**
 * Created by David on 11/16/2015.
 */
public class NetworkServerTest extends TestCase {

    private NetworkServer networkServer;

    public void setUp() throws Exception {
        super.setUp();
        networkServer = new NetworkServer(8085);
    }

    public void tearDown() throws Exception {

    }

    public void testGetPort() throws Exception {

    }

    public void testSetPort() throws Exception {

    }

    public void testListen() throws Exception {

    }

    public void testSend() throws Exception {

    }

    public void testConsumeMessage() throws Exception {
        // Test for no messages in queue
        assertNull("Object appeared in queue", networkServer.consumeMessage());

        // Test for message in queue
        NetworkMessage message = new NetworkMessage("Testing consumption", "sender", "receiver");
        networkServer.getMessageQueue().add(message);

        NetworkMessage consumedMessage = networkServer.consumeMessage();

        assertEquals(consumedMessage, message);

        // Test that there should no longer be any elements in queue

        assertNull("Objects still in queue", networkServer.consumeMessage());
    }

    public void testPeek() throws Exception {
        // Test for empty queue
        boolean peek = networkServer.peek();

        assertFalse("Peek managed to find items while there should be none", peek);

        // Test for a non-empty queue
        NetworkMessage message = new NetworkMessage("bla bla", "sender", "receiver");
        Queue<NetworkMessage> queue = networkServer.getMessageQueue();
        queue.add(message);

        peek = networkServer.peek();

        assertTrue("Peek did not return a value", peek);

        // empty the queue and then test again
        networkServer.consumeMessage();

        peek = networkServer.peek();

        assertFalse("Peek should no longer return any elements", peek);
    }

    public void testGetMessageQueue() throws Exception {

    }

    public void testStartListeners() throws Exception {
        final String sendMessage = "Dit is een testbericht";
        // Send a message
        networkServer.startListeners();

        Socket socket = new Socket("localhost", networkServer.getPort());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeBytes(sendMessage);

        dataOutputStream.close();
        socket.close();

        // Test to see if we received a message

        // sleep for a bit so that the connection is handled
        Thread.sleep(200);

        NetworkMessage message = networkServer.consumeMessage();

        assertNotNull("There was no message queued!", message);

        assertEquals(message.getText(), sendMessage);


    }

    public void testStopListeners() throws Exception {
        networkServer.stopListeners();
    }
}