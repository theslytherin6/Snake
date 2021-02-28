package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piece {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final int width;

    private int absoluteCol;
    private int absoluteRow;
    private final static String BODY_IMG = "snakebody.jpg";
    private final static String HEAD_IMG = "snakehead.png";
    private final Texture SNAKE_BODY = new Texture(Piece.BODY_IMG);
    private final Texture SNAKE_HEAD = new Texture(Piece.HEAD_IMG);
    private boolean isHead;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// BEHAVIOR//CONDUCT
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Builder of piece
     *
     * @param width          width of piece
     * @param newAbsoluteRow New absolute position Y
     * @param newAbsoluteCol New absolute position X
     */
    public Piece(int newAbsoluteCol, int newAbsoluteRow, int width) {
        this.absoluteCol = newAbsoluteCol;
        this.absoluteRow = newAbsoluteRow;
        this.width = width;
    }

    /**
     * Method to draw the sprite texture
     *
     * @param spriteBatch Platform to draw textures
     */
    public void render(SpriteBatch spriteBatch) {
        if (this.isHead) {
            spriteBatch.begin();
            spriteBatch.draw(this.SNAKE_HEAD, this.absoluteCol, this.absoluteRow, this.width, this.width);
            spriteBatch.end();
        } else {
            spriteBatch.begin();
            spriteBatch.draw(this.SNAKE_BODY, this.absoluteCol, this.absoluteRow, this.width, this.width);
            spriteBatch.end();
        }
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    /**
     * Method to dispose texture from graphic buffer
     */
    public void dispose() {
        if (this.SNAKE_BODY != null) SNAKE_BODY.dispose();
    }

    /**
     * Method getter for the atribute position X
     *
     * @return Current position from the piece
     */
    public float getAbsoluteCol() {
        return this.absoluteCol;
    }

    /**
     * Method getter for the atribute position Y
     *
     * @return Current position Y from the piece
     */
    public float getAbsoluteRow() {
        return this.absoluteRow;
    }

    /**
     * Rapid Method to increment X position
     */
    public void incrementCol() {
        this.absoluteCol += width;
    }

    /**
     * Rapid Method to decrement X position
     */
    public void decrementCol() {
        this.absoluteCol -= width;
    }

    /**
     * Rapid Method to increment Y position
     */
    public void incrementRow() {
        this.absoluteRow += width;
    }

    /**
     * Rapid Method to decrement Y position
     */
    public void decrementRow() {
        this.absoluteRow -= width;
    }

    /**
     * Method to initialize the colider
     */
    public boolean isColliding(Piece piece) {
        return this.absoluteRow == piece.getAbsoluteRow() && this.absoluteCol == piece.getAbsoluteCol();
    }

    /**
     * Method that return a complete cloning of herself
     *
     * @return a instance of the clone piece
     */
    public Piece clone() {
        return new Piece(this.absoluteCol, this.absoluteRow, this.width);
    }
}