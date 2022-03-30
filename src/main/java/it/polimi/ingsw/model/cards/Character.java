package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.TableExpertMode;

public abstract class Character {

    private String name;
    private int cost;

    public Character(String name, int cost){
        this.name = name;
        this.cost = cost;
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
     * activates the effect of the Card
     * @param table
     */
    public abstract void effect(TableExpertMode table);

    /**
     * sets up the Card
     * @param table
     */
    public abstract void setup(TableExpertMode table);
}
