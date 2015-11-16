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
	 * @param name
	 */
	Team createTeam(String name) throws IllegalFormatException;

	/**
	 * 
	 * @param username
	 * @param ip
	 */
	Player createPlayer(String username, String ip);

	/**
	 * 
	 * @param player
	 * @param team
	 */
	void assignTeam(Player player, Team team);

	/**
	 * 
	 * @param hard
	 */
	void reset(boolean hard);

	ClientGame startRound();

	/**
	 * 
	 * @param player
	 * @param panel
	 */
	boolean processPanel(Player player, Panel panel);

	boolean hasGameEnded();

	/**
	 * 
	 * @param instruction
	 */
	boolean registerInvalidInstruction(Instruction instruction);

}