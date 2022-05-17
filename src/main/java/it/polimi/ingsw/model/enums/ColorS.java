package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.CLI;
import it.polimi.ingsw.exceptions.ColorNotFoundException;

import java.io.Serializable;

/**
 * pawn colors
 */
public enum ColorS implements Serializable {

    GREEN(CLI.ANSI_GREEN),
    BLUE(CLI.ANSI_BLUE),
    RED(CLI.ANSI_RED),
    PINK(CLI.ANSI_PURPLE),
    YELLOW(CLI.ANSI_YELLOW);

    public String ansiEscapeCode;

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

    public String toAnsiString(){
        return this.ansiEscapeCode+ toString()+CLI.ANSI_RESET;
    }
}
