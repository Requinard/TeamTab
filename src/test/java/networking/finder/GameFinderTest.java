package networking.finder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

/**
 * Edited on david on 4-1-16.
 */
public class GameFinderTest {

    @Test
    public void testFindGames() throws Exception {
        GameFinder gameFinder = new GameFinder();

        ObservableList<String> list = FXCollections.observableArrayList();
        gameFinder.findGames(list);
    }

    @Test
    public void testFindSingleGame() throws Exception {

    }
}