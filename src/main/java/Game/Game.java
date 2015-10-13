package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by HP user on 12-10-2015.
 */
public class Game {
    private ArrayList<Team> teams;
    private ArrayList<Player> players;
    private ArrayList<Instruction> instructions;
    private ArrayList<Panel> panels;
    private ArrayList<String> playerScores;

    Team team1 = new Team();
    Team team2 = new Team();
    private int timeRound;
    private int bonusCorrectInstructions;
    private int substractCorrectInstructions;

    public Game(){
        teams = new ArrayList<Team>();
        players = new ArrayList<Player>();
        instructions = new ArrayList<Instruction>();
        panels = new ArrayList<Panel>();

        teams.add(team1);
        teams.add(team2);

        // Standaard tijd voor een ronde
        timeRound = 9;
        bonusCorrectInstructions = 3;
        substractCorrectInstructions = 5;

    }

    /**
     * Het starten van de game wordt aan het begin van het spel aangeroepen
     * Beide teams krijgen vanaf hier ook panels aangewezen
     */
    public void startGame(){
        // Kijkt of beide teams even veel spelers hebben
        if (team1.getPlayers().size() == team2.getPlayers().size()) {
            // Geeft de teams die meedoen panels
            getAllPlayerPanels(team1);
            getAllPlayerPanels(team2);
        }

    }

    /**
     * Een nieuwe ronde dient gestart te worden wanneer een van de twee teams een leven verliest
     * Alle waardes in de game worden dan vervolgens gereset
     */
    public void newRound(){
        // Terug zetten van standaard waardes
        reset();
    }

    /**
     * Nog te maken: Het returnen van een lijst me alle scores
     * Aan het einde van een game worden de spelers gesorteerd op score die ze behaald hebben
     */
    public void endGame(){

        // Sorteren van de spelers op score
        try {
            Collections.sort(players, new Comparator<Player>() {
                @Override
                // Logica achter het sorteren
                public int compare(Player o1, Player o2) {
                    return Integer.compare(o1.getScore(), o2.getScore());
                }
            });
        }

        catch (Exception ex) {
            // Geef een messagebox met een error
        }

        // Lijst met spelers + de score wordt gevuld
        for (Player p : players){
            // Scores opslaan in een lijst met namen + de score
            playerScores.add(p.getName() + ": " + p.getScore());
        }
    }

    /**
     * Tijdens het joinen van een game wordt de speler in het team gezet waar de minste spelers in zitten
     * Wanneer dit gelijk is wordt hij in het tweede team gezet
     * @param player De speler waarvan het team bepaald moet worden
     */
    public void addPlayerToTeam(Player player){
        // Automatisch toevoegen van spelers aan een team wanneer ze de lobby joinen
        if(teams.get(0).getPlayers().size() <= teams.get(1).getPlayers().size())
        {
            addPlayer(player, team1);
        }else {
            addPlayer(player, team2);
        }
    }

    /**
     * Wanneer de speler van team wilt veranderen wordt hij van het team waar hij nu in zit verplaats naar het andere team
     * @param player De speler waarvan het team verandert moet worden
     */
    public  void changeTeam(Player player){

        if (team1.getPlayers().contains(player)) {
            // Speler wordt toegevoegd aan team 2
            team1.getPlayers().remove(player);
            team2.getPlayers().add(player);
        }

        else {
            // Speler wordt toegevoegd aan team 1
            team2.getPlayers().remove(player);
            team1.getPlayers().add(player);
        }


    }

    // TEAM METHODS
    // Alle functionaliteit van een Team is verplaats naar de game
    // Onderstaande methodes bevat functionaliteit die over gezet is


    /**
     * Wanneer een team een bepaalde winstreak behaald heeft krijgt het een seconden extra voor de volgende instructies
     * @param tm Het team waar tijd aan toegevoegd dient te worden
     */
    private void addTime(Team tm){

        if (tm.getCorrectInstruction() == bonusCorrectInstructions){
            // Toevoegen van een seconden
            tm.setTime(tm.getTime() + 1);
            resetCorrectInstruction(tm);
        }
    }

    /**
     * Wanneer een team minder dan 3 seconden heeft om instructies uit te voeren heeft het team verloren
     * @param losingTeam het team waarvan er gekeken wordt of ze nog genoeg tijd hebben
     */
    public void subtractLives(Team losingTeam){
        if (losingTeam.getTime() <= 3){
            losingTeam.setLives(losingTeam.getLives() - 1);
            if (losingTeam.getLives() <= 0)
                // Spel is voorbij een team heeft geen levens meer over
                endGame();
            else
                // Team heeft een leven verloren er moet een nieuwe ronde gestart worden
                newRound();
        }
    }

    /**
     * Het team dat een bepaalde hoeveelheid instructies voltooid heeft zorgt ervoor dat de tegenstanders minder tijd hebben voor de volgende ronde
     * @param currentTeam het team dat genoeg correcte instructies zou hebben
     */
    public void subtractTime(Team currentTeam){

        for (Team t : teams) {
            // Checken of het teamm genoeg correcte antwoorden behaald heeft
            if (!t.equals(currentTeam) && currentTeam.getCorrectInstruction() == substractCorrectInstructions){
                Team otherTeam = t;
                otherTeam.setTime(otherTeam.getTime() - 1);
                subtractLives(otherTeam);
                break;
            }
        }

    }

    /**
     * Het toevoegen van een speler aan een team
     * IS DEZE NOG RELEVANT????????????????????????
     * @param p
     * @param t
     */
    public void addPlayer(Player p, Team t){
        t.getPlayers().add(p);
    }

    // NOG OP VERANDERING WACHTEN VAN QUNFONG
    public void addCorrectInstruction(Panel donePanel, Player player){
        Team t = player.getTeam();
        int currentCorrect = t.getCorrectInstruction();


        for (Player p : t.getPlayers()){

            if (p.getInstructions().getPanel() == donePanel) {
                t.setCorrectInstruction(currentCorrect + 1);
                givePlayerInstructions(p);
                addTime(t);
            }
            //player instruction controleren op doorgegeven Paneel
        }
        if (!(currentCorrect < t.getCorrectInstruction())){
            resetCorrectInstruction(t);
        }
    }

    /**
     * Wanneer de instructies een bepaalde hoeveelheid behaald hebben dienen ze gerest te worden
     * @param t Het team waarvan het gecontroleerd dient te worden
     */
    private void resetCorrectInstruction(Team t) {
        if (t.getCorrectInstruction() >= substractCorrectInstructions)
            t.setCorrectInstruction(0);
    }

    /**
     * Zet voor beide teams alles terug op de standaard waarde
     */
    private void reset(){
        // Voor alle teams de waardes naar standaard terug zetten
        for (Team t : teams) {
            // Resetten van de tijd naar 9 seconden
            t.setTime(timeRound);
            // Het aantal correcte instructies terugzetten op 0
            t.setCorrectInstruction(0);
        }
    }

    /**
     * Haalt per team alle panels op die bij de spelers van dat team horen
     * @param t het team waarvoor de panels opgehaald moeten worden
     */
    public void getAllPlayerPanels(Team t){
        ArrayList<Panel> tempPanels = new ArrayList<>();
        for (Player p : t.getPlayers()){
            tempPanels.add(p.getPanel());
        }
        t.setPlayerPanels(tempPanels);

    }

    /**
     * Het geven van een random instructie aan een speler
     * @param player De speler die een nieuwe instructie moet krijgen
     */
    private void givePlayerInstructions(Player player) {
        Team playerTeam = player.getTeam();
        int maxSize = playerTeam.getPlayerPanels().size();
        Random random = new Random();
        // Panels die in gebruik zijn
        ArrayList<Panel> usedPanelNumbers = new ArrayList<>();
        // Panels die niet in gebruik zijn en waar dus uit gekozen mag worden
        ArrayList<Panel> unusedPanelNumbers = playerTeam.getPlayerPanels();

        // Haalt alle panels op die op dit moment gebruikt worden door de speler uit het team
        for (Player p : playerTeam.getPlayers()) {
            usedPanelNumbers.add(p.getInstructions().getPanel());
        }

        // Verwijdert de panels die gebruikt worden zodat alleen de panels overblijven die nog niet gebruikt worden
        for (Panel p : usedPanelNumbers) {
            unusedPanelNumbers.remove(p);
        }

        // Een random panel wordt gekozen uit de lijst met panels
        Panel panel = unusedPanelNumbers.get(random.nextInt(maxSize));

        // Voegt de random instructie toe aan de speler
        player.setInstructions(panel.getInstruction());

    }
}
