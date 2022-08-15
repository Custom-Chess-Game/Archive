package me.smudge.client.engine;

/**
 * Represents a colour displayed in the console
 */
public enum ConsoleColour {
    RESET, WHITE, GRAY, GREEN, YELLOW, PINK;

    /**
     * Used to parse a message colours
     * @param message Message to parse
     * @return Parsed message
     */
    public static String parse(String message) {
        return message
                .replace("RESET", "\033[0m")
                .replace("WHITE", "\033[0;37m")
                .replace("GRAY", "\033[0;30m")
                .replace("GREEN", "\033[0;32m")
                .replace("YELLOW", "\033[0;33m")
                .replace("PINK", "\033[0;35m");
    }
}
