package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
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

    private LinkedList<Piece> PieceList;
    private int lastMovement;
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 2;
    public static final int RIGHT = -2;

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

    public Snake() {
        PieceList = new LinkedList();
    }

    public void grow() {
        Piece clonedFirstPiece = this.PieceList.getFirst().clone();
        this.movePiece(clonedFirstPiece);
        this.PieceList.addFirst(clonedFirstPiece);
    }

    /**
     * Method to change the direction of the Snake
     * @param movement one of the following directions (UP,DOWN,LEFT,RIGHT)
     */
    public void changeMovement(int movement) {

        if(lastMovement + movement == 0);
        this.lastMovement = movement;

    }

    /**
     * Method to move the Snake
     * todo trello: metodo mover clase Snake
     */
    public void move() {
        this.PieceList.removeLast();
        this.grow();
    }

    /**
     * Method to move a specific piece
     * @param pieceToMove piece that it's going to move
     */
    private void movePiece(Piece pieceToMove){
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
                throw new IllegalArgumentException("BUM!");  // Quitar la excepción y indicar que debe repetirse el último movimiento si no se elige otro distinto
        }
    }

    /**
     * Method to check if a piece can move
     * @return true if the piece can move
     */

    public boolean canMove() {

        //creamos una pieza clonada de la primera pieza y la movemos en la ultima direccion y recorremos el linked list para
        //saber si colisionaria con alguna de las piezas de snake y controlar la pieza que se va a borrar

        Piece clonedFirstPiece = this.PieceList.getFirst().clone();
        this.movePiece(clonedFirstPiece);
        for (Piece c: PieceList) {
            if (c.isColliding(clonedFirstPiece)) return false;
        }
        return true;
    }

    /**
     * Method to dispose the piece list from snake
     */
    public void dispose(){

        for (Piece piece : PieceList){
            piece.dispose();
        }
    }

}
