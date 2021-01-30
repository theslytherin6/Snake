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

    public void changeMovement(String movement) {

        this.lastMovement = movement;

    }

    public void move() {
        /**
         * todo El metodo es muy largo, hay que separar en módulos su comportamiento.
         * La responsabilidad de crecer es de Snake [actualmente contando cuantas veces se ha movido para saber cuando crecer(4)]
         * Tenemos que crear un método queeeeeeeeeeee..... devuelva si is the moment or not de crecer y actuar en consecuencia, eliminando o no la ultima pieza.
         * Antes de cada movimiento tenemos que comprobar en el actual metodo canMove si puedo moverme, actualmente el único requisito es no estar outOfRange.
         * Para eso la serpiente debe conocer las características concretas del tablero.
         */
        PieceList.removeLast();
        Piece clonedFirstPiece = PieceList.getFirst().clone();
        switch (lastMovement) {
            case Snake.UP:
                clonedFirstPiece.decrementRow();
                break;
            case Snake.DOWN:
                clonedFirstPiece.incrementRow();
                break;
            case Snake.LEFT:
                clonedFirstPiece.decrementCol();
                break;
            case Snake.RIGHT:
                clonedFirstPiece.incrementCol();
                break;
            default:
                throw new IllegalArgumentException("BUM!");
        }
        PieceList.addFirst(clonedFirstPiece);
    }

    public boolean canMove() {

        // PREGUNTAR AL METODO COLIDER DE LA CLASE PIEZA SI EN LA SIGUIENTE POSICIÓN (A PARTIR DE LA 5ª FICHA) HAY OTRA PIEZA O UN BORDE DEL TABLERO
        // SI NO HAY NADA RETORNAR TRUE
        // SI HAY UNA PIEZA O EL BORDE DEL TABLERO RETORNAR FALSE

        for (int i = PieceList.get(4); i < PieceList.size(); i++) {
            if (PieceList.getFirst().isColliding(i)) ;
        }

        return true;
    }

}
