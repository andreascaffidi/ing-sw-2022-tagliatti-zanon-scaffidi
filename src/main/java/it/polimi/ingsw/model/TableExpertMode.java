package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.CardWithStudents;
import it.polimi.ingsw.model.effects.InfluenceEffect;
import it.polimi.ingsw.model.effects.MotherNatureEffect;
import it.polimi.ingsw.model.effects.ProfessorOwnerEffect;
import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import it.polimi.ingsw.network.client.reducedModel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Table class for match control, expert mode variant
 */
public class TableExpertMode extends Table {
    public static final int NUM_OF_CHARACTER_CARDS=12;
    public static final int NUM_OF_CHARACTER_CHOSEN = 3;
    public static final int NUM_OF_COINS = 20;
    public static final int NUM_OF_COINS_SETUP = 1;
    public static final int MAX_NO_ENTRY_TILES = 4;

    private int bank;
    private int numOfNoEntryTiles;
    private boolean characterAlreadyPlayed;

    private Effect currentEffect;

    private final Map<Player, Integer> playerCoins;

    private final Map<Island, Boolean> noEntryTiles;

    private Map<Integer, Integer> characters;
    private List<CardWithStudents> cards;

    /**
     * builds the table expert mode
     * @param players game players
     */
    public TableExpertMode(List<Player> players) {
        super(players);

        this.playerCoins = new HashMap<>();
        this.noEntryTiles = new HashMap<>();
        this.characterAlreadyPlayed = false;

        for (int i = 0; i < this.getPlayers().length; i++){
            this.playerCoins.put(this.getPlayers()[i], NUM_OF_COINS_SETUP);
        }

        for (int i = 0; i < this.getIslands().size(); i++){
            this.noEntryTiles.put(this.getIslands().get(i), false);
        }

        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.setupCharacterCards();

    }

    /**
     * sets up the character cards, choose 3 random characters and creates a map < cardId, cardCost>
     */
    private void setupCharacterCards() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/characters.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            this.characters = new HashMap<>();
            this.cards = new ArrayList<>();
            while (characters.size() < NUM_OF_CHARACTER_CHOSEN)
            {
                int character = new Random().nextInt(NUM_OF_CHARACTER_CARDS+1);
                if(!characters.containsKey(character)) {
                    for (Object o : cards){
                        JSONObject card =  (JSONObject) o;
                        if (((Long)card.get("id")).intValue()==character){
                            int cost = ((Long)card.get("cost")).intValue();
                            this.characters.put(character, cost);
                            setupStudentsOnCard(character, ((Long)card.get("studentsOnCard")).intValue());
                        }
                    }

                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets up students on the character card, only if it needs
     * @param card card on which place students
     * @param numOfStudents number of students to place
     */
    private void setupStudentsOnCard(int card, int numOfStudents)
    {
        List<Student> students = new ArrayList<>();
        if (numOfStudents != 0) {
            for (int i = 0; i < numOfStudents; i++) {
                students.add(this.getBag().drawStudent());
            }
            cards.add(new CardWithStudents(students, card));
        }
    }

    /**
     * gets the card that has students placed on it
     * @param character character card id
     * @return card with students placed on it
     * @throws CardNotFoundException if the character card hasn't got students on it
     */
    public CardWithStudents getCardWithStudents(int character) throws CardNotFoundException {
        for(CardWithStudents card : cards)
        {
            if(card.getCharacter() == character) {
                return card;
            }
        }
        throw new CardNotFoundException("Card not found");
    }

    /**
     * deposits coins to the bank
     * @param coins coins to deposit
     */
    private void deposit(int coins) {
        this.bank += coins;
    }

    /**
     * withdraws coins from the bank
     * @param coins coins to withdraw
     */
    private void withdraw(int coins){
        this.bank -= coins;
    }

    /**
     * gets the number of coins in the bank
     * @return coins in the bank
     */
    public int getBank(){
        return this.bank;
    }

    /**
     * adds coins to a player
     * @param player player who gains coins
     * @param coinsToAdd coins to add
     */
    public void addCoins(Player player, Integer coinsToAdd){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins + coinsToAdd);
        withdraw(coinsToAdd);
    }

    /**
     * makes a player pay an amount of coins
     * @param player player who pays
     * @param coinsToPay coins to pay
     */
    public void pay(Player player, Integer coinsToPay){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins - coinsToPay);
        deposit(coinsToPay);
    }

    /**
     * increments character card cost the first time a player pays it
     * @param character character card to increment
     */
    public void incrementCardCost(int character){
        int cost = this.characters.get(character);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/characters.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;
            for (Object o : cards){
                JSONObject card =  (JSONObject) o;
                if (((Long)card.get("id")).intValue()==character){
                    int initialCost = ((Long)card.get("cost")).intValue();
                    if (cost == initialCost){
                        this.characters.put(character, cost + 1);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets player's coins
     * @param player player
     * @return player's coins
     */
    public int getPlayerCoins(Player player){return this.playerCoins.get(player);}

    /**
     * sets a no entry tile on an island
     * @param island island on which set no entry tile
     * @param entryTile boolean that indicates if there's a no entry tile
     */
    public void setNoEntryTile(Island island, boolean entryTile) {
        this.noEntryTiles.put(island, entryTile);
        if (entryTile){
            this.numOfNoEntryTiles++;
        } else {
            this.numOfNoEntryTiles--;
        }
    }

    /**
     * checks if there's a no entry tile on an island
     * @param island island to check
     * @return boolean that indicates if there's a no entry tile
     */
    public boolean isNoEntryTile(Island island){
        return this.noEntryTiles.get(island);
    }

    /**
     * sets the current effect of the round
     * @param effect effect to set
     */
    public void setCurrentEffect(Effect effect){
        this.currentEffect = effect;
    }

    /**
     * sets if character is already played by a player in this round
     * @param characterAlreadyPlayed if character is already played
     */
    public void setCharacterAlreadyPlayed(boolean characterAlreadyPlayed) {
        this.characterAlreadyPlayed = characterAlreadyPlayed;
    }

    /**
     * resets the current effect of the round
     */
    public void resetCurrentEffect() {
        this.currentEffect = null;
    }

    /**
     * returns the player who has the greatest influence on the island. If there's a NoEntryTile, returns the old owner of the island
     * @param island island on which calculate supremacy
     * @return player with the greatest supremacy or old owner if there's a NoEntryTile
     * @throws ParityException if there's a parity of the max influence
     */
    @Override
    public Player getSupremacy (Island island)throws ParityException {
        if (this.isNoEntryTile(island)){
            this.setNoEntryTile(island, false);
            if (island.getTower() == null){
                throw new ParityException("There's a parity");
            }
            return island.getTower().getOwner();
        }
        else{
            return super.getSupremacy(island);
        }
    }

    /**
     * creates an IslandGroup from a list of islands: adds all the students, sets the tower, removes the old Islands and changes
     * all the Islands IDs. If there's a NoEntryTile on an island to merge, adds the NoEntryTile to the new island group
     * @param islands : islands to merge
     */
    @Override
    public void newIslandGroup(List<Island> islands){
        boolean noEntryTile = false;
        for (Island i : islands){
            if (noEntryTiles.get(i)){
                noEntryTile = true;
                this.setNoEntryTile(i, false);
            }
        }
        super.newIslandGroup(islands);
        int idMin = islands.stream().map(Island::getId).reduce(12, (id1, id2) -> id1 < id2 ? id1 : id2);
        if (noEntryTile){
            this.setNoEntryTile(this.getIsland(idMin), true);
        } else {
            this.noEntryTiles.put(this.getIsland(idMin), false);
        }
    }

    /**
     * calculates player's influence on an island counting the current effect of the round
     * @param island island on which calculate player's influence
     * @param player influence's owner
     * @return player's influence
     */
    @Override
    protected int getInfluence(Island island, Player player){
        int influence = super.getInfluence(island, player);
        if (this.currentEffect != null && this.currentEffect instanceof InfluenceEffect){
            InfluenceEffect influenceEffect = (InfluenceEffect) this.currentEffect;
            influence = influenceEffect.influenceEffect(influence, island, player);
        }
        return influence;
    }

    /**
     * moves mother nature counting the current effect of the round
     * @param movement mother nature movements
     */
    @Override
    public void moveMotherNature(int movement) {
        int movements = movement;
        if (this.currentEffect != null && this.currentEffect instanceof MotherNatureEffect){
            MotherNatureEffect motherNatureEffect = (MotherNatureEffect) this.currentEffect;
            movements = motherNatureEffect.motherNatureEffect(movements);
        }
        super.moveMotherNature(movements);
    }

    /**
     * counts the number of students with a specified color for a player counting the current effect of the round
     * @param color students' color
     * @param currentPlayer students' owner
     * @return number of students with the specified color
     */
    @Override
    protected int getNumberOfStudents(ColorS color, Player currentPlayer){
        int number = super.getNumberOfStudents(color, currentPlayer);
        if (this.currentEffect != null && this.currentEffect instanceof ProfessorOwnerEffect){
            ProfessorOwnerEffect professorOwnerEffect = (ProfessorOwnerEffect) this.currentEffect;
            number = professorOwnerEffect.professorOwnerEffect(number, color, currentPlayer);
        }
        return number;
    }

    /**
     * gets all the characters on the table, and also their costs
     * @return map <characterId, characterCost>
     */
    public Map<Integer, Integer> getCharacters() {
        return this.characters;
    }

    /**
     * checks character validity
     * @param id character id
     * @throws GameException if character isn't on the table
     * @throws GameException if there aren't enough coins to pay the character card
     */
    public void validCharacter(int id) throws GameException{
        if(this.characterAlreadyPlayed){
            throw new GameException("You have already played a character in this round");
        }
        if(!this.characters.containsKey(id)) {
            throw new GameException("Invalid Character");
        }
        if(this.playerCoins.get(getCurrentPlayer()) < this.characters.get(id)){
            throw new GameException("You don't have enough coins");
        }
    }

    /**
     * checks additional movement validity
     * @param movement additional movement
     * @throws GameException if additional movement is invalid
     */
    public void validAdditionalMovement(int movement) throws GameException {
        if (movement > 2 || movement <= 0){
            throw new GameException("Invalid additional movement");
        }
    }

    /**
     * checks no entry tile validity
     * @param island island on which place no entry tile
     * @throws GameException if all 4 no entry tiles are already placed on the table
     * @throws GameException if there's already a no entry tile on the island
     */
    public void validNoEntryTile(Island island) throws GameException{
        if (this.numOfNoEntryTiles >= MAX_NO_ENTRY_TILES){
            throw new GameException("No more no-entry tile placeable");
        }
        if (this.noEntryTiles.get(island)){
            throw new GameException("Invalid no-entry tile position");
        }
    }

    /**
     * creates and returns a reduced version of the table expert mode
     * @return reduced model expert mode
     */
    @Override
    public ReducedModel createReducedModel(){

        List<ReducedBoard> reducedBoards = new ArrayList<>();
        List<ReducedIsland> reducedIslands = new ArrayList<>();
        List<ReducedCloud> reducedClouds = new ArrayList<>();

        for(Player p : this.getPlayers()) {
            reducedBoards.add(p.reduceBoard());
        }

        for(Island i : this.getIslands()) {
            reducedIslands.add(i.reduceIsland());
        }

        for(Cloud c : this.getClouds()) {
            reducedClouds.add(new ReducedCloud(this.getClouds().indexOf(c),
                    c.getStudents().stream().map(Student::getColor).collect(Collectors.toList())));
        }

        Map<String, Integer> reducedCoins = new HashMap<>();
        for (Player p : this.playerCoins.keySet()){
            reducedCoins.put(p.getUsername(), this.playerCoins.get(p));
        }

        Map<Integer, Boolean> reducedNoEntryTiles = new HashMap<>();
        for (Island i : this.noEntryTiles.keySet()){
            reducedNoEntryTiles.put(i.reduceIsland().getId(), this.noEntryTiles.get(i));
        }

        List<ReducedCharacter> reducedCharacters = new ArrayList<>();
        for (int character : this.characters.keySet()){
            try {
                List<Student> studentsOnCard = this.getCardWithStudents(character).getStudents();
                reducedCharacters.add(new ReducedCharacter(character, this.characters.get(character),
                        studentsOnCard.stream().map(Student::getColor).collect(Collectors.toList())));
            } catch (CardNotFoundException e){
                reducedCharacters.add(new ReducedCharacter(character, this.characters.get(character), null));
            }
        }

        return new ReducedModelExpertMode(reducedIslands, reducedClouds,
                this.getCurrentPlayer().getUsername(), reducedBoards,
                this.currentEffect != null ? this.currentEffect.toString() : null,
                reducedCoins, reducedNoEntryTiles, reducedCharacters, characterAlreadyPlayed);
    }

}
