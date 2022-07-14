package me.smudge.client.game;

import me.smudge.client.engine.Application;
import me.smudge.client.game.layout.BoardLayout;
import me.smudge.client.items.chessboard.pieces.King;
import me.smudge.client.positions.Position;
import me.smudge.client.positions.Region2D;
import me.smudge.client.positions.TilePosition;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a chessboard
 */
public class ChessBoard {

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
     * The log of the moves that have officially
     * been made on the board
     */
    private ChessMoveLog log;

    /**
     * The list of tiles
     * By default the tiles are in order.
     * However, the order of the tiles does not matter when rendering
     * as it's based on the tile position.
     */
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Create a new instance of {@link ChessBoard}
     * @param amountOfTilesX The amount of tiles in the x direction
     * @param amountOfTilesY The amount of tiles in the y direction
     */
    public ChessBoard(int amountOfTilesX, int amountOfTilesY) {
        this.amountOfTilesX = amountOfTilesX;
        this.amountOfTilesY = amountOfTilesY;

        this.log = new ChessMoveLog();
    }

    /**
     * Used to clone a chess board
     * @param board board to clone
     */
    public ChessBoard(ChessBoard board) {
        this.amountOfTilesX = board.amountOfTilesX;
        this.amountOfTilesY = board.amountOfTilesY;

        this.log = new ChessMoveLog(board.log);

        this.setup();
        for (Tile tile : board.getAllPieces()) {
            this.getTile(tile.getTilePosition()).setPiece(tile.getPiece());
        }
    }

    /**
     * Used to set up the tiles on the
     * board based on the size.
     */
    public void setup() {
        ChessColour tileColour = ChessColour.WHITE;

        for (int y = 1; y < this.amountOfTilesX + 1; y++) {
            for (int x = 1; x < this.amountOfTilesY + 1; x++) {

                if (x != 1) tileColour = ChessColour.opposite(tileColour);

                this.addTile(new Tile(tileColour, new TilePosition(x, y)));
            }
        }
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

    /**
     * @return All tiles on the board
     */
    public ArrayList<Tile> getAllTiles() {
        return this.tiles;
    }

    /**
     * @return All tiles with a piece on
     */
    public ArrayList<Tile> getAllPieces() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile tile : this.tiles) {
            if (tile == null || tile.getPiece() == null) continue;
            tiles.add(tile);
        }
        return tiles;
    }

    /**
     * Used to get all pieces that are of a certain colour
     * @param colour Colour of the pieces
     * @return List of tiles with these pieces on
     */
    public ArrayList<Tile> getAllPieces(ChessColour colour) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile tile : this.tiles) {
            if (tile == null || tile.getPiece() == null) continue;
            if (tile.getPiece().getColour() != colour) continue;
            tiles.add(tile);
        }
        return tiles;
    }

    /**
     * Used to calculate how many pieces are in
     * the path of the two tiles
     * @param tile1 The first tile
     * @param tile2 The second tile
     * @return Amount of pieces between the tiles
     */
    public int getPiecesBetween(Tile tile1, Tile tile2) {
        TilePosition vector = new TilePosition(
                tile2.getTilePosition().getX() - tile1.getTilePosition().getX(),
                tile2.getTilePosition().getY() - tile1.getTilePosition().getY()
        );

        int amount = 0;

        // Starting at tile 2, loop across the tiles between
        while (vector.not0()) {
            TilePosition temp = tile1.getTilePosition().add(
                    vector.getX(),
                    vector.getY()
            );

            if (this.isPieceAt(temp)) amount ++;

            vector.decrease();
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
                if (!tile.getPiece().getOptions().canJump) continue;
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
            if (tile.getPiece().getOptions().canJump) {
                tiles.add(temp);
                continue;
            }

            // If the piece is blocked
            if (this.getPiecesBetween(tile, temp) > 1) continue;

            tiles.add(temp);
        }

        return tiles;
    }

    public ArrayList<ChessMove> getPossibleMoveForColour(ChessColour colour) {
        ArrayList<ChessMove> moves = new ArrayList<>();

        for (Tile from : this.getAllPieces(colour)) {
            for (Tile to : this.getPossibleMoves(from)) {
                moves.add(new ChessMove(from, to, from.getPiece()));
            }
        }

        return moves;
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
     * Used to update the board layout
     * @param boardLayout The layout
     */
    public void setLayout(BoardLayout boardLayout) {
        boardLayout.update(this);
    }

    /**
     * Used to make an official move
     * @param move The chess move made
     */
    public void makeMove(ChessMove move) {
        this.getTile(move.getFrom()).setEmpty();
        this.getTile(move.getTo()).setPiece(move.getPiece());

        // Add the move to the log
        this.log.add(move);
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
     * Determine if a piece is at a location
     * @param position Position to check
     * @return True if there is a piece on the tile
     */
    public boolean isPieceAt(TilePosition position) {
        Tile tile = this.getTile(position);
        if (tile == null) return false;
        return tile.getPiece() != null;
    }

    /**
     * Used to see if a controller is able to end the game
     * @param moves List of tiles to check
     * @return If there is a tile that has end game set to true
     */
    public boolean includesCanEndGame(ArrayList<ChessMove> moves) {
        for (ChessMove move : moves) {
            if (this.getTile(move.getTo()).getPiece() == null) continue;
            if (this.getTile(move.getTo()).getPiece().getOptions().endsGame) return true;
        }
        return false;
    }

    /* ---------- Rendering ---------- */

    /**
     * Used to render the board
     * @param panel The chessboard panel
     */
    public void render(JButton panel) {
        panel.setLayout(new GridLayout(this.amountOfTilesX, this.amountOfTilesY, 0 ,0));
        panel.setBorder(new LineBorder(Color.BLACK, 5));

        this.tileWidth = panel.getWidth() / this.amountOfTilesX;
        this.tileHeight = panel.getHeight() / this.amountOfTilesY;

        HashMap<Integer, JButton> listOfTiles = this.getSortedListOfTiles();

        for (int index = 0; index < listOfTiles.size(); index++) {
            panel.add(listOfTiles.get(index));
        }

        panel.setVisible(true);
    }

    /**
     * Used to get the sorted list of tiles to render
     * Essentially each tile will get an index number which is the order
     * of which they are added to the table
     * @return Map of the panel index and tile button
     */
    private HashMap<Integer, JButton> getSortedListOfTiles() {
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

    public void asString() {
        for (int y = 1; y < this.amountOfTilesX + 1; y++) {
            for (int x = 1; x < this.amountOfTilesY + 1; x++) {
                Tile tile = this.getTile(new TilePosition(x, y));
                if (this.isPieceAt(tile.getTilePosition())) {
                    System.out.print(this.getTile(new TilePosition(x, y)).getPiece().getClass().getSimpleName().split("")[0]);
                }
                else {
                    System.out.print("x");
                }
            }
            System.out.println();
        }
    }
}
