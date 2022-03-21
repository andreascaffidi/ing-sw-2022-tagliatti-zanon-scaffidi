package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;

import java.util.List;

public class PlayerExpertMode extends Player{
    public static final int NUM_OF_COINS_SETUP = 1;
    private int coins;
    private boolean additionalInfluence;

    public PlayerExpertMode(String username, List<Assistant> assistantDeck, SchoolBoard schoolBoard, int tagTeam) {
        super(username, assistantDeck, schoolBoard, tagTeam);
        this.coins = NUM_OF_COINS_SETUP;
    }

    /**
     * Add coins to the amount of coins of the player
     * @param coins
     */
    public void addCoins(int coins){
        this.coins += coins;
    }

    /**
     * Subtract cost from the amount of coins of the player
     * @param cost
     */
    public void pay(int cost){
        this.coins -= cost;
    }

    public int getCoins() {
        return coins;
    }


    public void setAdditionalInfluence(boolean additionalInfluence) {
        this.additionalInfluence = additionalInfluence;
    }

    public boolean isAdditionalInfluence() {
        return additionalInfluence;
    }
}
