package Game;

import java.util.Collection;
import java.util.IllegalFormatException;

/**
 * Created by David on 11/16/2015.
 */
public interface IGame {

	Collection<Player> getPlayers();

	Collection<Team> getTeams();

	Collection<Panel> getPanels();

	/**
	 *
	 * @param name the name of the team
	 */
	Team createTeam(String name) throws IllegalFormatException;

	/**
	 *
	 * @param username the username of the player
	 * @param ip the ip adres of the player
	 */
	Player createPlayer(String username, String ip);

	/**
	 *
	 * @param player the player that will be assigned to the team
	 * @param team the team that will get the player
	 */
	void assignTeam(Player player, Team team);

	/**
	 *
	 * @param hard if true than hardreset
	 */
	void reset(boolean hard);

	HostGame startRound();

	/**
	 *
	 * @param player the player of which the panel has been changed
	 * @param panel the panel that is pressed
	 */
	boolean processPanel(Player player, Panel panel);

	boolean hasGameEnded();

	/**
	 *
	 * @param instruction the instruction that was to late
	 * @param player the player that was to late with the instruction
	 */
	boolean registerInvalidInstruction(Player player, Instruction instruction);

}