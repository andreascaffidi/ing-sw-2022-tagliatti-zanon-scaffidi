package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.exceptions.ColorNotFoundException;
import it.polimi.ingsw.network.client.UI.CLI.Ansi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class purpose is to Students color enum
 */
class ColorSTest {

    /**
     * Test if a string is correctly parsed to its respective color
     * @throws ColorNotFoundException if color isn't available
     */
    @Test
    void parseToColor() throws ColorNotFoundException {
        assertEquals(ColorS.BLUE, ColorS.parseToColor("blue"));
        assertEquals(ColorS.BLUE, ColorS.parseToColor("BLUE"));
        assertEquals(ColorS.BLUE, ColorS.parseToColor("bLuE"));
        assertEquals(ColorS.RED, ColorS.parseToColor("red"));
        assertEquals(ColorS.PINK, ColorS.parseToColor("pink"));
        assertEquals(ColorS.YELLOW, ColorS.parseToColor("yellow"));
        assertEquals(ColorS.GREEN, ColorS.parseToColor("green"));
        assertThrows(ColorNotFoundException.class, () -> ColorS.parseToColor("black"));
    }

    /**
     * Tests if return the correct ansi escape code
     */
    @Test
    void getAnsiEscapeCode(){
        assertEquals(Ansi.GREEN, ColorS.GREEN.getAnsiEscapeCode());
        assertEquals(Ansi.BLUE, ColorS.BLUE.getAnsiEscapeCode());
        assertEquals(Ansi.RED, ColorS.RED.getAnsiEscapeCode());
        assertEquals(Ansi.MAGENTA, ColorS.PINK.getAnsiEscapeCode());
        assertEquals(Ansi.YELLOW, ColorS.YELLOW.getAnsiEscapeCode());
    }
}