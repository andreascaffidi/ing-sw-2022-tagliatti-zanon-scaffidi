package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.CardWithStudents;
import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.effects.InfluenceEffect;
import it.polimi.ingsw.model.effects.ProfessorOwnerEffect;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class TableExpertMode extends Table {
    private final int NUM_OF_CHARACTER_CARDS=12;
    private final int NUM_OF_CHARACTER_CHOSEN = 3;
    private final int NUM_OF_COINS = 20;
    private final int NUM_OF_COINS_SETUP = 1;
    private final int MAX_NO_ENTRY_TILES = 4;


    private int bank;
    private int numOfNoEntryTiles;

    private Effect currentEffect;

    private Map<Player, Integer> playerCoins;

    private Map<Island, Boolean> noEntryTiles;

    private Map<Integer, Integer> characters;
    private List<CardWithStudents> cards;

    public TableExpertMode(List<Player> players) {
        super(players);

        this.playerCoins = new HashMap<>();
        this.noEntryTiles = new HashMap<>();

        for (int i = 0; i < this.getPlayers().length; i++){
            this.playerCoins.put(this.getPlayers()[i], NUM_OF_COINS_SETUP);
        }

        for (int i = 0; i < this.getIslands().size(); i++){
            this.noEntryTiles.put(this.getIslands().get(i), false);
        }

        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.setupCharacterCards();

    }

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
                int character = new Random().nextInt(NUM_OF_CHARACTER_CARDS);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

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

    public CardWithStudents getCardWithStudents(int character) throws CardNotFoundException {
        for(CardWithStudents card : cards)
        {
            if(card.getCharacter() == character) {
                return card;
            }
        }
        throw new CardNotFoundException("Card not found");
    }

    private void deposit(int coins) {
        this.bank += coins;
    }

    private void withdraw(int coins){
        this.bank -= coins;
    }

    public int getBank(){
        return this.bank;
    }

    public void addCoins(Player player, Integer coinsToAdd){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins + coinsToAdd);
        withdraw(coinsToAdd);
    }

    public void pay(Player player, Integer coinsToPay){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins - coinsToPay);
        deposit(coinsToPay);
    }

    //TODO: ho scoperto che il costo viene incrementato solo la prima volta
    public void incrementCardCost(int character){
        int cost = this.characters.get(character);
        this.characters.put(character, cost + 1);
    }

    public int getPlayerCoins(Player player){return this.playerCoins.get(player);}

    public void setNoEntryTile(Island island, boolean entryTile) {
        this.noEntryTiles.put(island, entryTile);
        this.numOfNoEntryTiles++;
    }

    public boolean isNoEntryTile(Island island){
        return this.noEntryTiles.get(island);
    }

    public void setCurrentEffect(Effect effect){
        this.currentEffect = effect;
    }

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
            this.numOfNoEntryTiles--;
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
        super.newIslandGroup(islands);
        int idMin = islands.stream().map(Island::getId).reduce(12, (id1, id2) -> id1 < id2 ? id1 : id2);
        for (Island i : islands){
            if (noEntryTiles.get(i)){
                this.setNoEntryTile(this.getIsland(idMin), true);
                this.setNoEntryTile(i, false);
            }
        }
    }

    @Override
    protected int getInfluence(Island island, Player player){
        int influence = super.getInfluence(island, player);
        if (this.currentEffect != null){
            InfluenceEffect influenceEffect = (InfluenceEffect) this.currentEffect.getTypeOfEffect();
            influence = influenceEffect.influenceEffect(influence, island, player);
        }
        return influence;
    }

    @Override
    protected int getNumberOfStudents(ColorS color, Player currentPlayer){
        int number = super.getNumberOfStudents(color, currentPlayer);
        if (this.currentEffect != null){
            ProfessorOwnerEffect professorOwnerEffect = (ProfessorOwnerEffect) this.currentEffect.getTypeOfEffect();
            number = professorOwnerEffect.professorOwnerEffect(number, color, currentPlayer);
        }
        return number;
    }

    public Map<Integer, Integer> getCharacters() {
        return this.characters;
    }

    public void validCharacter(int id) throws InvalidCharacterException, NotEnoughCoinsException {
        //todo: solo se non si è giocato nessun altro character nello stesso round (penso nella remote view)
        if(!this.characters.containsKey(id)) {
            throw new InvalidCharacterException("Invalid Character");
        }
        if(this.playerCoins.get(getCurrentPlayer()) < this.characters.get(id)){
            throw new NotEnoughCoinsException("You don't have enough coins");
        }
    }

    public void validAdditionalMovement(int movement) throws InvalidAdditionalMovementException {
        if (movement > 2 || movement <= 0){
            throw new InvalidAdditionalMovementException("Invalid additional movement");
        }
    }

    public void validNoEntryTile(Island island) throws TooManyNoEntryTileException, InvalidNoEntryTileException {
        if (this.numOfNoEntryTiles >= MAX_NO_ENTRY_TILES){
            throw new TooManyNoEntryTileException("No more no-entry tile placeable");
        }
        if (this.noEntryTiles.get(island)){
            throw new InvalidNoEntryTileException("Invalid no-entry tile position");
        }
    }
}
