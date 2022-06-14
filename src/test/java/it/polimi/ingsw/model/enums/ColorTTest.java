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
        assertEquals(Ansi.GREY, ColorT.GREY.getAnsiEscapeCode());
        assertEquals(Ansi.WHITE, ColorT.WHITE.getAnsiEscapeCode());
        assertEquals(Ansi.BLACK, ColorT.BLACK.getAnsiEscapeCode());
    }
}