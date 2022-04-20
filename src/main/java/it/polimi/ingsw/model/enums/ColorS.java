package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.exceptions.ColorNotFoundException;

/**
 * pawn colors
 */
public enum ColorS {
    GREEN, BLUE, RED, PINK, YELLOW;

    /**
     * parses the given string to the correct ColorS enum
     * @param string string to parse
     * @return correct parsed enum
     * @throws ColorNotFoundException if the given string can't be parsed
     */
    public static ColorS parseToColor(String string) throws ColorNotFoundException{
        try {
            return ColorS.valueOf(string.toUpperCase());
        }catch(IllegalArgumentException e){
            throw new ColorNotFoundException("Color not found");
        }
    }
}
