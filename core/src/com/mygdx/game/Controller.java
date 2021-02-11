package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controller {
    private static Controller controller;
    private final int INIT_SNAKE_RELATIVE_ROW = 10;
    private final int INIT_SNAKE_RELATIVE_COL = 10;
    private final int INIT_SNAKE_DIRECTION = Snake.RIGHT;
    private Snake snake;


    private Controller(float cellWith) {
        this.snake = new Snake(this.INIT_SNAKE_RELATIVE_COL, this.INIT_SNAKE_RELATIVE_ROW, this.INIT_SNAKE_DIRECTION, cellWith);
    }

    public static Controller create(float cellWith) {
        if (Controller.controller == null)
            Controller.controller = new Controller(cellWith);
        return Controller.controller;
    }

    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        this.snake.render(spriteBatch);
    }

    private void moveSnake() {
        this.snake.move();
    }

    public void dispose() {
        this.snake.dispose();
    }
}