package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.islands.Island;

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
    private final int NUM_OF_CHARACTER_CARDS = 3;
    private final int NUM_OF_COINS = 20;
    private final int NUM_OF_COINS_SETUP = 1;

    private int bank;
    private Character[] characterCards;
    private ColorS noInfluenceColor;

    private Map<Player, Integer> playerCoins;
    private Map<Player, Boolean> additionalInfluence;
    private Map<Player, Boolean> professorTie;

    private Map<Island, Boolean> entryTile;
    private Map<Island, Boolean> countTowers;
    private ArrayList<Character> characters;


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


        this.bank = NUM_OF_COINS - players.size() * NUM_OF_COINS_SETUP;
        this.characterCards = new Character[NUM_OF_CHARACTER_CARDS];
        this.setupCharacterCards(); //todo:fare implementazione

    }

    private void setupCharacterCards() {

        /*
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assets/characters.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;
            this.characters = new ArrayList<>();
            cards.forEach( object ->{
                JSONObject card =  (JSONObject) object;
                this.characters.add(new Character(((String)card.get("name")), ((Long)card.get("cost")).intValue(),null));
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */

    }

    public void deposit(int coins) {
        this.bank += coins;
    }

    public void addCoins(Player player, Integer coinsToAdd){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins + coinsToAdd);
    }

    public void pay(Player player, Integer coinsToPay){
        Integer coins = this.playerCoins.get(player);
        this.playerCoins.put(player, coins - coinsToPay);
    }

    public void setAdditionalInfluence(Player player, boolean additionalInfluence){
        this.additionalInfluence.put(player, additionalInfluence);
    }

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

    public void setProfessorTie(Player player, boolean professorTie) {
        this.professorTie.put(player, professorTie);
    }

    public boolean isProfessorTie(Player player) {
        return this.professorTie.get(player);
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

    //todo: implementare metodo per aggiungere un coin ai player che hanno occupato le posizioni di schoolboard

}
