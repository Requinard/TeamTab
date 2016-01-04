package Game.adapters;

import Game.Player;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PlayerAdapter {

    public static String toString(List<Player> players) {
        return JsonAdapter.toString(players, List.class);
    }

    public static List<Player> toObjects(String input) {
        Type playerListType = new TypeToken<List<Player>>() {}.getType();
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