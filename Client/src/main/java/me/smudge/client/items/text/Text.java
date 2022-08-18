package me.smudge.client.items.text;

import me.smudge.client.items.Item;
import me.smudge.client.positions.ModularPosition;

import javax.swing.*;
import java.awt.*;

/**
 * Represents some text
 */
public class Text extends Item {

    /**
     * The instance of the liable to put on the frame
     */
    private JLabel label;

    /**
     * The text
     */
    private final String text;

    /**
     * Used to create a new instance of some text
     * @param modularPosition The position of the text
     * @param text The text to display
     */
    public Text(ModularPosition modularPosition, String text) {
        super(modularPosition);

        this.text = text;
        this.create();
    }

    /**
     * Used to create and update the label instance
     */
    public void create() {
        // <html> Allows the text to wrap
        this.label = new JLabel("<html>" + this.text + "</html>", SwingConstants.CENTER);

        this.label.setBounds(
                this.modularPosition.getPos1().getX(),
                this.modularPosition.getPos1().getY(),
                this.modularPosition.getWidth(),
                this.modularPosition.getHeight()
        );

        int fontSize = (int) (10 + (Math.min(this.modularPosition.getWidth(), this.modularPosition.getHeight()) * 0.1));
        this.label.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));

        this.label.setForeground(Color.white);
        this.label.setBackground(Color.black);
        this.label.setOpaque(true);
    }

    @Override
    public void updateItem() {
        this.label.setBounds(
                this.modularPosition.getPos1().getX(),
                this.modularPosition.getPos1().getY(),
                this.modularPosition.getWidth(),
                this.modularPosition.getHeight()
        );
    }

    @Override public void onHover() {}
    @Override public void onNotHover() {}

    @Override
    public void render(JPanel frame) {
        this.create();
        frame.add(this.label);
    }

    @Override
    public Component getComponent() {
        return this.label;
    }
}
