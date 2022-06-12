package it.polimi.ingsw.network.client.UI.CLI;

/**
 * Usage:
 * <li>String msg = Ansi.Red.and(Ansi.BgYellow).format("Hello %s", name)</li>
 * <li>String msg = Ansi.Blink.colorize("BOOM!")</li>
 *
 * Or, if you are adverse to that, you can use the constants directly:
 * <li>String msg = new Ansi(Ansi.ITALIC, Ansi.GREEN).format("Green money")</li>
 * Or, even:
 * <li>String msg = Ansi.BLUE + "scientific"</li>
 *
 * NOTE: Nothing stops you from combining multiple FG colors or BG colors,
 *       but only the last one will display.
 *
 *
 */
public final class Ansi {

        // Color code strings from:
        // http://www.topmudsites.com/forums/mud-coding/413-java-ansi.html
        public static final String RESET = "\u001B[0m";

        public static final String	HIGH_INTENSITY		= "\u001B[1m";
        public static final String	LOW_INTENSITY		= "\u001B[2m";

        public static final String	UNDERLINE			= "\u001B[4m";

        public static final String	BLACK				= "\u001B[30m";
        public static final String	RED					= "\u001B[31m";
        public static final String	GREEN				= "\u001B[32m";
        public static final String	YELLOW				= "\u001B[33m";
        public static final String	BLUE				= "\u001B[34m";
        public static final String	MAGENTA				= "\u001B[35m";
        public static final String	CYAN				= "\u001B[36m";
        public static final String	WHITE				= "\u001B[37m";

        public static final String	BACKGROUND_BLACK	= "\u001B[40m";
        public static final String	BACKGROUND_RED		= "\u001B[41m";
        public static final String	BACKGROUND_GREEN	= "\u001B[42m";
        public static final String	BACKGROUND_YELLOW	= "\u001B[43m";
        public static final String	BACKGROUND_MAGENTA	= "\u001B[45m";
        public static final String	BACKGROUND_CYAN		= "\u001B[46m";
        public static final String	BACKGROUND_WHITE	= "\u001B[47m";

        public static final String TYPING_ICON = "➡️";
        public static final String ERROR_ICON = "❌️";
        public static final String CARD_ICON = "\uD83C\uDCCF️";
        public static final String MONEY_BAG_ICON = "\uD83D\uDCB0️";
        public static final String EFFECT_ICON = "✨";

        /**
         * colorizes a string with a specific color
         * @param original string to colorize
         * @param color color
         * @return string colored
         */
        public static String colorize(String original, String color) {
                return color + original + RESET;
        }

        /**
         * colorizes a string with more effect colors
         * @param original string to colorize
         * @param codes colors
         * @return string colored
         */
        public static String colorize(String original, String... codes) {
                StringBuilder _codes_str = new StringBuilder();
                for (String code : codes) {
                        _codes_str.append(code);
                }
                return _codes_str + original + RESET;
        }

}
