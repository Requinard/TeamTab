package Game;

import java.util.*;
import gui.panel.*;

public class ClientGame implements IGame {

	private Collection<Player> players;
	private Collection<Team> teams;
	private Collection<Panel> panels;

	public Collection<Player> getPlayers() {
		return this.players;
	}

	public Collection<Team> getTeams() {
		return this.teams;
	}

	public Collection<Panel> getPanels() {
		return this.panels;
	}

	public ClientGame() {
		// TODO - implement ClientGame.ClientGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	@Override
	public Team createTeam(String name) {
		return null;
		//todo: methode invullen
	}

	/**
	 * 
	 * @param username
	 * @param ip
	 */
	@Override
	public Player createPlayer(String username, String ip) {
		return null;
		//todo: methode invullen
	}

	/**
	 * 
	 * @param player
	 * @param team
	 */
	@Override
	public void assignTeam(Player player, Team team) {

	}

	private java.util.List<Panel> generatePanels() {
		return null;
		//todo: methode invullen
	}

	private void loadPanelsFromFile() {
		// TODO - implement ClientGame.loadPanelsFromFile
		throw new UnsupportedOperationException();
	}

	/**
	 * Resets lives, time and instructions for all teams.
	 * @param hard Indicates that this is a hard reset. Destroy all data
	 */
	@Override()
	public void reset(boolean hard) {
		// TODO - implement ClientGame.reset
		throw new UnsupportedOperationException();
	}

	@Override
	public ClientGame startRound() {
		return null;
		//todo: methode invullen
	}

	/**
	 * 
	 * @param player
	 * @param panel
	 */
	@Override
	public boolean processPanel(Player player, Panel panel) {
		return false;
		//todo: methode invullen
	}

	@Override()
	public boolean hasGameEnded() {
		// TODO - implement ClientGame.hasGameEnded
		throw new UnsupportedOperationException();
	}

	/**
	 * Takes an instruction and marks it as invalid, thus generating a new instruction for a player
	 * @param instruction The instruction that needs to be marked as invalid
	 */
	@Override
	public boolean registerInvalidInstruction(Instruction instruction) {
		return false;
		//todo: methode invullen
	}

	/**
	 * Takes an instruction and asserts whether the click was a valid instruction in your team.
	 * @param player Player that clicked on a panel
	 * @param panel Panel control that was clicked
	 */
	private boolean validateInstruction(Player player, AbstractPanelControl panel) {
		return false;
		//todo: methode invullen
	}

}