package me.smudge.client.pages;

import me.smudge.client.items.Item;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Represents a GUI interface
 */
public abstract class Page {

    /**
     * Contains all the items to be rendered and updated
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Used to create a new instance of a page
     */
    public Page() {}

    /**
     * Used to add an item to the page
     * @param item Item to add to the page
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Used to remove an item from the pagge
     * @param item Item to be removed
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Used to get the items on the page
     * @return List of items
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Used to update all items on the page
     */
    public void update() {
        this.items.forEach(Item::update);
    }

    /**
     * Used to render all items on the page
     * @param panel The window to render
     */
    public void render(JPanel panel) {
        this.onRender();
        this.items.forEach(item -> item.render(panel));
    }

    /**
     * Called before rendering
     */
    public abstract void onRender();

    /**
     * Used to delete all items from the page
     */
    protected void deleteItems() {
        this.items = new ArrayList<>();
    }
}
