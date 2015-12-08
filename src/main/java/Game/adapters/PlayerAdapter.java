package Game.adapters;

import Game.Player;
import Game.Team;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    public static Player makeSendable(Player player) {
       return null;
    }

    public static List<Player> makeSendable(List<Player> players) {
        List<Player> tempPlayer = new ArrayList<>();
        for (Player player : players) {
            Player player1 = new Player(player.getUsername(), player.getIp());
            player1.setTeam(new Team(player.getTeam().getName()));
            tempPlayer.add(player1);

        }
        return tempPlayer;
    }

}