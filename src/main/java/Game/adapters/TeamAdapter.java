package Game.adapters;

import Game.Team;

import java.util.List;

public class TeamAdapter {

    public String toString(Team team) {
        return JsonAdapter.toString(team, Team.class);
    }

    public String toString(List<Team> teamList) {
        return JsonAdapter.toString(teamList, List.class);
    }

    /**
     * @param input
     */
    public Team toObject(String input) {
        return (Team) JsonAdapter.toObject(input, Team.class);
    }

    public List<Team> toObjects(String input) {
        return (List<Team>) JsonAdapter.toObject(input, List.class);
    }
}