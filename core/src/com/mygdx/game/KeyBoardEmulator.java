package com.mygdx.game;

public class KeyBoardEmulator {

    private int displayWidth,
                displayHeight,
                leftLimit,
                rightLimit,
                topLimit,
                botLimit;

    private int moveSelected;

    private final float MARGIN_LIMIT = .3f;

    public KeyBoardEmulator(int displayWidth, int displayHeight){
        this.displayHeight = displayHeight;
        this.displayWidth = displayWidth;
        this.calculateLimits();
        System.out.println("LIMITS");
        System.out.println("T:" + this.topLimit);
        System.out.println("D:" + this.botLimit);
        System.out.println("L:" + this.leftLimit);
        System.out.println("R:" + this.rightLimit);
        this.moveSelected = 0;
    }

    private void calculateLimits(){
        this.leftLimit = (int) (this.displayWidth * this.MARGIN_LIMIT);
        this.rightLimit = (int) (this.displayWidth * (1 - this.MARGIN_LIMIT));
        this.topLimit = (int) (this.displayHeight * this.MARGIN_LIMIT);
        this.botLimit = (int) (this.displayHeight * (1 - this.MARGIN_LIMIT));
    }

    public void emulate(int positionX, int positionY){
        this.checkAxisX(positionX);
        this.checkAxisY(positionY);
    }

    private void checkAxisX(int position){
        if (this.touchOnLeftSite(position))
            this.moveSelected = Snake.LEFT;
        if (this.touchOnRightSite(position))
            this.moveSelected = Snake.RIGHT;
    }

    private void checkAxisY(int position){
        if (this.touchOnTopSite(position))
            this.moveSelected = Snake.UP;
        if (this.touchOnBotSite(position))
            this.moveSelected = Snake.DOWN;
    }

    private boolean touchOnTopSite(int position){
        return 0 <= position && position <= this.topLimit;
    }

    private boolean touchOnBotSite(int position){
        return this.botLimit <= position && position <= this.displayHeight;
    }

    private boolean touchOnLeftSite(int position){
        return 0 <= position && position <= this.leftLimit;
    }
    private boolean touchOnRightSite(int position){
        return this.rightLimit <= position && position <= this.displayWidth;
    }

    public int getMoveSelected(){
        return this.moveSelected;
    }
}
