package Game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.List;

public class ClientGame implements IGame {
    @Override
    public Collection<Player> getPlayers() {
        return null;
    }

    public void setPlayers(List<Player> players) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Team> getTeams() {
        return null;
    }

    @Override
    public Collection<Panel> getPanels() {
        return null;
    }

    /**
     * @param name the name of the team
     */
    @Override
    public Team createTeam(String name) throws IllegalFormatException {
        return null;
    }

    /**
     * @param username the username of the player
     * @param ip       the ip adres of the player
     */
    @Override
    public Player createPlayer(String username, String ip) {
        return null;
    }

    /**
     * @param player the player that will be assigned to the team
     * @param team   the team that will get the player
     */
    @Override
    public void assignTeam(Player player, Team team) {

    }

    /**
     * @param hard if true than hardreset
     */
    @Override
    public void reset(boolean hard) {

    }

    @Override
    public HostGame startRound() {
        return null;
    }

    /**
     * @param player the player of which the panel has been changed
     * @param panel  the panel that is pressed
     */
    @Override
    public boolean processPanel(Player player, Panel panel) {
        return false;
    }

    @Override
    public boolean hasGameEnded() {
        return false;
    }

    /**
     * @param instruction the instruction that was to late
     */
    @Override
    public void registerInvalidInstruction(Instruction instruction) {
    }
}