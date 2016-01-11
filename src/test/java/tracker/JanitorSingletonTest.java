package tracker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Edited on david on 11-1-16.
 */
public class JanitorSingletonTest {

    @Test
    public void testGetInstance() throws Exception {
        Janitor instance = JanitorSingleton.getInstance();

        assertNotNull(instance);
    }
}