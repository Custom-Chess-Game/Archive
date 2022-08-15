package me.smudge.client.items.button;

import me.smudge.client.items.Item;
import me.smudge.client.positions.ModularPosition;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a button
 */
public class Button extends Item {
    /**
     * Represents what will be rendered
     */
    private JButton button = null;

    /**
     * The buttons text
     */
    private ButtonText text = null;

    /**
     * The buttons executors
     */
    private ButtonExecute execute = null;

    /**
     * Create a new instance of a button
     * @param modularPosition Position of the button
     */
    public Button(ModularPosition modularPosition) {
        super(modularPosition);

        this.create();
    }

    /**
     * Create a new instance of a button
     * @param modularPosition Position of the button
     * @param text Button text
     */
    public Button(ModularPosition modularPosition, ButtonText text) {
        super(modularPosition);

        this.text = text;
        this.create();
    }

    /**
     * Create a new instance of a button
     * @param modularPosition Position of the button
     * @param text Button text
     */
    public Button(ModularPosition modularPosition, ButtonText text, ButtonExecute executor) {
        super(modularPosition);

        this.text = text;
        this.execute = executor;

        this.create();
    }

    /**
     * Used to re-create and update the position of the button
     */
    public void create() {
        if (this.text != null) this.button = new JButton(this.text.getText());
        else this.button = new JButton();

        this.button.setBounds(
                this.getItemRegion().getMin().getX(),
                this.getItemRegion().getMin().getY(),
                this.modularPosition.getWidth(), this.modularPosition.getHeight()
        );

        int fontSize = (int) (1 + (Math.min(this.modularPosition.getWidth(), this.modularPosition.getHeight()) * 0.3));
        this.button.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));

        this.button.addActionListener(event -> execute.execute());
        this.button.setBackground(Color.white);
        this.button.setOpaque(true);
        this.button.setBorderPainted(false);
    }

    @Override
    public void updateItem() {
        this.button.setBounds(
                this.modularPosition.getPos1().getX(),
                this.modularPosition.getPos1().getY(),
                this.modularPosition.getWidth(),
                this.modularPosition.getHeight()
        );
    }

    @Override
    public void onHover() {
        this.button.setBackground(Color.gray);

        if (this.text == null) return;
        if (this.text.getOnHover() == null) return;

        this.button.setText(this.text.getOnHover());
    }

    @Override
    public void onNotHover() {
        this.button.setBackground(Color.WHITE);

        if (this.text == null) return;

        this.button.setText(this.text.getText());
    }

    @Override
    public void render(JPanel frame) {
        this.create();
        frame.add(this.button);
    }

    @Override
    public Component getComponent() {
        return this.button;
    }
}
