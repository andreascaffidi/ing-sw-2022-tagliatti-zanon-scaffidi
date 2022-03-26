package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.TableExpertMode;

public class Character {

    private String name;
    private int cost;
    private TypeOfCard typeOfCard;

    public Character(String name, int cost, TypeOfCard typeOfCard){
        this.name = name;
        this.cost = cost;
        this.typeOfCard = typeOfCard;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    /**
     * increments the cost of the card
     */
    public void incrementCost(){
        this.cost++;
    }

    /**
     * activates the effect of the Character
     * @param table
     */
    public void activate(TableExpertMode table){
        typeOfCard.effect(table);
    }

    /**
     * sets up the Character
     * @param table
     */

    public void setup(TableExpertMode table){
        typeOfCard.setup(table);
    }
}
