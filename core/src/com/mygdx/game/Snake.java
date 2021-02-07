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
    private String lastMovement;
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";

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

    public void grow(Piece piece) {
        PieceList.add(piece);
    }

    /**
     * Method to change the direction of the Snake
     * @param movement one of the following directions (UP,DOWN,LEFT,RIGHT)
     */
    public void changeMovement(String movement) {

        this.lastMovement = movement;

    }

    /**
     * Method to move the Snake
     * todo trello: metodo mover clase Snake
     */
    public void move() {
        this.PieceList.removeLast();
        Piece clonedFirstPiece = this.PieceList.getFirst().clone();
        this.movePiece(clonedFirstPiece);
        this.PieceList.addFirst(clonedFirstPiece);
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
                throw new IllegalArgumentException("BUM!");
        }
    }
    public boolean canMove() {

        // PREGUNTAR AL METODO COLIDER DE LA CLASE PIEZA SI EN LA SIGUIENTE POSICIÓN (A PARTIR DE LA 5ª FICHA) HAY OTRA PIEZA O UN BORDE DEL TABLERO
        // SI NO HAY NADA RETORNAR TRUE
        // SI HAY UNA PIEZA O EL BORDE DEL TABLERO RETORNAR FALSE

        //creamos una pieza clonada de la primera pieza y la movemos en la ultima direccion y recorremos el linked list para
        //saber si colisionaria con alguna de las piezas de snake y controlar la pieza que se va a borrar

        Piece clonedFirstPiece = this.PieceList.getFirst().clone();
        this.movePiece(clonedFirstPiece);
        for (Piece c: PieceList) {
            if (c.isColliding(clonedFirstPiece)) return false;
        }
        return true;
    }

}
