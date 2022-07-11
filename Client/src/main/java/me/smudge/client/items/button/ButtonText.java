package me.smudge.client.items.button;

/**
 * Represents a buttons text
 */
public class ButtonText {

    /**
     * Types of text
     */
    private final String text;
    private String textOnHover = null;

    /**
     * Create a new instance of button text
     * @param text Normal text
     */
    public ButtonText(String text) {
        this.text = text;
    }

    /**
     * Create a new instance of button text
     * @param text Normal text
     * @param textOnHover Text to change to on hover
     */
    public ButtonText(String text, String textOnHover) {
        this.text = text;
        this.textOnHover = textOnHover;
    }

    /**
     * Used to get the default text
     */
    public String getText() {
        return text;
    }

    /**
     * Used to get the text when hovering
     */
    public String getOnHover() {
        return textOnHover;
    }
}
