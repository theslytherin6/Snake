package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    private static final int FRAMES_TO_SNAKE_GROWS = Controller.FRAMES_TO_SNAKE_MOVES * 4;

    private static Controller controller;

    private int displayWidth;
    private int displayHeight;
    private int xOffset;
    private int yOffset;

    private int framesCounter;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private SpriteBatch spriteBatch;
    private Sound movementSound;
    private Sound growSound;
    private Music backgroundMusic;

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
    private Controller(int cellWidth, int newXOffset, int newYOffset, int newDisplayWidth, int newDisplayHeight, SpriteBatch spriteBatch) {
        this.snake = new Snake(newXOffset, newYOffset, newDisplayWidth, newDisplayHeight, Controller.INIT_SNAKE_DIRECTION, cellWidth);
        this.keyBoardEmulator = new KeyBoardEmulator(newXOffset, newYOffset, newDisplayWidth, newDisplayHeight);
        this.setDisplaySetting(newXOffset, newYOffset, newDisplayWidth, newDisplayHeight);
        this.framesCounter = 0;
        this.controllerVG = gameStates.GAME_START;
        this.spriteBatch = spriteBatch;
        this.movementSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/movement.mp3"));
        this.growSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/grow.mp3"));
        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/backgroundMusic.mp3"));
    }

    private void setDisplaySetting(int newXOffset, int newYOffset, int newDisplayWidth, int newDisplayHeight){
        this.displayWidth = newDisplayWidth;
        this.displayHeight = newDisplayHeight;
        this.xOffset = newXOffset;
        this.yOffset = newYOffset;
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
    public static Controller create(int cellWidth, int newXOffset, int newYOffset, int newDisplayWidth, int newDisplayHeight, SpriteBatch spriteBatch) {
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
        this.draw(Controller.START_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, displayHeight);

        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched) {
            this.controllerVG = gameStates.PLAYING;
            this.backgroundMusic.play();
        }
    }

    private void draw(Texture texture2Draw, int initXPosition, int initYPosition, int width, int height){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.spriteBatch.begin();
        this.spriteBatch.draw(texture2Draw, initXPosition, initYPosition, width, height);
        this.spriteBatch.end();
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
        this.draw(Controller.GAME_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, this.displayHeight);
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
        if (this.snake.isDead()) {
            this.controllerVG = gameStates.GAME_END;
            this.backgroundMusic.stop();
        }
        else
            this.snakeMove();
    }

    private void snakeMove(){
        this.framesCounter++;
        if (this.framesCounter == Controller.FRAMES_TO_SNAKE_GROWS) {
            this.growSnake();
            this.framesCounter = 0;
            this.growSound.play();
        } else if (this.framesCounter % Controller.FRAMES_TO_SNAKE_MOVES == 0) {
            this.moveSnake();
            this.movementSound.play();
        }
    }

    private void gameFinished(){
        this.draw(Controller.END_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, this.displayHeight);

        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched)
            this.controllerVG = gameStates.GAME_START;
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
        this.movementSound.dispose();
        this.growSound.dispose();
        this.backgroundMusic.dispose();
    }
}