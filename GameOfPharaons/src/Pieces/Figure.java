package Pieces;

import java.awt.*;

public class Figure {
    protected int row;
    protected int col;
    protected int id;
    protected String string;
    protected int ap;
    protected int dp;
    protected int mp;
    protected int s;
    protected int maxDP;
    public static final int TILE_SIZE = 50;

    public Figure(int row, int col, int id, int ap, int dp, int mp, int s,int maxDP) {
        this.row = row;
        this.col = col;
        this.id = id;
        this.ap = ap;
        this.dp = dp;
        this.mp = mp;
        this.s = s;
        this.maxDP = maxDP;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getId() {
        return id;
    }

    public int getAP() {
        return ap;
    }

    public int getDP() {
        return dp;
    }

    public int getMP() {
        return mp;
    }

    public String getString() {
        return string;
    }

    public int getSpeed() {
        return s;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public int getMaxDP() {
        return maxDP;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(Graphics g) {

        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;

        g.setColor(Color.RED);
        g.drawString("E", tileX + 20, tileY + 28);

    }



    public int isMoveValid(int row, int col) {
        return 1;
    }
}

