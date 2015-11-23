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
    public static String toString(Player player) {
        return JsonAdapter.toString(player, Player.class);
    }

    /**
     * @param input
     */
    public static Player toObject(String input) {
        return (Player) JsonAdapter.toObject(input, Player.class);
    }

}