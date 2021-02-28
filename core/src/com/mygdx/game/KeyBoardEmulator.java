package com.mygdx.game;

import static com.mygdx.game.Directions.*;

public class KeyBoardEmulator {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final float MARGIN_LIMIT = .3f;

    private final int displayWidth;
    private final int displayHeight;
    private final int yOffset;
    private final int xOffset;

    private float leftLimit;
    private float rightLimit;
    private float topLimit;
    private float botLimit;
    private Directions moveSelected;


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
     * @param newXOffset Margin in X to center the game screen
     * @param newYOffset Margin in Y to center the game screen
     * @param newDisplayWidht new display width size
     * @param newdisplayHeight new display height size
     */
    public KeyBoardEmulator(int newXOffset, int newYOffset, int newDisplayWidth, int newDisplayHeight){
        this.displayHeight = newDisplayHeight;
        this.displayWidth = newDisplayWidth;
        this.yOffset = newYOffset;
        this.xOffset = newXOffset;
        this.calculateLimits();
        this.moveSelected = null;
    }

    /**
     * Method for calculate buttons limits
     */
    private void calculateLimits(){
        this.leftLimit = this.xOffset + this.displayWidth * KeyBoardEmulator.MARGIN_LIMIT;
        this.rightLimit = this.xOffset + this.displayWidth * this.oppositePercentSide();
        this.topLimit =  this.yOffset + this.displayHeight * KeyBoardEmulator.MARGIN_LIMIT;
        this.botLimit =  this.yOffset + this.displayHeight * this.oppositePercentSide();
    }

    /**
     * Method for calculate the opposite percent
     * @return percent to calculate the opposite side button
     */
    private float oppositePercentSide(){
        return 1 - KeyBoardEmulator.MARGIN_LIMIT;
    }

    /**
     * Method to translate a Cartesian position to a known variable
     * @param positionX the X position from the object to be emulated
     * @param positionY the X position from the object to be emulated
     */
    public void emulate(int positionX, int positionY){
        this.checkAxisX(positionX);
        this.checkAxisY(positionY);
    }

    /**
     * Method to check if X position is touching simulated button
     * @param position X axis position to be evaluated
     */
    private void checkAxisX(int position){
        if (this.touchOnLeftSite(position))
            this.moveSelected = LEFT;
        if (this.touchOnRightSite(position))
            this.moveSelected = RIGHT;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true only if the position is in the limits
     */
    private boolean touchOnLeftSite(int position){
        return this.xOffset <= position && position <= this.leftLimit;
    }

    /**
     * Method to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true only if the position is in the limits
     */
    private boolean touchOnRightSite(int position){
        return this.rightLimit <= position && position <= this.xOffset + this.displayWidth;
    }

    /**
     * Method to check if Y position is touching simulated button
     * @param position Y axis position to be evaluated
     */
    private void checkAxisY(int position){
        if (this.touchOnTopSite(position))
            this.moveSelected = UP;
        if (this.touchOnBotSite(position))
            this.moveSelected = DOWN;
    }

    /**
     * Method to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true only if the position is in the limits
     */
    private boolean touchOnTopSite(int position){
        return this.yOffset <= position && position <= this.topLimit;
    }

    /**
     * Method to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true only if the position is in the limits
     */
    private boolean touchOnBotSite(int position){
        return this.botLimit <= position && position <= this.yOffset + this.displayHeight;
    }

    /**
     * Getter for the attribute moveSelected
     * @return snake move code
     */
    public Directions getMoveSelected(){
        return this.moveSelected;
    }
}
