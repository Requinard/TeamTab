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


    /**
     * Author Kamil Wasylkiewicz
     *
     * @param player the player that needs to be made sendable
     * @return the reference to the player
     */
    public static Player makeSendable(Player player) {
        Player tempPlayer = new Player(player.getUsername(), player.getIp());
        tempPlayer.setTeam(new Team(player.getTeam().getName()));
        return tempPlayer;
    }

    public static List<Player> makeSendable(List<Player> players) {
        List<Player> tempPlayer = new ArrayList<>();
        for (Player player : players) {
            Player player1 = new Player(player.getUsername(), player.getIp());
            Team team = new Team(player.getTeam().getName());

            player1.setTeam(team);
            player1.setPlayerStatus(player.getPlayerStatus());
            tempPlayer.add(player1);
        }
        return tempPlayer;
    }

}