package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final static float USABLE_PERCENT = .9f;
    private final static int PIECES_PER_AXIS = 20;

    private Controller mainController;
    private int finalDisplaySize;
    private int screenWidth;
    private int screenHeight;
    private int finalLeftOffsetX;
    private int finalDownOffsetY;

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
     * Method to initialize the SpriteBatch, calculate the characteristics of the Display and create the Controller
     */
    @Override
    public void create() {
        SpriteBatch batch = new SpriteBatch();
        this.calculateDisplayCharacteristics();

        mainController = Controller.create(getCellDimesions(),
                this.finalLeftOffsetX,
                this.finalDownOffsetY,
                this.finalDisplaySize,
                this.finalDisplaySize,
                batch);
    }

    /**
     * Method to calculate the Display Characteristics
     */
    private void calculateDisplayCharacteristics(){
        this.finalDisplaySize = this.getRoundedUsableDisplaySize();
        this.calculateOffsets();
    }

    /**
     * Method to get the rounded size of the usable display size
     * @return The usable display size rounded
     */
    private int getRoundedUsableDisplaySize(){
        int lessDisplaySize = this.getSmallerDisplaySize();
        int usableDisplaySize = this.getUsableDisplaySize(lessDisplaySize);
        return this.roundUsableDisplaySize(usableDisplaySize);
    }

    /**
     * Method to get the Smaller Size of the Display
     * @return The smaller size of the display
     */
    private int getSmallerDisplaySize() {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        return Math.min(this.screenWidth, this.screenHeight);
    }

    /**
     * Method to get the Usable Size of the Display
     * @param displaySize The size of the display
     * @return the usable size of the display
     */
    private int getUsableDisplaySize(int displaySize){
        return (int) (displaySize * MyGdxGame.USABLE_PERCENT);
    }

    /**
     * Method to round the usable size of the display
     * @param displaySize The size of the display
     * @return The usable size of the display rounded
     */
    private int roundUsableDisplaySize(int displaySize){
        return (int) (MyGdxGame.PIECES_PER_AXIS*(Math.floor((float)displaySize/MyGdxGame.PIECES_PER_AXIS)));
    }

    /**
     * Method to call another method which calculate the Offsets
     */
    private void calculateOffsets(){
        this.finalLeftOffsetX = (this.screenWidth - this.finalDisplaySize) / 2;
        this.finalDownOffsetY = (this.screenHeight - this.finalDisplaySize) / 2;
    }

    /**
     * Method to calculate the Dimensions of the cells
     * @return The dimensions of the cells in the game
     */
    private int getCellDimesions() {
        return this.finalDisplaySize / MyGdxGame.PIECES_PER_AXIS;
    }

    /**
     * Method to start the loop of the Controller
     */
    @Override
    public void render() {
        this.mainController.loop();
    }

    /**
     * Method to dispose the Controller
     */
    @Override
    public void dispose() {
        mainController.dispose();
    }
}
