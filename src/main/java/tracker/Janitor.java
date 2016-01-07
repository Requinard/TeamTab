package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;

/**
 * Created by David on 12/15/2015.
 */
public class Janitor {
    private List<Thread> threads;
    private List<ExecutorService> pools;
    private List<Timer> timers;

    public Janitor() {
        threads = new ArrayList<>();
        pools = new ArrayList<>();
        timers = new ArrayList<>();
    }


    /**
     * Makes the janitor watch a thread
     *
     * @param t thread to be watched
     */
    public void trackThread(Thread t) {
        synchronized (threads) {
            threads.add(t);
        }
    }

    /**
     * Retrieve all threadpools
     *
     * @return List of threadpools, not modifialbe
     */
    public List<ExecutorService> getPools() {
        return pools;
    }

    /**
     * Retrieve all threads
     *
     * @return A list of thread that is not modifiable
     */
    public List<Thread> getThreads() {
        return threads;
    }


    /**
     * Makes the janitor keep thread of a pool
     *
     * @param p Threadpool to be watched
     */
    public void trackPool(ExecutorService p) {
        synchronized (pools) {
            pools.add(p);
        }
    }

    /**
     * Makes the janitor keep track of a timer
     *
     * @param t Timer to keep track off
     */
    public void trackTimer(Timer t) {
        synchronized (timers) {
            timers.add(t);
        }
    }

    /**
     * Cleans house
     * will interrupt all threads and threadpools
     */
    public void clean() {
        threads.stream().forEach((Thread t) -> t.interrupt());
        pools.stream().forEach((ExecutorService e) -> e.shutdown());
        timers.stream().forEach((Timer t) -> t.cancel());
    }
}
