package tracker;

/**
 * Created by David on 12/15/2015.
 */
public class JanitorSingleton {
    private static Janitor janitor;

    public static Janitor getInstance() {
        if (janitor == null) janitor = new Janitor();

        return janitor;
    }
}
