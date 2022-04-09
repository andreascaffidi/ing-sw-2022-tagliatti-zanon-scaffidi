package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.exceptions.ColorNotFoundException;

public enum ColorS {
    GREEN, BLUE, RED, PINK, YELLOW;

    public static ColorS parseToColor(String string) throws ColorNotFoundException{
        try {
            return ColorS.valueOf(string.toUpperCase());
        }catch(IllegalArgumentException e){
            throw new ColorNotFoundException("Color not found");
        }
    }
}
