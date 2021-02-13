package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;


    private Controller mainController;
    private int smallerDimension;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        this.getSmallerDisplaySize();
        mainController = Controller.create(getCellDimesions(), this.smallerDimension, this.smallerDimension);
    }

    private void getSmallerDisplaySize() {
        int ScreenWidth = Gdx.graphics.getWidth(); // Get the Width of the screen size in pixels
        int ScreenHeight = Gdx.graphics.getHeight(); // Get the Height of the screen size in pixels
        // The smaller dimension between the Width and the Height of the screen
        this.smallerDimension = Math.min(ScreenWidth, ScreenHeight);
    }

    private int getCellDimesions() {
        return (int) (this.smallerDimension * 0.9 / 20);
    }

    @Override
    public void render() {
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(img, 0, 0);
        //batch.end();
        mainController.render(batch);
    }

    @Override
    public void dispose() {
        //batch.dispose();
        //img.dispose();
        mainController.dispose();
    }
}