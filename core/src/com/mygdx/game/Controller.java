package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;

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

    private static final String GRASS = "grass.png";
    private static final String START = "start.png";
    private static final String END = "end.png";
    private static final Texture GAME_BACKGROUND = new Texture(Controller.GRASS);
    private static final Texture START_BACKGROUND = new Texture(Controller.START);
    private static final Texture END_BACKGROUND = new Texture(Controller.END);
    private static final Directions INIT_SNAKE_DIRECTION = Directions.RIGHT;
    private static final int FRAMES_TO_SNAKE_MOVES = 60;
    private static final int FRAMES_TO_SNAKE_GROWS = 240;

    private static Controller controller;

    private final float DISPLAY_WIDTH;
    private final float DISPLAY_HEIGHT;
    private final float X_OFFSET;
    private final float Y_OFFSET;

    private int framesCounter;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private SpriteBatch spriteBatch;
    private Sound movementSound;

    public enum gameStates{
        GAME_START,
        PLAYING,
        GAME_END
    }
    private gameStates controllerVG;
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
     *
     * @param cellWidth         Width of the cells where Snake would move
     * @param newXOffset
     * @param newYOffset
     * @param newDisplayWidth   Width of the display
     * @param newDisplayHeight  Height of the display
     */
    private Controller(float cellWidth, float newXOffset, float newYOffset, float newDisplayWidth, float newDisplayHeight, SpriteBatch spriteBatch) {
        System.out.println("Debug Information -------------------");
        System.out.printf("width: %s\nX Offset: %s\nY Offset: %s\nDisplay Width: %s\n" +
                "Display Height: %s\n", cellWidth, newXOffset, newYOffset, newDisplayWidth, newDisplayHeight);
        this.snake = new Snake(newXOffset, newYOffset, newDisplayWidth, newDisplayHeight, Controller.INIT_SNAKE_DIRECTION, cellWidth);
        this.keyBoardEmulator = new KeyBoardEmulator(newXOffset, newYOffset, newDisplayWidth, newDisplayHeight);
        this.DISPLAY_WIDTH = newDisplayWidth;
        this.DISPLAY_HEIGHT = newDisplayHeight;
        this.X_OFFSET = newXOffset;
        this.Y_OFFSET = newYOffset;
        this.framesCounter = 0;
        this.controllerVG = gameStates.GAME_START;
        this.spriteBatch = spriteBatch;
        this.movementSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/movement.mp3"));
    }

    /**
     *
     * @param cellWidth         Width of the cells where Snake would move
     * @param newXOffset
     * @param newYOffset
     * @param newDisplayWidth   Height of the display
     * @param newDisplayHeight  Width of the display
     * @return A controller if the there is not other controller
     */
    public static Controller create(float cellWidth, float newXOffset, float newYOffset, float newDisplayWidth, float newDisplayHeight, SpriteBatch spriteBatch) {
        if (Controller.controller == null)
            Controller.controller = new Controller(cellWidth, newXOffset, newYOffset, newDisplayWidth, newDisplayHeight, spriteBatch);
        return Controller.controller;
    }

    /**
     * Method to manage the objects associated to the controller
     * @param spriteBatch Platform to draw textures
     */
    public void loop() {
        switch (controllerVG){
            case GAME_START:
                this.startScreen();
                break;
            case PLAYING:
                this.gameStarted();
                break;
            case GAME_END:
                this.gameFinished();
        }
    }

    private void startScreen(){
        this.spriteBatch.begin();
        this.spriteBatch.draw(Controller.START_BACKGROUND, this.X_OFFSET, this.Y_OFFSET, this.DISPLAY_WIDTH, this.DISPLAY_HEIGHT);
        this.spriteBatch.end();

        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched)
            this.controllerVG = gameStates.PLAYING;
    }

    private void gameStarted(){
        this.renderPlaying();
        this.touchHandler();
        this.snakeHandler();
    }

    /**
     * Method to draw the sprite texture and the background
     * @param spriteBatch Platform to draw textures
     */
    private void renderPlaying() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.spriteBatch.begin();
        this.spriteBatch.draw(Controller.GAME_BACKGROUND, this.X_OFFSET, this.Y_OFFSET, this.DISPLAY_WIDTH, this.DISPLAY_HEIGHT);
        this.spriteBatch.end();
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
        if (this.snake.isDead())
            this.controllerVG = gameStates.GAME_END;
        this.framesCounter++;
        if (this.framesCounter == Controller.FRAMES_TO_SNAKE_GROWS) {
            this.growSnake();
            this.framesCounter = 0;
            movementSound.play();
        } else if (this.framesCounter % Controller.FRAMES_TO_SNAKE_MOVES == 0) {
            this.moveSnake();
            movementSound.play();
        }
    }

    private void gameFinished(){
        this.spriteBatch.begin();
        this.spriteBatch.draw(Controller.END_BACKGROUND, this.X_OFFSET, this.Y_OFFSET, this.DISPLAY_WIDTH, this.DISPLAY_HEIGHT);
        this.spriteBatch.end();

        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched) this.controllerVG = gameStates.GAME_START;
    }

    /**
     * Method to move the pieces of the Snake
     */
    private void moveSnake() {
        this.snake.move();
    }

    private void growSnake() {
        this.snake.grow();
    }

    /**
     * Method to dispose the piece list from snake
     */
    public void dispose() {
        this.snake.dispose();
        movementSound.dispose();
    }
}