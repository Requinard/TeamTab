package Game.adapters;

import Game.Player;
import Game.Team;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter {
    static Type playerListType = new TypeToken<List<Player>>() {}.getType();

    public static String toString(List<Player> players) {
        return JsonAdapter.toString(players, playerListType);
    }

    public static List<Player> toObjects(String input) {
        List<Player> list = (List<Player>) JsonAdapter.toObject(input, playerListType);
        return list;
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