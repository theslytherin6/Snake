package com.mygdx.game;

public enum Directions {
    UP(1),
    DOWN(-1),
    LEFT(2),
    RIGHT(-2);

    public final int value;

    Directions(int value){
        this.value = value;
    }
}
