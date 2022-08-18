package me.smudge.client.items;

import me.smudge.client.Chess;
import me.smudge.client.pages.Page;
import me.smudge.client.positions.Position;

import java.util.ArrayList;

/**
 * Represents a collection of items
 * Makes it easy to dynamically display a collection of items
 */
public class ItemCollection {

    /**
     * List of items
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Layout options
     */
    private int columns = 1;
    private int padding = 0;

    /**
     * New instance of an item collection
     */
    public ItemCollection() {}

    /**
     * @param item Item to be added to the {@link ItemCollection}
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * @param item Item to be removed from the {@link ItemCollection}
     */
    public void remove(Item item) {
        this.items.remove(item);
    }

    /**
     * Used to calculate and update the positions
     * This is based on the window size, amount of items and layout options
     */
    public void calculate() {
        // Amount of items per column
        int amountPerColumn = (int) Math.ceil(this.getModularItems().size() / (double)this.columns);

        for (Item item : this.items) {
            if (item.getModularPosition() == null) continue;

            // The index of the item after filtering static items
            int index = this.getModularItems().indexOf(item) + 1;

            // Width and height to set the items
            int columnWidth = this.getWidest(this.getModularItems()).getModularPosition().getWidth();
            int rowHeight = this.getHighest(this.getModularItems()).getModularPosition().getHeight();

            // Dividing the screen depending on the amount of columns and rows
            int columnMultiplier = (Chess.getConfig().getScreenSize().get(0) + this.padding) / (this.columns + 1);
            int rowMultiplier = (Chess.getConfig().getScreenSize().get(1) + this.padding) / (amountPerColumn + 1);

            // Difference from default screen size
            double sizeMultiplierX = Chess.getConfig().getScreenSize().get(0) / (double)1000;
            double sizeMultiplierY = Chess.getConfig().getScreenSize().get(1) / (double)600;

            // If the size is smaller, set it so there is no padding
            if (columnMultiplier < columnWidth) columnMultiplier = columnWidth;
            if (rowMultiplier < rowHeight) rowMultiplier = rowHeight;

            // The new size of the item
            double sizeX = item.getModularPosition().getFirstWidth() * sizeMultiplierX;
            double sizeY = item.getModularPosition().getFirstHeight() * sizeMultiplierY;

            // What column the item is in
            int currentColumn = (int) Math.ceil(index / (double)amountPerColumn);
            if (index / (double)amountPerColumn == 1) currentColumn = index / amountPerColumn;
            if (currentColumn == 0) currentColumn = 1;

            // What row the item is on
            int currentRow = index - ((currentColumn - 1) * amountPerColumn);

            int x = columnMultiplier * currentColumn;
            x -= item.getModularPosition().getWidth() / 2;

            int y = rowMultiplier * currentRow;
            y -= item.getModularPosition().getHeight() / 2;

            if (item.getModularPosition().isStatic()) {
                item.getModularPosition().setWidth((int)sizeX);
                item.getModularPosition().setHeight((int)sizeY);
                continue;
            }

            item.getModularPosition().setPos1(new Position(x, y));

            item.getModularPosition().setWidth((int)sizeX);
            item.getModularPosition().setHeight((int)sizeY);

            item.getModularPosition().setToFirstPosition();
        }
    }

    /**
     * Get the items that have modular positions
     */
    private ArrayList<Item> getModularItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getModularPosition() != null) items.add(item);
        }

        return items;
    }

    /**
     * Get items that are centered
     */
    private ArrayList<Item> getCenteredItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getModularPosition() == null) continue;
            if (item.getModularPosition().isCentered())
            items.add(item);
        }

        return items;
    }

    /**
     * Get items that are static
     */
    private ArrayList<Item> getStaticItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getModularPosition() == null) continue;
            if (item.getModularPosition().isStatic())
                items.add(item);
        }

        return items;
    }

    /**
     * Get the widest item in a list
     * @param modularItems Items to sort
     */
    private Item getWidest(ArrayList<Item> modularItems) {
        Item widest = modularItems.get(0);

        for (Item item : modularItems) {
            if (widest.getModularPosition().getWidth() >=
                    item.getModularPosition().getWidth()) continue;
            widest = item;
        }

        return widest;
    }

    /**
     * Get the highest item in a list
     * @param modularItems Items to sort
     */
    private Item getHighest(ArrayList<Item> modularItems) {
        Item heighest = modularItems.get(0);

        for (Item item : modularItems) {
            if (heighest.getModularPosition().getHeight() >=
                    item.getModularPosition().getHeight()) continue;
            heighest = item;
        }

        return heighest;
    }

    /**
     * Used to set the amount of columns in the collection
     * @param columns Amount of columns
     * @return This instance
     */
    public ItemCollection setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Used to set extra distance between the items
     * @param padding Amount of padding
     * @return This instance
     */
    public ItemCollection setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    /**
     * Add the items to the page
     */
    public void toPage(Page page) {
        this.calculate();
        for (Item item : items) page.addItem(item);
    }
}
