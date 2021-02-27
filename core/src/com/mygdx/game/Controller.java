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

    private static final String GRASS_IMG = "grass.png";
    private static final String START_IMG = "start.png";
    private static final String END_IMG = "end.png";
    private static final String SOUND_MOVEMENT_PATH = "Sounds/movement.mp3";
    private static final String SOUND_GROW_PATH = "Sounds/grow.mp3";
    private static final String SOUND_BACKGROUND_PATH = "Sounds/background.mp3";
    private static final Directions INIT_SNAKE_DIRECTION = Directions.RIGHT;
    private static final int FRAMES_TO_SNAKE_MOVES = 60;
    private static final int FRAMES_TO_SNAKE_GROWS = Controller.FRAMES_TO_SNAKE_MOVES * 4;

    private static Controller controller;

    private final Texture GAME_BACKGROUND = new Texture(Controller.GRASS_IMG);
    private final Texture START_BACKGROUND = new Texture(Controller.START_IMG);
    private final Texture END_BACKGROUND = new Texture(Controller.END_IMG);
    private final Sound MOVEMENT_SOUND = Gdx.audio.newSound(Gdx.files.internal(Controller.SOUND_MOVEMENT_PATH));
    private final Sound GROW_SOUND = Gdx.audio.newSound(Gdx.files.internal(Controller.SOUND_GROW_PATH));
    private final Music BACKGROUND_SOUND = Gdx.audio.newMusic(Gdx.files.internal(Controller.SOUND_BACKGROUND_PATH));

    private int displayWidth;
    private int displayHeight;
    private int xOffset;
    private int yOffset;

    private int framesCounter;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private SpriteBatch spriteBatch;


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
        this.draw(this.START_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, displayHeight);

        if (Gdx.input.justTouched()) {
            this.controllerVG = gameStates.PLAYING;
            this.BACKGROUND_SOUND.play();
            this.BACKGROUND_SOUND.setVolume(10);
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
        this.draw(this.GAME_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, this.displayHeight);
        this.snake.render(spriteBatch);
    }

    /**
     * Method responsible of the touchs on the screen
     */
    private void touchHandler() {
        if (Gdx.input.justTouched()) {
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
            this.BACKGROUND_SOUND.stop();
        }
        else
            this.snakeMove();
    }

    private void snakeMove(){
        this.framesCounter++;
        if (this.shouldGrow())
            this.growSnake();
        else if (this.shouldMove())
            this.moveSnake();
    }

    private boolean shouldGrow(){
        return this.framesCounter == Controller.FRAMES_TO_SNAKE_GROWS;
    }

    private void growSnake(){
        this.snake.grow();
        this.framesCounter = 0;
        this.GROW_SOUND.play(.6f);
    }

    private boolean shouldMove(){
        return this.framesCounter % Controller.FRAMES_TO_SNAKE_MOVES == 0;
    }

    /**
     * Method to move the pieces of the Snake
     */
    private void moveSnake() {
        this.snake.move();
        this.MOVEMENT_SOUND.play();
    }

    private void gameFinished(){
        this.draw(this.END_BACKGROUND, this.xOffset, this.yOffset, this.displayWidth, this.displayHeight);

        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched)
            this.controllerVG = gameStates.GAME_START;
    }

    /**
     * Method to dispose the piece list from snake
     */
    public void dispose() {
        this.snake.dispose();
        this.disposeMusic();
    }

    private void disposeMusic(){
        this.GROW_SOUND.dispose();
        this.MOVEMENT_SOUND.dispose();
        this.BACKGROUND_SOUND.dispose();
    }
}