package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller {
    private static Controller controller;
    private final int INIT_SNAKE_RELATIVE_ROW = 10;
    private final int INIT_SNAKE_RELATIVE_COL = 10;
    private final int INIT_SNAKE_DIRECTION = Snake.RIGHT;
    private final int FRAMES_TO_SNAKE_MOVES = 60;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private int displayWidth;
    private int displayHeight;
    private int contador;

    private Controller(int cellWith, int displayWidth, int displayHeight) {
        this.snake = new Snake(this.INIT_SNAKE_RELATIVE_COL, this.INIT_SNAKE_RELATIVE_ROW, this.INIT_SNAKE_DIRECTION, cellWith);
        this.keyBoardEmulator = new KeyBoardEmulator(displayWidth, displayHeight);
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.contador = 0;
    }

    public static Controller create(int cellWith, int displayWidth, int displayHeight) {
        if (Controller.controller == null)
            Controller.controller = new Controller(cellWith, displayWidth, displayHeight);
        return Controller.controller;
    }

    public void loop(SpriteBatch spriteBatch) {
        this.render(spriteBatch);
        this.touchHandler();
        this.snakeHandler();
    }

    private void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.snake.render(spriteBatch);
    }

    private void touchHandler(){
        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched){
            keyBoardEmulator.emulate(Gdx.input.getX(), Gdx.input.getY());
            snake.changeMovement(keyBoardEmulator.getMoveSelected());
        }
    }

    private void snakeHandler(){
        contador++;
        if (contador == FRAMES_TO_SNAKE_MOVES){
            this.moveSnake();
            contador = 0;
        }
    }

    private void moveSnake() {
        this.snake.move();
    }

    public void dispose() {
        this.snake.dispose();
    }
}