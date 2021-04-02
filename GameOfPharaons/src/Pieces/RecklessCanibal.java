package Pieces;

import java.awt.*;

public class RecklessCanibal extends Figure {
    public RecklessCanibal(int row, int col, int id, int ap, int dp, int mp, int s, int maxDP) {
        super(row, col, id, ap, dp, mp, s,maxDP);
    }

    public void render(Graphics g) {


        int tileX = this.col * Figure.TILE_SIZE;
        int tileY = this.row * Figure.TILE_SIZE;
        g.setColor(Color.RED);
        g.drawString("RC", tileX + 20, tileY + 28);
    }

    public int isMoveValid(int row, int col) {
        int x = Math.abs(row - this.row);
        int y = Math.abs(col - this.col);
        if (x+y == 10 || x+y == 9 || x+y == 8 || x+y == 7 || x+y == 6 || x+y == 5 || x+y == 4 || x+y == 3 || x+y == 2 || x+y == 1) {
            return 1;
        } else {
            return 0;
        }
    }


    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}


