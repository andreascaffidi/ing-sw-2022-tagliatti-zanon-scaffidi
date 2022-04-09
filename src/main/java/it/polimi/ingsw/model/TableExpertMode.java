package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.CardWithStudents;
import it.polimi.ingsw.model.effects.Effect;
import it.polimi.ingsw.model.effects.InfluenceEffect;
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

    private List<Effect> influenceEffects;

    private Map<Player, Integer> playerCoins;
    private Map<Player, Boolean> professorTie;

    private Map<Island, Boolean> noEntryTiles;

    private Map<Integer, Integer> characters;
    private List<CardWithStudents> cards;

    public TableExpertMode(List<Player> players) {
        super(players);

        this.influenceEffects = new ArrayList<>();

        this.playerCoins = new HashMap<>();
        this.noEntryTiles = new HashMap<>();
        this.professorTie = new HashMap<>();

        for (int i = 0; i < this.getPlayers().length; i++){
            this.playerCoins.put(this.getPlayers()[i], NUM_OF_COINS_SETUP);
            this.professorTie.put(this.getPlayers()[i], false);
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
                        if ((int)card.get("id")==character){
                            int cost = (int)card.get("cost");
                            this.characters.put(character, cost);
                            setupStudentsOnCard(character, (int)card.get("studentsOnCard"));
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
        for(int i = 0; i < numOfStudents; i++){
            students.add(this.getBag().drawStudent());
        }
        cards.add(new CardWithStudents(students, card));
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

    public void setProfessorTie(Player player, boolean professorTie) {
        this.professorTie.put(player, professorTie);
    }

    public boolean isProfessorTie(Player player) {
        return this.professorTie.get(player);
    }

    public void addInfluenceEffect(Effect effect){
        this.influenceEffects.add(effect);
    }

    public void resetEffects() {
        this.influenceEffects.clear();
        for (int i = 0; i < this.getPlayers().length; i++){
            this.setProfessorTie(this.getPlayers()[i], false);
        }
    }

    //FIXME: separare gli effetti

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

    @Override
    public int getInfluence(Island island, Player player){
        int influence = super.getInfluence(island, player);
        for (Effect e : this.influenceEffects){
            InfluenceEffect influenceEffect = (InfluenceEffect) e.getTypeOfEffect();
            influence = influenceEffect.influenceEffect(influence, island, player);
        }
        return influence;
    }

    @Override
    public void setProfessorOwner(ColorS color, Player currentPlayer){
        if (isProfessorTie(currentPlayer)) {
            int currentPlayerProf = currentPlayer.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color);
            Player oldOwner = this.getProfessorOwner(color);
            if (oldOwner == null || currentPlayerProf >= oldOwner.getSchoolBoard().getDiningRoom().getNumberOfStudentsPerColor(color)) {
                this.getProfessor(color).setOwner(currentPlayer);
            }
        }
        else {
            super.setProfessorOwner(color, currentPlayer);
        }
    }

    public Map<Integer, Integer> getCharacters() {
        return this.characters;
    }

    public void validCharacter(int id) throws InvalidCharacterException, NotEnoughCoinsException {
        //todo: solo se non si è giocato nessun altro character nello stesso round (penso nella remote view)
        if(this.playerCoins.get(getCurrentPlayer()) < this.characters.get(id)){
            throw new NotEnoughCoinsException("You don't have enough coins");
        }
        if(!this.characters.containsKey(id)) {
            throw new InvalidCharacterException("Invalid Character");
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
