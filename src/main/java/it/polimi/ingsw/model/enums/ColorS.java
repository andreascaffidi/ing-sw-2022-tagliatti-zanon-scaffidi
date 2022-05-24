package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.Ansi;
import it.polimi.ingsw.network.client.UI.CLI.CLI;
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

    public String getAnsiEscapeCode() {
        return ansiEscapeCode;
    }

    public String toAnsiString(){
        return this.ansiEscapeCode+ toString()+CLI.ANSI_RESET;
    }
}
