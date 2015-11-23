package Game.adapters;

import Game.HostGame;

public class HostGameAdapter {


    public HostGame toObject(String input) {
        return (HostGame) JsonAdapter.toObject(input, HostGame.class);
    }

    /**
     * @param game
     */
    public String ToString(HostGame game) {
        return JsonAdapter.toString(game, HostGame.class);
    }

}