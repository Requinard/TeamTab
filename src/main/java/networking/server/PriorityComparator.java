package networking.server;

import java.util.Comparator;

public class PriorityComparator implements Comparator<NetworkMessage> {
    public int compare(NetworkMessage firstMessage, NetworkMessage secondMessage) {
        if (firstMessage.isPriority() == false && secondMessage.isPriority() == true)
            return -1;
        else if (firstMessage.isPriority() == true && secondMessage.isPriority() == false)
            return 1;
        return 0;
    }
}
