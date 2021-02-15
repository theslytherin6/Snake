package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class Snake {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// STATE
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private LinkedList<Piece> pieceList;
    private int lastMovement;
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 2;
    public static final int RIGHT = -2;
    private final String IMAGE = "Snake.png";

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////
////// BEHAVIOR//CONDUCT
//////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // BUILDER

    /**
     * @param initRelativeCol initial relative X position
     * @param initRelativeRow initial relative Y position
     * @param initDirection initial snake direction
     * @param width width for every Piece
     */
    public Snake(int initRelativeCol, int initRelativeRow, int initDirection, float width) {
        this.pieceList = new LinkedList<>();
        Piece piece = new Piece(initRelativeCol, initRelativeRow, width, this.IMAGE);
        this.pieceList.add(piece);
        this.lastMovement = initDirection;
    }

    /**
     * Method to move the Snake
     */
    public void move() {
        this.grow();
        this.pieceList.removeLast();
    }

    /**
     * Method to make grow the Snake
     */
    public void grow() {
        Piece clonedFirstPiece = this.pieceList.getFirst().clone();
        this.moveSpecificPiece(clonedFirstPiece);
        this.pieceList.addFirst(clonedFirstPiece);
    }

    /**
     * Method to move a specific piece
     * @param pieceToMove piece that it's going to move
     */
    private void moveSpecificPiece(Piece pieceToMove) {
        switch (lastMovement) {
            case Snake.UP:
                pieceToMove.decrementRow();
                break;
            case Snake.DOWN:
                pieceToMove.incrementRow();
                break;
            case Snake.LEFT:
                pieceToMove.decrementCol();
                break;
            case Snake.RIGHT:
                pieceToMove.incrementCol();
                break;
            default:
                throw new IllegalArgumentException("BUM!");
        }
    }

    /**
     * Method to change the direction of the Snake
     * @param movement one of the following directions (UP,DOWN,LEFT,RIGHT)
     */
    public void changeMovement(int movement) {
        if (this.isMovementValid(movement))
            this.lastMovement = movement;
    }

    /**
     * Method to check if the movement is opposite or not
     * @param movement
     * @return true if the movement is not opposite
     */
    private boolean isMovementValid(int movement) {
        return this.lastMovement + movement != 0;
    }

    /**
     * Method to render the piece list from snake
     * @param spriteBatch
     */
    public void render(SpriteBatch spriteBatch) {
        for (Piece piece : this.pieceList) {
            piece.render(spriteBatch);
        }
    }

    /**
     * Method to dispose the piece list from snake
     */
    public void dispose() {

        for (Piece piece : this.pieceList) {
            piece.dispose();
        }
    }

    /**
     * Method to check if a piece can move
     *
     * @return true if the piece can move
     */
    /*public boolean canMove() {

        //creamos una pieza clonada de la primera pieza y la movemos en la ultima direccion y recorremos el linked list para
        //saber si colisionaria con alguna de las piezas de snake y controlar la pieza que se va a borrar

        Piece clonedFirstPiece = this.pieceList.getFirst().clone();
        this.movePiece(clonedFirstPiece);
        for (Piece c : pieceList) {
            if (c.isColliding(clonedFirstPiece)) return false;
        }
        return true;
    }

     */
}