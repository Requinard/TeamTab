package Game.adapters;

import Game.Player;

import java.util.List;

public class PlayerAdapter {

    public static String toString(List<Player> players) {
        return JsonAdapter.toString(players, List.class);
    }

    public static List<Player> toObjects(String input) {
        return (List<Player>) JsonAdapter.toObject(input, List.class);
    }

    /**
     * @param player
     */
    public String toString(Player player) {
        // TODO - implement PlayerAdapter.toString
        throw new UnsupportedOperationException();
    }

    /**
     * @param input
     */
    public Player toObject(String input) {
        // TODO - implement PlayerAdapter.toObject
        throw new UnsupportedOperationException();
    }

}