package Game;

import gui.panel.AbstractPanelControl;

import java.util.List;

public class ClientGame implements IGame {

	private List<Player> players;
	private List<Team> teams;
	private List<Panel> panels;

	public ClientGame() {
		// TODO - implement ClientGame.ClientGame
		throw new UnsupportedOperationException();
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public List<Panel> getPanels() {
		return this.panels;
	}

	/**
	 * 
	 * @param name
	 */
	@Override
	public Team createTeam(String name) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param ip
	 */
	@Override
	public Player createPlayer(String username, String ip) {
		throw new UnsupportedOperationException();
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
		teams.stream().forEach(team -> team.generatePanels(panels));
		return this.panels;
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
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 * @param panel
	 */
	@Override
	public boolean processPanel(Player player, Panel panel) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	/**
	 * Takes an instruction and asserts whether the click was a valid instruction in your team.
	 * @param player Player that clicked on a panel
	 * @param panel Panel control that was clicked
	 */
	private boolean validateInstruction(Player player, AbstractPanelControl panel) {
		throw new UnsupportedOperationException();
	}

}