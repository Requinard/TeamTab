package networking.server;

import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkRequest {

    private RequestType type;
    private String url;
    private String payload;
    private NetworkMessage networkMessage;

    /**
     * Generates a network request that can be sent over the wire
     *
     * @param type    Method verb of the request
     * @param url     location of the resource requested
     * @param payload Data that is to be sent along
     */
    public NetworkRequest(RequestType type, String url, String payload) {
        this.type = type;
        this.url = url;
        this.payload = payload;

        String s = String.format("%s %s %s", type, url, payload);

        networkMessage = new NetworkMessage(s, "localhost", "localhost");
    }

    /**
     * Generates a Request from a message. This request can be interpreted
     *
     * @param networkMessage Message that was received by the server
     */
    public NetworkRequest(NetworkMessage networkMessage) throws UnknownFormatConversionException {
        this.networkMessage = networkMessage;

        // Parse the message
        Pattern p = Pattern.compile("(GET|POST|SEND) ([\\w/]+) (.+)");

        Matcher matcher = p.matcher(networkMessage.getText());

        if (matcher.matches() == false)
            throw new UnknownFormatConversionException("Network request could not be parsed!");

        switch (matcher.group(0)) {
            case "POST":
                this.type = RequestType.POST;
                break;
            case "SEND":
                this.type = RequestType.SEND;
                break;
            default:
                this.type = RequestType.GET;
                break;
        }

        this.url = matcher.group(1);
        this.payload = matcher.group(2);
    }

    public NetworkMessage getNetworkMessage() {
        return networkMessage;
    }

    public RequestType getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPayload() {
        return this.payload;
    }

    @Override
    public String toString() {
        return this.networkMessage.toString();
    }
}