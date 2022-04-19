package it.polimi.ingsw.model.enums;

import it.polimi.ingsw.exceptions.ColorNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorSTest {

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
}