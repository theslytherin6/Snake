package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piece {
    private float width;
    private int positionX;
    private int positionY;
    private final Texture texture = new Texture("badlogic.jpg");

    /**
     * Contructor for a Piece
     * @param newPositionX Position X for the new piece
     * @param newPositionY Posicion Y for the new piece
     * @param newWidth Width for the new piece
     */
    public Piece(int newPositionX, int newPositionY, float newWidth) {
        this.positionX = newPositionX;
        this.positionY = newPositionY;
        this.width = newWidth;
    }

    /**
     * Method for draw the sprite texture
     * @param spriteBatch Platform to draw textures
     */
    public void render(SpriteBatch spriteBatch){
        spriteBatch.begin();
        spriteBatch.draw(this.texture, this.positionX * this.width, this.positionY * this.width);
        spriteBatch.end();
    }

    /**
     * Method to dispose texture from graffic buffer
     */
    public void dispose() {
        texture.dispose();
    }

    /**
     * Method setter for the atrbute position X
     * @param newPositionX new position X for the piece
     */
    public void setPositionX(int newPositionX){
        this.positionX = newPositionX;
    }

    /**
     * Method getter for the atribute position X
     * @return Acual position from the piece
     */
    public int getPositionX(){
        return this.positionX;
    }

    /**
     * Method setter for the atrbute position Y
     * @param newPositionY new position Y for the piece
     */
    public void setPositionY(int newPositionY) {
        this.positionY = newPositionY;
    }

    /**
     * Method getter for the atribute position Y
     * @return Acual position Y from the piece
     */
    public int getPositionY(){
        return this.positionY;
    }

    /**
     * Method setter for the atrbute width
     * @param newWidth new width for the piece
     */
    public void setWidth(float newWidth){
        this.width = newWidth;
    }

    /**
     * Rapid Method to increment X position
     */
    public void incrementPositionX(){
        this.setPositionX(this.positionX++);
    }

    /**
     * Rapid Method to decrement X position
     */
    public void decrementPositionX(){
        this.setPositionX(this.positionX--);
    }

    /**
     * Rapid Method to increment Y position
     */
    public void incrementPositioY(){
        this.setPositionX(this.positionY++);
    }

    /**
     * Rapid Method to decrement Y position
     */
    public void decrementPositionY(){
        this.setPositionX(this.positionY--);
    }
}
