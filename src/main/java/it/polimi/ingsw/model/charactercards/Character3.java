package it.polimi.ingsw.model.charactercards;

import it.polimi.ingsw.exceptions.ParityException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.TableExpertMode;
import it.polimi.ingsw.model.cards.Character;
import it.polimi.ingsw.model.islands.Island;
import it.polimi.ingsw.model.pawns.Tower;

public class Character3 extends Character {

    private int islandChosen;

    public Character3() {
        super("Character3",3);
        this.islandChosen=0;
    }


    /**
     * calculates supremacy on the chosen Island and eventually changes the Owner
     * @param table
     */
    @Override
    public void effect(TableExpertMode table)
    {
        //notify view scegli un isola
        Island island = table.getIsland(islandChosen);
        try {
            Player king = table.getSupremacy(island);
            if (!king.equals(table.getIsland(islandChosen).getTower().getOwner())) {
                Tower newTower = king.getSchoolBoard().getTowers().removeLastTower();
                Tower oldTower = table.getIsland(islandChosen).getTower();
                table.getIsland(islandChosen).setTower(newTower);
                oldTower.getOwner().getSchoolBoard().getTowers().addTower(oldTower);
            }
        }
        catch (ParityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * doesn't need setup
     * @param table
     */
    @Override
    public void setup(TableExpertMode table) {

    }

    public void setIslandChosen(int islandChosen) {
        this.islandChosen = islandChosen;
    }

    public int getIslandChosen() {
        return this.islandChosen;
    }
}
