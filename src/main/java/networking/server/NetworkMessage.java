package networking.server;

public class NetworkMessage {

    private String text;
    private String sender;
    private String receiver;
    private boolean priority = false;

    public NetworkMessage(String text, String sender, String receiver) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    public boolean isPriority() {
        return priority;
    }

    public boolean setHighPriority() {
        return priority = true;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return this.text;
    }

}