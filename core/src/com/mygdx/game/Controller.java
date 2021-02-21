package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static Controller controller;
    private static final String GRASS = "grass.png";
    private Texture background;
    private final int INIT_SNAKE_RELATIVE_ROW = 10;
    private final int INIT_SNAKE_RELATIVE_COL = 10;
    private final int INIT_SNAKE_DIRECTION = Snake.RIGHT;
    private final int FRAMES_TO_SNAKE_MOVES = 60;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private final float displayWidth;
    private final float displayHeight;
    private final float xOffset;
    private final float yOffset;
    private int contador;

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
     * Builder of controller
     *
     * @param cellWidth     Width of the cells where Snake would move
     * @param displayWidth  Width of the display
     * @param displayHeight Height of the display
     */
    private Controller(float cellWidth, float newXOffset, float newYOffset, float newDisplayWidth, float newDisplayHeight) {
        this.snake = new Snake(this.INIT_SNAKE_RELATIVE_COL, this.INIT_SNAKE_RELATIVE_ROW, this.INIT_SNAKE_DIRECTION, cellWidth);
//        this.keyBoardEmulator = new KeyBoardEmulator(newDisplayWidth, newDisplayHeight);
//        this.keyBoardEmulator = new KeyBoardEmulator(newInitAbsoluteX, newIitAbsoluteY, newDisplayWidth, newDisplayHeight);
        this.displayWidth = newDisplayWidth;
        this.displayHeight = newDisplayHeight;
        this.xOffset = newXOffset;
        this.yOffset = newYOffset;
        this.contador = 0;
        this.background = new Texture(Controller.GRASS);
    }

    /**
     * Method to create a Controller
     *
     * @param cellWidth     Width of the cells where Snake would move
     * @param displayWidth  Width of the display
     * @param displayHeight Height of the display
     * @return A controller if the there is not other controller
     */
    public static Controller create(float cellWidth, float newXOffset, float newYOffset, float newDisplayWidth, float newDisplayHeight) {
        if (Controller.controller == null)
            Controller.controller = new Controller(cellWidth, newXOffset, newYOffset, newDisplayWidth, newDisplayHeight);
        return Controller.controller;
    }

    /**
     * Method to manage the objects associated to the controller
     * @param spriteBatch Platform to draw textures
     */
    public void loop(SpriteBatch spriteBatch) {
        this.render(spriteBatch);
        this.touchHandler();
        this.snakeHandler();
    }

    /**
     * Method to draw the sprite texture and the background
     * @param spriteBatch Platform to draw textures
     */
    private void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(this.background, this.xOffset, this.yOffset, this.displayWidth, this.displayHeight);
//        spriteBatch.draw(this.background, 1,1,30,30);
        spriteBatch.end();
        this.snake.render(spriteBatch);
    }

    /**
     * Method responsible of the touchs on the screen
     */
    private void touchHandler() {
        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched) {
            this.keyBoardEmulator.emulate(Gdx.input.getX(), Gdx.input.getY());
            this.snake.changeMovement(this.keyBoardEmulator.getMoveSelected());
        }
    }

    /**
     * Method responsible of the movement of the Snake
     */
    private void snakeHandler() {
        this.contador++;
        if (this.contador == this.FRAMES_TO_SNAKE_MOVES) {
            this.moveSnake();
            this.contador = 0;
        }
    }

    /**
     * Method to move the pieces of the Snake
     */
    private void moveSnake() {
        this.snake.move();
    }

    /**
     * Method to dispose the piece list from snake
     */
    public void dispose() {
        this.snake.dispose();
    }
}