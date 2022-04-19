package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;

/**
 * student pawn
 */
public class Student {

    private final ColorS color;

    /**
     * builds student pawn
     * @param color student color
     */
    public Student(ColorS color){
        this.color=color;
    }

    /**
     * gets student color
     * @return student color
     */
    public ColorS getColor() {
        return color;
    }
}
