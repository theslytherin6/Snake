package com.mygdx.game;

import static com.mygdx.game.Directions.*;

public class KeyBoardEmulator {

    private float displayWidth,
                displayHeight,
                leftLimit,
                rightLimit,
                topLimit,
                botLimit,
                yOffset,
                xOffset;

    private Directions moveSelected;

    private static final float MARGIN_LIMIT = .3f;

    /**
     * @param DisplayWidth display width size
     * @param displayHeight display height size
     */
    public KeyBoardEmulator(float newXOffset, float newYOffset, float newDisplayWidth, float newDisplayHeight){
        this.displayHeight = newDisplayHeight;
        this.displayWidth = newDisplayWidth;
        this.yOffset = newYOffset;
        this.xOffset = newXOffset;
        this.calculateLimits();
        this.moveSelected = null;
        // debugging prints
        System.out.println("LIMITS");
        System.out.println("T:" + this.topLimit);
        System.out.println("D:" + this.botLimit);
        System.out.println("L:" + this.leftLimit);
        System.out.println("R:" + this.rightLimit);
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
        System.out.println(positionX);
        System.out.println(positionY);
        System.out.println("--");
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
     * Metho to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnTopSite(int position){
        System.out.print("Top: ");
        System.out.println(this.yOffset + " " + position + " " + this.topLimit);
        return this.yOffset <= position && position <= this.topLimit;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnBotSite(int position){
        System.out.print("bot: ");
        System.out.println(this.botLimit + " " + position + " " + this.displayHeight);
        return this.botLimit <= position && position <= this.yOffset + this.displayHeight;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnLeftSite(int position){
        System.out.print("Left: ");
        System.out.println(this.xOffset + " " + position + " " + this.leftLimit);
        return this.xOffset <= position && position <= this.leftLimit;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnRightSite(int position){
        System.out.print("Rigth: ");
        System.out.println(this.rightLimit + " " + position + " " + this.displayWidth);
        return this.rightLimit <= position && position <= this.xOffset + this.displayWidth;
    }

    /**
     * Getter for the attribute moveSelected
     * @return snake move code
     */
    public Directions getMoveSelected(){
        return this.moveSelected;
    }
}
