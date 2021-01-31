package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piece 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private float width;
    private int relativeCol;
    private int relativeRow;
    private Texture texture;
    private String image;

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
     * @param width width of piece
     * @param newRelativeRow Y relative position that piece it's going to have
     * @param newRelativeCol X relative position that piece it's going to have
     * @param newImage route of the sprite to loading
     */
    public Piece(float width, int newRelativeRow, int newRelativeCol, String newImage) {
        this.width = width;
        this.relativeRow = newRelativeRow;
        this.relativeCol = newRelativeCol;
        this.texture = new Texture(newImage);
        this.image = newImage;
    }

    /**
     * Method to draw the sprite texture
     *
     * @param spriteBatch Platform to draw textures
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(this.texture, this.absoluteCol(), this.absoluteRow(), this.width, this.width);
        spriteBatch.end();
    }

    /**
     * Method to get the absolute position in pixel of the abscissa X
     *
     * @return absolute position in pixel of the abscissa X
     */
    private float absoluteCol(){
        return this.relativeCol*this.width;
    }

    /**
     * Method to get the absolute position in pixel of the abscissa Y
     *
     * @return absolute position in pixel of the abscissa Y
     */
    private float absoluteRow(){
        return this.relativeRow*this.width;
    }

    /**
     * Method to dispose texture from graffic buffer
     */
    public void dispose() {
        texture.dispose();
    }

    /**
     * Method getter for the atribute position X
     *
     * @return Acual position from the piece
     */
    public int getRelativeCol() {
        return this.relativeCol;
    }

    /**
     * Method getter for the atribute position Y
     *
     * @return Acual position Y from the piece
     */
    public int getRelativeRow() {
        return this.relativeRow;
    }

    /**
     * Rapid Method to increment X position
     */
    public void incrementCol() {
        this.relativeCol++;
    }

    /**
     * Rapid Method to decrement X position
     */
    public void decrementCol() {
        this.relativeCol--;
    }

    /**
     * Rapid Method to increment Y position
     */
    public void incrementRow() {
        this.relativeRow++;
    }

    /**
     * Rapid Method to decrement Y position
     */
    public void decrementRow() {
        this.relativeRow--;
    }

    /**
     * Method to initialize the colider
     */
    public boolean isColliding(Piece piece) {
        return this.relativeRow == piece.getRelativeRow() && this.relativeCol == piece.getRelativeCol();
    }

    /**
     * Method that return a complete cloning of her self
     * @return a instance of the clone piece
     */
    public Piece clone(){
        return new Piece(this.width, this.relativeRow, this.relativeCol, this.image);
    }

}