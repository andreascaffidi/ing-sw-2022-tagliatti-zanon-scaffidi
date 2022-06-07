package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.Ansi;
import it.polimi.ingsw.exceptions.ColorNotFoundException;

import java.io.Serializable;

/**
 * pawn colors
 */
public enum ColorS implements Serializable {

    GREEN(Ansi.GREEN),
    BLUE(Ansi.BLUE),
    RED(Ansi.RED),
    PINK(Ansi.MAGENTA),
    YELLOW(Ansi.YELLOW);

    private final String ansiEscapeCode;

    /**
     * sets an ANSI escape code to a pawn color
     * @param ansiEscapeCode ANSI escape code
     */
    ColorS(String ansiEscapeCode) {
        this.ansiEscapeCode = ansiEscapeCode;
    }

    /**
     * parses the given string to the correct ColorS enum
     * @param string string to parse
     * @return correct parsed enum
     * @throws ColorNotFoundException if the given string can't be parsed
     */
    public static ColorS parseToColor(String string) throws ColorNotFoundException {
        try {
            return ColorS.valueOf(string.toUpperCase());
        }catch(IllegalArgumentException e){
            throw new ColorNotFoundException("Color not found");
        }
    }

    /**
     * gets the ANSI escape code of the pawn color
     */
    public String getAnsiEscapeCode() {
        return ansiEscapeCode;
    }
}
