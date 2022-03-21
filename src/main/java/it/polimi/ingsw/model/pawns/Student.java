package it.polimi.ingsw.model.pawns;

import it.polimi.ingsw.model.enums.ColorS;
import it.polimi.ingsw.model.Player;

public class Student {
    private ColorS color;

    public Student(ColorS color){
        this.color=color;
    }

    public ColorS getColor() {
        return color;
    }
}
