package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    private static final String SNAKE_HEAD = "snakehead.png";
    private static final String SNAKE_BODY = "snakebody.jpg";

    private TextureRegion head, body;
    private boolean isHead;
    private int absoluteCol;
    private int absoluteRow;
    private Directions direction;
//    private Texture texture;

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
    public Piece(int newAbsoluteCol, int newAbsoluteRow, int width, boolean isHead, Directions direction) {
        this.absoluteCol = newAbsoluteCol;
        this.absoluteRow = newAbsoluteRow;
        this.width = width;
        this.body = new TextureRegion(new Texture(Piece.SNAKE_BODY));
        this.head = new TextureRegion(new Texture(Piece.SNAKE_HEAD));
        this.isHead = isHead;
        this.direction = direction;
//        this.texture = new Texture(newImage);
//        this.image = newImage;
    }

    /**
     * Method to draw the sprite texture
     * @param spriteBatch Platform to draw textures
     */
    public void render(SpriteBatch spriteBatch) {
        TextureRegion textureToDraw = (this.isHead) ? this.head: this.body;
        int degreesRote = 0;
        switch (direction){
            case UP:
                degreesRote = 0;
                break;
            case DOWN:
                degreesRote = 180;
                break;
            case LEFT:
                degreesRote = 90;
                break;
            case RIGHT:
                degreesRote = 270;
                break;
        }
        spriteBatch.begin();
        spriteBatch.draw(textureToDraw, this.absoluteCol, this.absoluteRow, this.width/2, this.width/2,
                                width, width, 1, 1, degreesRote);
        spriteBatch.end();
    }

    /**
     * Method to dispose texture from graphic buffer
     */
    public void dispose() {
        if (this.head != null)
            this.head.getTexture().dispose();
        if (this.body != null)
            this.body.getTexture().dispose();
//        if (this.texture != null) texture.dispose();
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
        return new Piece(this.absoluteCol, this.absoluteRow, this.width, false, this.direction);
    }

    public void setHead(){
        this.isHead = true;
    }

    public void unsetHead(){
        this.isHead = false;
    }

    public void setDirection(Directions direction){
        this.direction = direction;
    }

}
