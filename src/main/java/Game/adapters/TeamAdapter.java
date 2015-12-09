package Game.adapters;

import Game.Team;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TeamAdapter {

    public static String toString(Team team) {
        return JsonAdapter.toString(team, Team.class);
    }

    public static String toString(List<Team> teamList) {
        return JsonAdapter.toString(teamList, List.class);
    }

    /**
     * @param input
     */
    public static Team toObject(String input) {
        return (Team) JsonAdapter.toObject(input, Team.class);
    }

    public static List<Team> toObjects(String input) {
        Type teamListType = new TypeToken<List<Team>>(){}.getType();
        return (List<Team>) JsonAdapter.toObject(input, teamListType);
    }

    /**
     * Author Kamil Wasylkiewicz
     *
     * @param team the team that needs to be made sendable
     * @return a reference to the team
     */
    public static Team makeSendable(Team team) {

        Team tempTeam = new Team(team.getName(), team.getLives(), team.getTime(), team.getScore());
        return tempTeam;
    }

    /**
     * Author Kamil Wasylkiewicz
     * @param teams the teams that needs to be made sendable
     * @return the list of references to the teams
     */
    public static List<Team> makeSendable(List<Team> teams) {
        List<Team> tempTeam = new ArrayList<>();

        for (Team team : teams) {
            tempTeam.add(new Team(team.getName(), team.getLives(), team.getTime(), team.getScore()));
        }
        return tempTeam;
    }
}