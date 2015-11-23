package networking;

import junit.framework.TestCase;
import networking.server.NetworkMessage;
import networking.server.NetworkRequest;
import networking.server.NetworkServer;
import networking.server.RequestType;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Queue;

/**
 * Created by David on 11/16/2015.
 */
public class NetworkServerTest extends TestCase {

    private static NetworkServer networkServer;

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
        NetworkServer networkServer = new NetworkServer(8088);
        networkServer.startListeners();
        String message = "testmessage";

        networkServer.send(message, "localhost");

        Thread.sleep(200);

        NetworkMessage message1 = networkServer.consumeMessage();

        assertNotNull(message1);
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

        NetworkServer networkServer = new NetworkServer(8087);
        // Send a message
        networkServer.stopListeners();
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
        NetworkServer networkServer1 = new NetworkServer(8086);
        networkServer1.startListeners();
        networkServer1.stopListeners();
    }

    public void testConsumeRequest() throws Exception {
        // Create a message
        networkServer.stopListeners();
        networkServer.startListeners();
        NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, "/test", "payload");

        networkServer.send(networkRequest.toString(), "localhost");

        Thread.sleep(300);
        // consume the request
        NetworkRequest request = networkServer.consumeRequest();

        assertNotNull("There were no messages received", request);

        assertEquals("Incoming request type did not match the outgoing request type", request.getType(), networkRequest.getType());
        assertEquals("Incoming message did not match the outgoing message", request.toString(), networkRequest.toString());
    }

    public void testSendRequest() throws Exception {
        NetworkServer networkServer = new NetworkServer(8089);

        networkServer.stopListeners();
        networkServer.startListeners();
        NetworkRequest request = new NetworkRequest(RequestType.GET, "/test", "payload");

        assertTrue("Exception occurred during sending", networkServer.sendRequest(request, "localhost"));
    }
}