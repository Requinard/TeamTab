package networking.server;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Boolean> {
    @Override
    public int compare(Boolean aBoolean, Boolean t1) {
        if (aBoolean == false && t1 == true)
            return -1;
        else if (aBoolean == true && t1 == false)
            return 1;
        return 0;
    }
}
