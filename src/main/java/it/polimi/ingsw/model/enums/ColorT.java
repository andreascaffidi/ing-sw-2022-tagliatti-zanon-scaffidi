package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.Ansi;

import java.io.Serializable;

/**
 * tower colors
 */
public enum ColorT implements Serializable {
    BLACK(Ansi.BACKGROUND_BLACK), WHITE(Ansi.BACKGROUND_WHITE), GREY(Ansi.WHITE);

    ColorT(String ansiEscapeCode) {
        this.ansiEscapeCode = ansiEscapeCode;
    }

    public String ansiEscapeCode;

    public String getAnsiEscapeCode() {
        return ansiEscapeCode;
    }
}
