package com.mygdx.game;

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

    private int displayWidth,
                displayHeight,
                leftLimit,
                rightLimit,
                topLimit,
                botLimit;

    private int moveSelected;

    private static final float MARGIN_LIMIT = .3f;

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
     * @param displayWidth display width size
     * @param displayHeight display height size
     */
    public KeyBoardEmulator(int displayWidth, int displayHeight){
        this.displayHeight = displayHeight;
        this.displayWidth = displayWidth;
        this.calculateLimits();
        this.moveSelected = 0;
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
        this.leftLimit = (int) (this.displayWidth * KeyBoardEmulator.MARGIN_LIMIT);
        this.rightLimit = (int) (this.displayWidth * this.oppositePercentSide());
        this.topLimit = (int) (this.displayHeight * KeyBoardEmulator.MARGIN_LIMIT);
        this.botLimit = (int) (this.displayHeight * this.oppositePercentSide());
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
            this.moveSelected = Snake.LEFT;
        if (this.touchOnRightSite(position))
            this.moveSelected = Snake.RIGHT;
    }

    /**
     * Method to check if Y position is touching simulated button
     * @param position Y axis position to be evaluated
     */
    private void checkAxisY(int position){
        if (this.touchOnTopSite(position))
            this.moveSelected = Snake.UP;
        if (this.touchOnBotSite(position))
            this.moveSelected = Snake.DOWN;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnTopSite(int position){
        return 0 <= position && position <= this.topLimit;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position Y position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnBotSite(int position){
        return this.botLimit <= position && position <= this.displayHeight;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnLeftSite(int position){
        return 0 <= position && position <= this.leftLimit;
    }

    /**
     * Metho to check if the position given is between limits
     * @param position X position to be evaluated
     * @return true if and only if the position is in the limits
     */
    private boolean touchOnRightSite(int position){
        return this.rightLimit <= position && position <= this.displayWidth;
    }

    /**
     * Getter for the attribute moveSelected
     * @return snake move code
     */
    public int getMoveSelected(){
        return this.moveSelected;
    }
}
