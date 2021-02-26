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

    private void calculateDisplayCharacteristics(){
        this.finalDisplaySize = this.getRoundedUsableDisplaySize();
        this.calculateOffsets();
    }

    private int getRoundedUsableDisplaySize(){
        int lessDisplaySize = this.getSmallerDisplaySize();
        int usableDisplaySize = this.getUsableDisplaySize(lessDisplaySize);
        return this.roundUsableDisplaySize(usableDisplaySize);
    }

    private int getSmallerDisplaySize() {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        return Math.min(this.screenWidth, this.screenHeight);
    }

    private int getUsableDisplaySize(int displaySize){
        return (int) (displaySize * MyGdxGame.USABLE_PERCENT);
    }

    private int roundUsableDisplaySize(int displaySize){
        return (int) (MyGdxGame.PIECES_PER_AXIS*(Math.floor((float)displaySize/MyGdxGame.PIECES_PER_AXIS)));
    }

    private void calculateOffsets(){
        this.calculateMainOffsets();
    }

    private void calculateMainOffsets(){
        this.finalLeftOffsetX = (this.screenWidth - this.finalDisplaySize) / 2;
        this.finalDownOffsetY = (this.screenHeight - this.finalDisplaySize) / 2;
    }

    private int getCellDimesions() {
        return this.finalDisplaySize / MyGdxGame.PIECES_PER_AXIS;
    }

    @Override
    public void render() {
        this.mainController.loop();
    }

    @Override
    public void dispose() {
        mainController.dispose();
    }
}