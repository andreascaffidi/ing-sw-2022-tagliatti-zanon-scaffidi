package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.network.client.UI.CLI.Ansi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTTest {

    /**
     * Tests if return the correct ansi escape code
     */
    @Test
    void getAnsiEscapeCode() {
        assertEquals(Ansi.WHITE, ColorT.GREY.getAnsiEscapeCode());
        assertEquals(Ansi.BACKGROUND_WHITE, ColorT.WHITE.getAnsiEscapeCode());
        assertEquals(Ansi.BACKGROUND_BLACK, ColorT.BLACK.getAnsiEscapeCode());
    }
}