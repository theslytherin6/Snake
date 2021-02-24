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
    private Directions lastMovement;
    private final String IMAGE = "Snake.png";
    private final float GAME_DISPLAY_INITIAL_X,
                        GAME_DISPLAY_INITIAL_Y,
                        GAME_DISPLAY_FINAL_X,
                        GAME_DISPLAY_FINAL_Y;
    private final int INIT_RELATIVE_COL=10,
                      INIT_RELATIVE_ROW=10;

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
     * Builder of snake
     *
     * @param initRelativeCol  initial relative X position
     * @param initRelativeRow  initial relative Y position
     * @param initDirection    initial snake direction
     * @param width            width for every Piece
     */
    public Snake(float newGameDisplayInitialX,float newGameDisplayInitialY,float newGameDisplayFinalX,float newGameDisplayFinalY ,Directions initDirection, float width) {
        this.pieceList = new LinkedList<>();
        Piece piece = new Piece(width*this.INIT_RELATIVE_COL+newGameDisplayInitialX, width*this.INIT_RELATIVE_ROW+newGameDisplayInitialY, width, this.IMAGE);
        this.pieceList.add(piece);
        this.lastMovement = initDirection;
        this.GAME_DISPLAY_FINAL_X = newGameDisplayFinalX;
        this.GAME_DISPLAY_FINAL_Y = newGameDisplayFinalY;
        this.GAME_DISPLAY_INITIAL_X = newGameDisplayInitialX;
        this.GAME_DISPLAY_INITIAL_Y = newGameDisplayInitialY;

    }

    /**
     * Method to move the Snake
     */
    public void move() {
        System.out.printf("X: %s, Y:%s\n", this.pieceList.getFirst().getAbsoluteCol(), this.pieceList.getFirst().getAbsoluteRow());
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
            case UP:
                pieceToMove.incrementRow();
                break;
            case DOWN:
                pieceToMove.decrementRow();
                break;
            case LEFT:
                pieceToMove.decrementCol();
                break;
            case RIGHT:
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
    public void changeMovement(Directions movement) {
        if (this.isMovementValid(movement))
            this.lastMovement = movement;
    }

    /**
     * Method to check if the movement is opposite or not
     * @param movement
     * @return true if the movement is not opposite
     */
    private boolean isMovementValid(Directions movement) {
        return movement != null && this.lastMovement.value + movement.value != 0;
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
    public boolean isDead() {

        Piece head = this.pieceList.getFirst();

        for (int i=5;i<pieceList.size();i++) {
            if (head.isColliding(pieceList.get(i)))
                return true;
        }

        return !(this.GAME_DISPLAY_INITIAL_X < head.getAbsoluteCol() &&
               head.getAbsoluteCol() < this.GAME_DISPLAY_FINAL_X + this.GAME_DISPLAY_INITIAL_X &&
               this.GAME_DISPLAY_INITIAL_Y < head.getAbsoluteRow() &&
               head.getAbsoluteRow() < this.GAME_DISPLAY_FINAL_Y + this.GAME_DISPLAY_INITIAL_Y);
    }


}
