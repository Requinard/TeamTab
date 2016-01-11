package networking.server;

import org.junit.Test;

/**
 * Edited on david on 4-1-16.
 */
public class NetworkRequestTest {

    @Test
    public void testGetNetworkMessage() throws Exception {

    }

    @Test
    public void testConstructor() throws Exception {
        String incomplete = "GET /help";
        NetworkMessage message = new NetworkMessage(incomplete, "", "");
        NetworkRequest request = new NetworkRequest(message);
    }
}