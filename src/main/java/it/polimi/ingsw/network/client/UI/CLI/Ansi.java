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
        public static String RESET = "\u001B[0m";

        public static  String	HIGH_INTENSITY		= "\u001B[1m";

        public static  String	UNDERLINE			= "\u001B[4m";

        public static  String	BLACK				= "\u001B[30m";
        public static  String	RED					= "\u001B[31m";
        public static  String	GREEN				= "\u001B[32m";
        public static  String	YELLOW				= "\u001B[33m";
        public static  String	BLUE				= "\u001B[34m";
        public static  String	MAGENTA				= "\u001B[35m";
        public static  String	CYAN				= "\u001B[36m";
        public static  String	WHITE				= "\u001B[97m";
        public static  String   GREY                = "\u001B[37m";

        public static  String	BACKGROUND_RED		= "\u001B[41m";
        public static  String	BACKGROUND_GREEN	= "\u001B[42m";
        public static  String	BACKGROUND_YELLOW	= "\u001B[43m";
        public static  String	BACKGROUND_MAGENTA	= "\u001B[45m";
        public static  String	BACKGROUND_CYAN		= "\u001B[46m";
        public static  String	BACKGROUND_GREY     = "\u001B[100m";

        public Ansi() {
                if (System.getProperty("os.name").contains("Windows")){
                       RESET = "Esc[0m";

                       HIGH_INTENSITY		= "Esc[1m";

                       UNDERLINE			= "Esc[4m";

                       BLACK				= "Esc[30m";
                       RED					= "Esc[31m";
                       GREEN			    = "Esc[32m";
                       YELLOW			    = "Esc[33m";
                       BLUE	                = "Esc[34m";
                       MAGENTA				= "Esc[35m";
                       CYAN				    = "Esc[36m";
                       WHITE				= "Esc[97m";
                       GREY                 = "Esc[37m";

                       BACKGROUND_RED		= "Esc[41m";
                       BACKGROUND_GREEN 	= "Esc[42m";
                       BACKGROUND_YELLOW	= "Esc[43m";
                       BACKGROUND_MAGENTA	= "Esc[45m";
                       BACKGROUND_CYAN		= "Esc[46m";
                       BACKGROUND_GREY      = "Esc[100m";
                }
        }

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
