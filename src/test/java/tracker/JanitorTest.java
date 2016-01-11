package tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

/**
 * Edited on david on 11-1-16.
 */
public class JanitorTest {
    private Janitor janitor;

    @Before
    public void setUp() {
        janitor = JanitorSingleton.getInstance();
    }

    @Test
    public void testTrackThread() throws Exception {
        Thread t = new Thread(() -> System.out.println("new thread"));

        janitor.trackThread(t);
    }

    @Test
    public void testGetPools() throws Exception {
        testTrackPool();

        assertTrue(janitor.getPools().size() > 0);
    }

    @Test
    public void testGetThreads() throws Exception {
        testTrackThread();

        assertTrue(janitor.getThreads().size() > 0);
    }

    @Test
    public void testTrackPool() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        janitor.trackPool(pool);
    }

    @Test
    public void testTrackTimer() throws Exception {
        Timer t = new Timer("test");

        janitor.trackTimer(t);
    }

    @Test
    public void testClean() throws Exception {
        testTrackPool();
        testTrackThread();
        testTrackTimer();

        janitor.clean();
    }
}