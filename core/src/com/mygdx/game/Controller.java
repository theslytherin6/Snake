package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller {
    private static Controller controller;
    private final int INIT_SNAKE_RELATIVE_ROW = 10;
    private final int INIT_SNAKE_RELATIVE_COL = 10;
    private final int INIT_SNAKE_DIRECTION = Snake.RIGHT;
    private Snake snake;
    private KeyBoardEmulator keyBoardEmulator;
    private int displayWidth;
    private int displayHeight;


    private Controller(int cellWith, int displayWidth, int displayHeight) {
        this.snake = new Snake(this.INIT_SNAKE_RELATIVE_COL, this.INIT_SNAKE_RELATIVE_ROW, this.INIT_SNAKE_DIRECTION, cellWith);
        this.keyBoardEmulator = new KeyBoardEmulator(displayWidth, displayHeight);
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
    }

    public static Controller create(int cellWith, int displayWidth, int displayHeight) {
        if (Controller.controller == null)
            Controller.controller = new Controller(cellWith, displayWidth, displayHeight);
        return Controller.controller;
    }

    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // todo estas lineas comentadas tienen que descomentarse para que todo funcione
        this.snake.render(spriteBatch);
        boolean screenTouched = Gdx.input.justTouched();
        if (screenTouched){
            keyBoardEmulator.emulate(Gdx.input.getX(), Gdx.input.getY());
            System.out.println("X: " + Gdx.input.getX() + "\nY: " + Gdx.input.getY());
            System.out.println("U:" + keyBoardEmulator.isKeyUpPressed());
            System.out.println("D:" + keyBoardEmulator.isKeyDownPressed());
            System.out.println("L:" + keyBoardEmulator.isKeyLeftPressed());
            System.out.println("R:" + keyBoardEmulator.isKeyRightPressed());
            System.out.println("-------------------------------------");
        }
    }

    private void moveSnake() {
        this.snake.move();
    }

    public void dispose() {
        this.snake.dispose();
    }
}