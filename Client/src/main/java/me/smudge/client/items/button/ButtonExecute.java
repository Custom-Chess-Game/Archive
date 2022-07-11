package me.smudge.client.items.button;

/**
 * Represents when a button is clicked
 */
public record ButtonExecute(Runnable runnable) {

    /**
     * Create a new button on execute
     *
     * @param runnable Task to run on execute
     */
    public ButtonExecute {}

    /**
     * Used to execute the button
     */
    public void execute() {
        this.runnable.run();
    }
}
