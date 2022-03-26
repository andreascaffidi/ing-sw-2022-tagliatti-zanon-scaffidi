package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Assistant;
import it.polimi.ingsw.model.enums.ColorT;
import it.polimi.ingsw.model.schoolBoard.SchoolBoard;
import it.polimi.ingsw.model.schoolBoard.SchoolBoardExpertMode;

import java.util.List;

public class PlayerExpertMode extends Player{
    public static final int NUM_OF_COINS_SETUP = 1;
    private int coins;
    private boolean additionalInfluence;
    private SchoolBoardExpertMode schoolBoardExpertMode;

    public PlayerExpertMode(String username, List<Assistant> assistantDeck, int tagTeam, ColorT towerColor) {
        super(username, assistantDeck, tagTeam, towerColor);
        this.coins = NUM_OF_COINS_SETUP;
        this.schoolBoardExpertMode = new SchoolBoardExpertMode(this);
    }

    /**
     * adds a coin to the coins of the player
     * @param coins
     */
    public void addCoins(int coins){
        this.coins += coins;
    }

    /**
     * subtracts the cost of the played Card from the amount of coins of the player
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

    @Override
    public SchoolBoardExpertMode getSchoolBoard(){
        return this.schoolBoardExpertMode;
    }
}
