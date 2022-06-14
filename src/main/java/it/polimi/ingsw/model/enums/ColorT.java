package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.Ansi;

import java.io.Serializable;

/**
 * tower colors
 */
public enum ColorT implements Serializable {

    BLACK(Ansi.BLACK),
    WHITE(Ansi.WHITE),
    GREY(Ansi.GREY);

    private final String ansiEscapeCode;

    /**
     * sets an ANSI escape code to a tower color
     * @param ansiEscapeCode ANSI escape code
     */
    ColorT(String ansiEscapeCode) {
        this.ansiEscapeCode = ansiEscapeCode;
    }


    /**
     * gets the ANSI escape code of the tower color
     */
    public String getAnsiEscapeCode() {
        return ansiEscapeCode;
    }
}
