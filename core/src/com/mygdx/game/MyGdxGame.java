package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;


    private Controller mainController;
    private float smallerDimension;
    private float screenWidth;
    private float screenHeight;
    private float mainXOffset;
    private float gameXOffset;
    private float mainYOffset;
    private float gameYOffset;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.getScreenProperties();

        int finalOffsetX = (int) (this.mainXOffset + this.gameYOffset);
        int finalOffsetY = (int) (this.mainYOffset + this.gameYOffset);
        int finalDisplayX = (int) (20*(Math.floor((this.smallerDimension - 2 * this.gameXOffset)/20)));
        int finalDisplayY = finalDisplayX;
        int finalCellDimentions = finalDisplayX/20;

        mainController = Controller.create(finalCellDimentions,
                finalOffsetX,
                finalOffsetY,
                finalDisplayX,
                finalDisplayY,
                this.batch);
    }

    private void getScreenProperties(){
        this.getSmallerDisplaySize();
        this.calculateOffsets();
    }

    private void getSmallerDisplaySize() {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.smallerDimension =  Math.min(this.screenWidth, this.screenHeight);
    }

    private void calculateOffsets(){
        this.calculateMainOffsets();
        this.calculateGameOffsets();
    }

    private void calculateMainOffsets(){
        this.mainXOffset = (this.screenWidth - this.smallerDimension) / 2;
        this.mainYOffset = (this.screenHeight - this.smallerDimension) / 2;
    }

    private void calculateGameOffsets(){
        this.gameXOffset = this.smallerDimension * .05f;
        this.gameYOffset = this.smallerDimension * .05f;
    }


    private float getCellDimesions() {
        return (this.smallerDimension - this.gameXOffset) / 20;
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