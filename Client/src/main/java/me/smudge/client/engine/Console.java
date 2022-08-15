package me.smudge.client.engine;

/**
 * Represents the console
 */
public class Console {

    /**
     * Print something in the console
     * @param message Message to print
     */
    public static void print(String message) {
        System.out.println(ConsoleColour.parse(ConsoleColour.RESET + "[LOG] " + message));
    }
}
