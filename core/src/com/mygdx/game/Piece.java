package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
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
    private final String image;

    private int absoluteCol;
    private int absoluteRow;
    private Texture texture;

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
     * @param width          width of piece
     * @param newAbsoluteRow New absolute position Y 
     * @param newAbsoluteCol New absolute position X
     * @param newImage       route of the sprite to loading
     */
    public Piece(int newAbsoluteCol, int newAbsoluteRow, int width, String newImage) {
        this.absoluteCol = newAbsoluteCol;
        this.absoluteRow = newAbsoluteRow;
        this.width = width;
        this.texture = new Texture(newImage);
        this.image = newImage;
    }

    /**
     * Method to draw the sprite texture
     * @param spriteBatch Platform to draw textures
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(this.texture, this.absoluteCol, this.absoluteRow, this.width, this.width);
        spriteBatch.end();
    }

    /**
     * Method to dispose texture from graphic buffer
     */
    public void dispose() {
        if (this.texture != null) texture.dispose();
    }

    /**
     * Method getter for the atribute position X
     * @return Current position from the piece
     */
    public float getAbsoluteCol() {
        return this.absoluteCol;
    }

    /**
     * Method getter for the atribute position Y
     * @return Current position Y from the piece
     */
    public float getAbsoluteRow() {
        return this.absoluteRow;
    }

    /**
     * Rapid Method to increment X position
     */
    public void incrementCol() {
        this.absoluteCol+=width;
    }

    /**
     * Rapid Method to decrement X position
     */
    public void decrementCol() {
        this.absoluteCol-=width;
    }

    /**
     * Rapid Method to increment Y position
     */
    public void incrementRow() {
        this.absoluteRow+=width;
    }

    /**
     * Rapid Method to decrement Y position
     */
    public void decrementRow() {
        this.absoluteRow-=width;
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
        return new Piece(this.absoluteCol, this.absoluteRow, this.width, this.image);
    }

}
