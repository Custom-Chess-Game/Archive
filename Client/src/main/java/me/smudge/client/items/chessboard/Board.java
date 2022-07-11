package me.smudge.client.items.chessboard;

import me.smudge.client.engine.Application;
import me.smudge.client.items.chessboard.layout.BoardLayout;
import me.smudge.client.positions.Region2D;
import me.smudge.client.positions.TilePosition;
import me.smudge.client.positions.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a chessboard
 */
public class Board {

    /**
     * The amount of chess tile on the board
     */
    private final int amountOfTilesX;
    private final int amountOfTilesY;

    /**
     * Height and width of tiles
     */
    private int tileHeight;
    private int tileWidth;

    /**
     * If the board is flipped
     * When flipped the renderer will calculate the flipped version of the board
     */
    private boolean flipped;

    /**
     * The list of tiles
     * By default the tiles are in order.
     * However, the order of the tiles does not matter when rendering
     * as it's based on the tile position.
     */
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Create a new instance of {@link Board}
     * @param amountOfTilesX The amount of tiles in the x direction
     * @param amountOfTilesY The amount of tiles in the y direction
     */
    public Board(int amountOfTilesX, int amountOfTilesY) {
        this.amountOfTilesX = amountOfTilesX;
        this.amountOfTilesY = amountOfTilesY;
    }

    /**
     * Used to get the tile at a position
     * The position doesn't have to be the same instance
     * @param tilePosition Tile position
     * @return Tile at the position
     */
    public Tile getTile(TilePosition tilePosition) {
        for (Tile tile : this.tiles) {
            if (tile.getTilePosition().contains(tilePosition)) return tile;
        }
        return null;
    }

    /**
     * Used to add a tile to the board
     * To change a tile you must get the instance.
     * @param tile Tile instance
     */
    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    /**
     * Used to remove a tile from the board
     * @param tilePosition The tiles position
     */
    public void removeTile(TilePosition tilePosition) {
        this.tiles.remove(this.getTile(tilePosition));
    }

    /**
     * Get the amount of tiles in the x direction
     */
    public int getAmountOfTilesX() {
        return this.amountOfTilesX;
    }

    /**
     * Get the amount of tiles in the y direction
     */
    public int getAmountOfTilesY() {
        return this.amountOfTilesY;
    }

    /**
     * Used to render the board
     * @param panel The chessboard panel
     */
    public void render(JButton panel) {
        panel.setLayout(new GridLayout(this.amountOfTilesX, this.amountOfTilesY, 0 ,0));
        panel.setBorder(new LineBorder(Color.BLACK, 5));

        this.tileWidth = panel.getWidth() / this.amountOfTilesX;
        this.tileHeight = panel.getHeight() / this.amountOfTilesY;

        HashMap<Integer, JButton> listOfTiles = this.getSortedListOfTiles(panel);

        for (int index = 0; index < listOfTiles.size(); index++) {
            panel.add(listOfTiles.get(index));
        }

        panel.setVisible(true);
    }

    /**
     * Used to create the board based on the size.
     */
    public void create() {
        ChessColour tileColour = ChessColour.WHITE;

        for (int y = 1; y < this.amountOfTilesX + 1; y++) {
        for (int x = 1; x < this.amountOfTilesY + 1; x++) {

            if (x != 1) tileColour = ChessColour.opposite(tileColour);

            this.addTile(new Tile(tileColour, new TilePosition(x, y)));
        }}
    }

    /**
     * Used to get the sorted list of tiles to render
     * Essentially each tile will get an index number which is the order
     * of which they are added to the table
     * @return Map of the panel index and tile button
     * @param panel
     */
    private HashMap<Integer, JButton> getSortedListOfTiles(JButton panel) {
        HashMap<Integer, JButton> listOfTiles = new HashMap<>();
        ArrayList<Tile> tiles = new ArrayList<>(this.tiles);

        for (Tile tile : tiles) {
            JButton tilePanel = new JButton();
            tile.render(tilePanel);
            tilePanel.setVisible(true);

            int x = tile.getTilePosition().getX();
            int y = tile.getTilePosition().getY();

            if (!this.flipped) {
                x = tile.getTilePosition().getX();
                y = (this.amountOfTilesY - tile.getTilePosition().getY()) + 1;
            }

            int index = ((this.amountOfTilesY * y) + x) - 9;

            listOfTiles.put(index, tilePanel);

            // Update tile position to check for hover event
            Position pos1 = new Position(
                    (x * this.tileWidth) - this.tileWidth,
                    (y * this.tileHeight) - this.tileHeight
            );
            tile.setRegion(new Region2D(
                    pos1,
                    pos1.add(this.tileWidth, this.tileHeight)
            ));
        }

        return listOfTiles;
    }

    /**
     * Used to update the board layout
     * @param boardLayout The layout
     */
    public void setLayout(BoardLayout boardLayout) {
        boardLayout.update(this);
    }

    /**
     * Used to flip the chess board
     */
    public void flip() {
        this.flipped = !flipped;

        // Render the new board
        Application.render();
    }

    /**
     * Used to get the tile panel from the mouse position
     * @param mouseLocation Mouse position
     * @return Instance of the tile
     */
    public Tile getTileAt(Position mouseLocation) {
        for (Tile tile : this.tiles) {
            if (tile.getRegion().contains(mouseLocation)) return tile;
        }
        return null;
    }

    public boolean isPieceAt(TilePosition position) {
        Tile tile = this.getTile(position);
        if (tile == null) return false;
        return tile.getPiece() != null;
    }

    public int getPiecesBetween(Tile tile1, Tile tile2) {
        System.out.println("------ DEBUG ------");
        TilePosition vector = new TilePosition(
                tile2.getTilePosition().getX() - tile1.getTilePosition().getX(),
                tile2.getTilePosition().getY() - tile1.getTilePosition().getY()
        );
        System.out.println("Starting Vector : " + vector.asString());
        System.out.println("From : " + tile1.getTilePosition().asString());
        System.out.println("To : " + tile2.getTilePosition().asString());

        // (5, -5)

        int amount = 0;

        while (vector.not0()) {
            TilePosition temp = tile1.getTilePosition().add(
                    vector.getX(),
                    vector.getY()
            );

            if (this.isPieceAt(temp)) {
                System.out.println("=>" + "Position where there is a piece : " + temp.asString());
                System.out.println("Vector : " + vector.asString());
                amount ++;
            }

            vector = vector.decrease();
        }

        return amount;
    }

    /**
     * Get the possible moves for a piece on the board
     * @param tile The tile on the board to check
     * @return List of tiles it can move to
     */
    public ArrayList<Tile> getPossibleMoves(Tile tile) {
        if (tile == null) return new ArrayList<>();
        if (tile.getPiece() == null) return new ArrayList<>();

        ArrayList<Tile> tiles = new ArrayList<>();

        // Add tiles that have no pieces on
        for (Tile temp : tile.getPiece().getValidPositions(this, tile)) {
            if (temp == null) continue;
            if (temp.getPiece() != null) continue;

            // Is there a piece blocking the square
            if (this.getPiecesBetween(tile, temp) > 0) {
                if (!tile.getPiece().canJump()) continue;
            }
            tiles.add(temp);
        }

        // Add tiles that the piece can take
        for (Tile temp : tile.getPiece().getTakePositions(this, tile)) {
            if (temp == null) continue;
            if (temp.getPiece() == null) continue;

            // If the piece is the other players colour
            if (temp.getPiece().getColour() == tile.getPiece().getColour()) continue;

            // If the piece can jump
            if (tile.getPiece().canJump()) {
                tiles.add(temp);
                continue;
            }

            // If the piece is blocked
            if (this.getPiecesBetween(tile, temp) > 1) continue;

            tiles.add(temp);
        }

        return tiles;

//        ArrayList<Tile> tilesToCheckCanTake = tile.getPiece().getTakePositions(this, tile);
//        ArrayList<Tile> tilesToCheckCanMove = tile.getPiece().getValidPositions(this, tile);
//
//        tilesToCheckCanMove.addAll(tilesToCheckCanTake);

//        // Loop for all the tiles the piece could move into
//        for (Tile temp : tilesToCheckCanMove) {
//            if (temp == null) continue;
//
//            // If there is a piece in the square
//            if (temp.getPiece() != null) {
//
//                // Check if the piece can take it
//                if (!tilesToCheckCanTake.contains(temp)) continue;
//
//                this.tiles.add(temp);
//            }
//
//            // If there is not a piece in the tile, and it's a take move, don't include tile
//            if (temp.getPiece() == null && tilesToCheckCanTake.contains(temp) &&
//                    !tile.getPiece().getValidPositions(this, tile).contains(temp)) {
//                continue;
//            }
//
//            if (this.getPiecesBetween(tile, temp) == 0) tiles.add(temp);
//
//            if (temp.getPiece() == null) continue;
//
//            if (tile.getPiece().canJump() && tile.getPiece().getColour() != temp.getPiece().getColour()) tiles.add(temp);
//
//            if (tilesToCheckCanTake.contains(temp) &&
//                this.getPiecesBetween(tile, temp) == 1 &&
//                tile.getPiece().getColour() != temp.getPiece().getColour()) tiles.add(temp);
//        }
//
//        return tiles;
    }

    public ArrayList<Tile> getAllTiles() {
        return this.tiles;
    }
}
