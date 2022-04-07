package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.CardNotFoundException;
import it.polimi.ingsw.exceptions.InvalidCharacterException;
import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.charactercards.*;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Student;

import javax.smartcardio.CardNotPresentException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class TableExpertMode extends Table {
    private final int NUM_OF_CHARACTER_CARDS=12;
    private final int NUM_OF_CHARACTER_CARDS_2 = 3;
    private final int NUM_OF_COINS = 20;
    private final int NUM_OF_COINS_SETUP = 1;
    private final int NUM_OF_ENTRY_TILE = 4;


    private int bank;
    private int numOfEntryTile;
    private ColorS noInfluenceColor;

    private Map<Player, Integer> playerCoins;
    private Map<Player, Boolean> additionalInfluence;
    private Map<Player, Boolean> professorTie;

    private Map<Island, Boolean> entryTile;
    private Map<Island, Boolean> countTowers;
    private ArrayList<Integer> characters = new ArrayList<>();

    private List<Card> cards = new ArrayList<>();

    public TableExpertMode(List<Player> players) {
        super(players);

        this.playerCoins = new HashMap<>();
        this.additionalInfluence = new HashMap<>();
        this.entryTile = new HashMap<>();
        this.countTowers = new HashMap<>();
        this.professorTie = new HashMap<>();

        for (int i = 0; i < this.getPlayers().length; i++){
            this.playerCoins.put(this.getPlayers()[i], NUM_OF_COINS_SETUP);
            this.additionalInfluence.put(this.getPlayers()[i], false);
            this.professorTie.put(this.getPlayers()[i], false);
        }

        for (int i = 0; i < this.getIslands().size(); i++){
            this.entryTile.put(this.getIslands().get(i), false);
            this.countTowers.put(this.getIslands().get(i), true);
        }


        this.numOfEntryTile = NUM_OF_ENTRY_TILE;
        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.setupCharacterCards(); //todo:fare implementazione

    }

    private void setupCharacterCards() {
        while (characters.size() < 3)
        {
            int random = new Random().nextInt(NUM_OF_CHARACTER_CARDS);
            if(!characters.contains(random))
            {
                characters.add(random);
                setup(random);
            }
        }

    }

    private void setup(int card)
    {
        List<Student> students = new ArrayList<>();

        if(card == 1 || card == 11)
        {
            for(int i = 0; i < 4; i++)
            {
                students.add(this.getBag().drawStudent());
            }
        }

        if(card == 7)
        {
            for(int i = 0; i < 6; i++)
            {
                students.add(this.getBag().drawStudent());
            }
        }

        cards.add(new Card(students, card));

    }

    public Card getCard(int character) throws CardNotFoundException {
        for(Card card : cards)
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

    public void setAdditionalInfluence(Player player, boolean additionalInfluence){
        this.additionalInfluence.put(player, additionalInfluence);
    }

    public int getPlayerCoins(Player player){return this.playerCoins.get(player);}

    public boolean isAdditionalInfluence(Player player){
        return this.additionalInfluence.get(player);
    }

    public void setEntryTile(Island island, boolean entryTile) {
        this.entryTile.put(island, entryTile);
    }

    public void setCountTowers(Island island, boolean countTowers) {
        this.countTowers.put(island, countTowers);
    }

    public boolean isEntryTile(Island island){
        return this.entryTile.get(island);
    }

    public boolean isCountTowers(Island island){
        return this.countTowers.get(island);
    }

    public void setNoInfluenceColor(ColorS noInfluenceColor) {
        this.noInfluenceColor = noInfluenceColor;
    }

    public ColorS getNoInfluenceColor() {
        return this.noInfluenceColor;
    }

    public void setProfessorTie(Player player, boolean professorTie) {
        this.professorTie.put(player, professorTie);
    }

    public boolean isProfessorTie(Player player) {
        return this.professorTie.get(player);
    }

    public void decrementEntryTile(){
        this.numOfEntryTile--;
    }

    public int getNumOfEntryTile() {
        return this.numOfEntryTile;
    }

    public void resetCardsEffect() {
        this.noInfluenceColor = null;
        for (int i = 0; i < this.getIslands().size(); i++) {
            this.setCountTowers(this.getIslands().get(i), true);
        }
        for (int i = 0; i < this.getPlayers().length; i++){
            this.setAdditionalInfluence(this.getPlayers()[i], false);
            this.setProfessorTie(this.getPlayers()[i], false);
        }
    }

    @Override
    public Player getSupremacy (Island island)throws ParityException {
        if (this.isEntryTile(island)){
            this.setEntryTile(island, false);
            this.numOfEntryTile++;
            return island.getTower().getOwner();
        }
        else{
            return super.getSupremacy(island);
        }
    }

    @Override
    public int getInfluence(Island island, Player player) {
        int influence = 0;
        for (ColorS c : ColorS.values()){
            if (c != noInfluenceColor && this.getProfessor(c).getOwner()==player){
                influence += island.numStudent(c);
            }
        }
        if (island.getTower() != null && player.equals(island.getTower().getOwner()) && countTowers.get(island)){
            influence += island.getNumOfTowers();
        }
        if (this.isAdditionalInfluence(player)){
            influence += 2;
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

    public ArrayList<Integer> getCharacters() {
        return characters;
    }

    //todo: implementare metodo per aggiungere un coin ai player che hanno occupato le posizioni di schoolboard

    public void validCharacter(int id) throws InvalidCharacterException
    {
        if(!this.getCharacters().contains(id))
        {
            throw new InvalidCharacterException("Invalid Character");
        }
    }
}
